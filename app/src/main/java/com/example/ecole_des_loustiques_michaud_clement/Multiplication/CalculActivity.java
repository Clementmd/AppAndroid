package com.example.ecole_des_loustiques_michaud_clement.Multiplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

import java.util.Random;

public class CalculActivity extends AppCompatActivity {


    private TextView tvProgression, tvCalcul;
    private EditText etReponse;
    private Button btnValider;

    private int niveau, score = 0, indexQuestion = 1;
    private int nombreA, nombreB, resultatAttendu;
    private String prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        prenom = getIntent().getStringExtra("USER_PRENOM");
        niveau = getIntent().getIntExtra("NIVEAU", 1);

        tvProgression = findViewById(R.id.tvProgression);
        tvCalcul = findViewById(R.id.tvCalcul);
        etReponse = findViewById(R.id.etReponse);
        btnValider = findViewById(R.id.btnValider);

        genererQuestion();

        btnValider.setOnClickListener(v -> verifierReponse());
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
}