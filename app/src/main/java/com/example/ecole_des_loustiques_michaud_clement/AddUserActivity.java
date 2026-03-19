package com.example.ecole_des_loustiques_michaud_clement;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DatabaseClient;
import db.User;


public class AddUserActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText etUsername;
    private EditText etPrenom;
    private Button btnSubmitAccount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creercompte);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        etUsername = findViewById(R.id.etUsername);
        etPrenom = findViewById(R.id.etPrenom);
        btnSubmitAccount = findViewById(R.id.btnSubmitAccount);

        // Associer un événement au bouton save
        btnSubmitAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    private void saveUser() {

        // Récupérer les informations contenues dans les vues
        final String sUser = etUsername.getText().toString().trim();
        final String sPrenom = etPrenom.getText().toString().trim();

        // Vérifier les informations fournies par l'utilisateur
        if (sUser.isEmpty()) {
            etUsername.setError("Task required");
            etUsername.requestFocus();
            return;
        }

        if (sPrenom.isEmpty()) {
            etPrenom.setError("Prenom requis");
            etPrenom.requestFocus();
            return;
        }

        /**
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = new User();
                user.setNom(sUser);
                user.setPrenom(sPrenom);

                mDb.getAppDatabase()
                        .UserDao()
                        .insert(user);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        SaveUser ur = new SaveUser();
        ur.execute();
    }

}