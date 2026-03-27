package com.example.ecole_des_loustiques_michaud_clement.Accueil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecole_des_loustiques_michaud_clement.Capitale.CapitaleActivity;
import com.example.ecole_des_loustiques_michaud_clement.ExercicesActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

public class AccueilcoActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvWelcome;

    Button btnMaths;
    Button btnCulture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueilco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvWelcome = findViewById(R.id.tvWelcome);
        btnMaths = findViewById(R.id.btnMaths);
        btnCulture = findViewById(R.id.btnCulture);

        btnMaths.setOnClickListener(this);
        btnCulture.setOnClickListener(this);

        String prenom = getIntent().getStringExtra("USER_PRENOM");

        tvWelcome.setText("Salut " + prenom + " !");
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnMaths) {
            Intent intent = new Intent(AccueilcoActivity.this, ExercicesActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.btnCulture) {
            Intent intent = new Intent(AccueilcoActivity.this, CapitaleActivity.class);
            startActivity(intent);
        }
    }
}