package com.example.ecole_des_loustiques_michaud_clement.Capitale;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_des_loustiques_michaud_clement.R;
import java.util.List;
import db.Capitale;
import db.DatabaseClient;

public class CapitaleActivity extends AppCompatActivity {

    private TextView tvProgression, tvPays;
    private EditText etReponse;
    private Button btnValider, btnQuitter;

    private List<Capitale> listeQuestions;
    private int indexQuestion = 0;
    private int score = 0;
    private String prenomEleve;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("SCORE_KEY", score);
        outState.putInt("INDEX_KEY", indexQuestion);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitale);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("SCORE_KEY");
            indexQuestion = savedInstanceState.getInt("INDEX_KEY");
        }

        tvProgression = findViewById(R.id.tvProgression);
        tvPays = findViewById(R.id.tvPays);
        etReponse = findViewById(R.id.etReponse);
        btnValider = findViewById(R.id.btnValider);
        btnQuitter = findViewById(R.id.btnQuitter);

        prenomEleve = getIntent().getStringExtra("USER_PRENOM");

        chargerSession();

        btnValider.setOnClickListener(v -> verifierEtSuivant());
        btnQuitter.setOnClickListener(v -> finish());
    }

    private void chargerSession() {
        new AsyncTask<Void, Void, List<Capitale>>() {
            @Override
            protected List<Capitale> doInBackground(Void... voids) {
                return DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .CapitaleDao()
                        .getSessionQuestions();
            }

            @Override
            protected void onPostExecute(List<Capitale> capitales) {
                listeQuestions = capitales;
                afficherQuestion();
            }
        }.execute();
    }

    private void afficherQuestion() {
        if (listeQuestions != null && indexQuestion < listeQuestions.size()) {
            Capitale q = listeQuestions.get(indexQuestion);

            tvProgression.setText("Question " + (indexQuestion + 1) + " / 5");
            tvPays.setText(q.getPays());
            etReponse.setText("");
        }
    }

    private void verifierEtSuivant() {
        if (listeQuestions == null) return;

        String reponseUser = etReponse.getText().toString().trim();
        String bonneReponse = listeQuestions.get(indexQuestion).getNomCapitale();

        if (reponseUser.equalsIgnoreCase(bonneReponse)) {
            score++;
            Toast.makeText(this, "Bravo !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Faux ! C'était " + bonneReponse, Toast.LENGTH_SHORT).show();
        }

        indexQuestion++;

        if (indexQuestion < 5) {
            afficherQuestion();
        } else {
            Intent intent = new Intent(CapitaleActivity.this, ScoreCapitaleActivity.class);
            intent.putExtra("SCORE_FINAL", score);
            intent.putExtra("USER_PRENOM", prenomEleve);
            startActivity(intent);
            finish();
        }
    }
}