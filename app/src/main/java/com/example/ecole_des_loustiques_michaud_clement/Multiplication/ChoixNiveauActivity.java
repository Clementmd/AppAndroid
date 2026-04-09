package com.example.ecole_des_loustiques_michaud_clement.Multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

public class ChoixNiveauActivity extends AppCompatActivity implements View.OnClickListener {

    private String prenom;
    private Button btnN1, btnN2, btnN3, btnQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_niveau);

        prenom = getIntent().getStringExtra("USER_PRENOM");

        btnN1 = findViewById(R.id.btnNiveau1);
        btnN2 = findViewById(R.id.btnNiveau2);
        btnN3 = findViewById(R.id.btnNiveau3);
        btnQuitter = findViewById(R.id.btnQuitterNiveau);

        btnN1.setOnClickListener(this);
        btnN2.setOnClickListener(this);
        btnN3.setOnClickListener(this);
        btnQuitter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnNiveau1 || id == R.id.btnNiveau2 || id == R.id.btnNiveau3) {
            Intent intent = new Intent(ChoixNiveauActivity.this, CalculActivity.class);
            intent.putExtra("USER_PRENOM", prenom);

            if (id == R.id.btnNiveau1) {
                intent.putExtra("NIVEAU", 1);
            }
            else if (id == R.id.btnNiveau2){
                intent.putExtra("NIVEAU", 2);
            }
            else {
                intent.putExtra("NIVEAU", 3);
            }
            startActivity(intent);
        }
        else if (id == R.id.btnQuitterNiveau) {
            Intent intent = new Intent(ChoixNiveauActivity.this, AccueilActivity.class);

            intent.putExtra("USER_PRENOM", prenom);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            finish();
        }
    }
}