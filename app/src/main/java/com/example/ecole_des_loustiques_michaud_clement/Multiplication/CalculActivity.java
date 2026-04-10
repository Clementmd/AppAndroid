package com.example.ecole_des_loustiques_michaud_clement.Multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

import java.util.Random;

public class CalculActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvProgression, tvCalcul;
    private EditText etReponse;
    private Button btnValider;

    private Button btnQuitter;

    private int niveau, score = 0, indexQuestion = 1;
    private int nombreA, nombreB, resultatAttendu;
    private String prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        tvProgression = findViewById(R.id.tvProgression);
        tvCalcul = findViewById(R.id.tvCalcul);
        etReponse = findViewById(R.id.etReponse);
        btnValider = findViewById(R.id.btnValider);
        btnQuitter = findViewById(R.id.btnQuitter);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("STATE_SCORE");
            indexQuestion = savedInstanceState.getInt("STATE_INDEX");
            prenom = savedInstanceState.getString("STATE_PRENOM");
            niveau = savedInstanceState.getInt("STATE_NIVEAU");

            nombreA = savedInstanceState.getInt("STATE_NA");
            nombreB = savedInstanceState.getInt("STATE_NB");
            resultatAttendu = nombreA * nombreB;

            tvProgression.setText("Question " + indexQuestion + " / 5");
            tvCalcul.setText(nombreA + "  x  " + nombreB + "  =  ?");
        } else {
            prenom = getIntent().getStringExtra("USER_PRENOM");
            niveau = getIntent().getIntExtra("NIVEAU", 1);
            genererQuestion();
        }

        btnQuitter.setOnClickListener(this);
        btnValider.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("STATE_SCORE", score);
        outState.putInt("STATE_INDEX", indexQuestion);
        outState.putString("STATE_PRENOM", prenom);
        outState.putInt("STATE_NIVEAU", niveau);
        outState.putInt("STATE_NA", nombreA);
        outState.putInt("STATE_NB", nombreB);

        super.onSaveInstanceState(outState);
    }

    private void genererQuestion() {
        Random random = new Random();
        int min, max;

        if (niveau == 1) {
            min = 1; max = 3;
        } else if (niveau == 2) {
            min = 4; max = 6;
        } else {
            min = 7; max = 9;
        }
        nombreA = random.nextInt(3) + min;
        nombreB = random.nextInt(3) + min;
        resultatAttendu = nombreA * nombreB;

        tvProgression.setText("Question " + indexQuestion + " / 5");
        tvCalcul.setText(nombreA + "  x  " + nombreB + "  =  ?");
        etReponse.setText("");
    }

    private void verifierReponse() {
        String reponseSaisie = etReponse.getText().toString();

        if (reponseSaisie.isEmpty()) return;

        int reponseUser = Integer.parseInt(reponseSaisie);

        if (reponseUser == resultatAttendu) {
            score++;
            Toast.makeText(this, "Bravo !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Faux ! C'était " + resultatAttendu, Toast.LENGTH_SHORT).show();
        }

        if (indexQuestion < 5) {
            indexQuestion++;
            genererQuestion();
        } else {
            Intent intent = new Intent(this, ScoreMathsActivity.class);
            intent.putExtra("SCORE_FINAL", score);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnValider) {
            verifierReponse();
        }
        else if (id == R.id.btnQuitter) {
            Intent intent = new Intent(CalculActivity.this, ChoixNiveauActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
            finish();
        }
    }
}