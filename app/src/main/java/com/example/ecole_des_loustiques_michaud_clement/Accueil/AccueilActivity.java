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
import com.example.ecole_des_loustiques_michaud_clement.MainActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;

public class AccueilActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvWelcome;

    Button btnMaths;
    Button btnCulture;
    Button btnDeco;
    String prenom;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("PRENOM_KEY", prenom);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvWelcome = findViewById(R.id.tvWelcome);
        btnMaths = findViewById(R.id.btnMaths);
        btnCulture = findViewById(R.id.btnCulture);
        btnDeco = findViewById(R.id.btnDeco);

        btnMaths.setOnClickListener(this);
        btnCulture.setOnClickListener(this);
        btnDeco.setOnClickListener(this);

        if (savedInstanceState != null) {
            prenom = savedInstanceState.getString("PRENOM_KEY");
        } else {
            prenom = getIntent().getStringExtra("USER_PRENOM");
        }

        if (prenom == null || prenom.equals("Anonyme") || prenom.isEmpty()) {
            prenom = "Invité";
        }

        tvWelcome.setText("Salut " + prenom + " !");
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnMaths) {
            Intent intent = new Intent(AccueilActivity.this, ExercicesActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
        }
        else if (id == R.id.btnCulture) {
            Intent intent = new Intent(AccueilActivity.this, CapitaleActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            startActivity(intent);
        }else if (id == R.id.btnDeco) {
            Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}