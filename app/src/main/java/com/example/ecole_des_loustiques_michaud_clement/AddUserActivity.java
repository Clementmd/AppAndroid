package com.example.ecole_des_loustiques_michaud_clement;

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
    private EditText editTextTaskView;
    private EditText editTextDescriptionView;
    private EditText editTextFinishByView;
    private Button saveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creercompte);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextTaskView = findViewById(R.id.etUsername);
        editTextDescriptionView = findViewById(R.id.etPassword);
        saveView = findViewById(R.id.btnSubmitAccount);

        // Associer un événement au bouton save
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {

        // Récupérer les informations contenues dans les vues
        final String sTask = editTextTaskView.getText().toString().trim();
        final String sDesc = editTextDescriptionView.getText().toString().trim();

        // Vérifier les informations fournies par l'utilisateur
        if (sTask.isEmpty()) {
            editTextTaskView.setError("Task required");
            editTextTaskView.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDescriptionView.setError("Desc required");
            editTextDescriptionView.requestFocus();
            return;
        }

        /**
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveTask extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {

                // creating a task
                User user = new User();
                user.setLibelle(sTask);
                task.setDescription(sDesc);

                // adding to database
                long id = mDb.getAppDatabase()
                        .UserDao()
                        .insert(task);

                // mettre à jour l'id de la tache
                // Nécessaire si on souhaite avoir accès à l'id plus tard dans l'activité
                task.setId(id);


                return task;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        SaveTask st = new SaveTask();
        st.execute();
    }

}