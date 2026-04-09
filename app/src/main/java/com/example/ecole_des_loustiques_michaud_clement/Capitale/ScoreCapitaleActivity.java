package com.example.ecole_des_loustiques_michaud_clement.Capitale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

public class ScoreCapitaleActivity extends AppCompatActivity implements View.OnClickListener {

    private String prenom;
    private Button btnRejouer, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_capitale);

        int score = getIntent().getIntExtra("SCORE_FINAL", 0);
        prenom = getIntent().getStringExtra("USER_PRENOM");

        TextView tvResultatTitre = findViewById(R.id.tvResultatTitre);
        TextView tvScoreDetail = findViewById(R.id.tvScoreDetail);
        btnRejouer = findViewById(R.id.btnRejouer);
        btnMenu = findViewById(R.id.btnRetourMenu);

        if (score<3){
            tvResultatTitre.setText("Dommage " + prenom + ", tu feras mieux la prochaine fois!");
            tvScoreDetail.setText("Tu as obtenu un score de\n" + score + " / 5");
        }else {
            tvResultatTitre.setText("Bravo " + prenom + " !");
            tvScoreDetail.setText("Tu as obtenu un score de\n" + score + " / 5");
        }

        btnRejouer.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnRejouer) {
            Intent intent = new Intent(this, CapitaleActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.btnRetourMenu) {
            Intent intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}