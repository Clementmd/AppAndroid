package com.example.ecole_des_loustiques_michaud_clement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.Addition.AdditionActivity;
import com.example.ecole_des_loustiques_michaud_clement.Multiplication.ChoixNiveauActivity;

public class ExercicesActivity extends AppCompatActivity implements View.OnClickListener{

    Button addition;

    Button multplication;

    Button retour;
    String prenom;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercices);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prenom = getIntent().getStringExtra("USER_PRENOM");

        addition = findViewById(R.id.btnAddition);
        multplication = findViewById(R.id.btnMultiplication);
        retour = findViewById(R.id.btnRetour);

        addition.setOnClickListener(this);
        multplication.setOnClickListener(this);
        retour.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.btnAddition){
            Intent intent = new Intent(ExercicesActivity.this, AdditionActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
        } else if (id==R.id.btnMultiplication) {
            Intent intent = new Intent(ExercicesActivity.this, ChoixNiveauActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
        }else if (id==R.id.btnRetour) {
            Intent intent = new Intent(ExercicesActivity.this, AccueilActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
        }
    }
}