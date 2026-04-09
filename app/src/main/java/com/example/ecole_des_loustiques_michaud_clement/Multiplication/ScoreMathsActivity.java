package com.example.ecole_des_loustiques_michaud_clement.Multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

public class ScoreMathsActivity extends AppCompatActivity implements View.OnClickListener {

    private String prenom;
    private int score;
    private TextView tvScoreTitre, tvScoreMessage;
    private Button btnRejouer, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_maths);

        prenom = getIntent().getStringExtra("USER_PRENOM");
        score = getIntent().getIntExtra("SCORE_FINAL", 0);

        tvScoreTitre = findViewById(R.id.tvScoreTitre);
        tvScoreMessage = findViewById(R.id.tvScoreMessage);
        btnRejouer = findViewById(R.id.btnRejouerMaths);
        btnMenu = findViewById(R.id.btnRetourMenu);

        btnRejouer.setOnClickListener(this);
        btnMenu.setOnClickListener(this);

        tvScoreTitre.setText("Ton score : " + score + " / 5");

        if (score < 3) {
            tvScoreMessage.setText("Dommage " + prenom + ", tu feras mieux la prochaine fois !");
        } else {
            tvScoreMessage.setText("Bravo " + prenom + " ! C'est un super score !");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnRejouerMaths) {
            Intent intent = new Intent(ScoreMathsActivity.this, ChoixNiveauActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.btnRetourMenu) {
            Intent intent = new Intent(ScoreMathsActivity.this, AccueilActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}