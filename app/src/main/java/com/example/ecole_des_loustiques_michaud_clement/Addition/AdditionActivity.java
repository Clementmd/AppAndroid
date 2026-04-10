package com.example.ecole_des_loustiques_michaud_clement.Addition;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;
import com.example.ecole_des_loustiques_michaud_clement.R;
import java.util.Random;

public class AdditionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvProgression;
    private LinearLayout containerCalculs;
    private Button btnValider, btnQuitter;
    private EditText etActuel;
    private int indexQuestion = 1;
    private int resultatAttendu;
    private String prenom;


    // Sauvegarde les données AVANT que l'activité ne soit arrêtée
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("INDEX_KEY", indexQuestion);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        if (savedInstanceState != null) {
            indexQuestion = savedInstanceState.getInt("INDEX_KEY");
        }

        prenom = getIntent().getStringExtra("USER_PRENOM");

        tvProgression = findViewById(R.id.tvProgressionAdd);
        containerCalculs = findViewById(R.id.containerCalculs);
        btnValider = findViewById(R.id.btnValiderAdd);
        btnQuitter = findViewById(R.id.btnQuitterAdd);

        btnValider.setOnClickListener(this);
        btnQuitter.setOnClickListener(this);

        genererNouvelleLigne();
    }

    private void genererNouvelleLigne() {
        if (etActuel != null) {
            etActuel.setEnabled(false);
            etActuel.setTextColor(Color.GRAY);
        }

        Random random = new Random();
        int nombreA = random.nextInt(50) + 1;
        int nombreB = random.nextInt(50) + 1;
        resultatAttendu = nombreA + nombreB;

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);
        row.setPadding(0, 15, 0, 15);

        TextView tvCalcul = new TextView(this);
        tvCalcul.setText(nombreA + " + " + nombreB + " = ");
        tvCalcul.setTextSize(24);
        tvCalcul.setTextColor(Color.BLACK);
        tvCalcul.setTypeface(null, Typeface.BOLD);

        etActuel = new EditText(this);
        etActuel.setInputType(InputType.TYPE_CLASS_NUMBER);
        etActuel.setHint("?");
        etActuel.setTextSize(22);
        etActuel.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.WRAP_CONTENT);
        etActuel.setLayoutParams(params);

        row.addView(tvCalcul);
        row.addView(etActuel);
        containerCalculs.addView(row);

        tvProgression.setText("Addition " + indexQuestion + " / 10");
        etActuel.requestFocus();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnValiderAdd) {
            String saisie = etActuel.getText().toString();

            if (!saisie.isEmpty()) {
                int reponseUser = Integer.parseInt(saisie);

                if (reponseUser == resultatAttendu) {
                    if (indexQuestion < 10) {
                        indexQuestion++;
                        genererNouvelleLigne();
                    } else {
                        afficherMessageFin();
                    }
                } else {
                    Toast.makeText(this, "Faux, essaie encore !", Toast.LENGTH_SHORT).show();
                    etActuel.setText("");
                }
            }
        }
        else if (id == R.id.btnQuitterAdd) {
            Intent intent = new Intent(AdditionActivity.this, AccueilActivity.class);
            intent.putExtra("USER_PRENOM", prenom);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void afficherMessageFin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Félicitations " + prenom + " !");
        builder.setMessage("Tu as réussi tes 10 additions !");
        builder.setCancelable(false);

        builder.setPositiveButton("Super !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AdditionActivity.this, AccueilActivity.class);
                intent.putExtra("USER_PRENOM", prenom);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}