package com.example.ecole_des_loustiques_michaud_clement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import db.DatabaseClient;
import db.User;

// On implémente OnClickListener pour le "gros onClick"
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listUsers;
    private Button btnCreerCompte, btnAnonyme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        listUsers = findViewById(R.id.listUsers);
        btnCreerCompte = findViewById(R.id.btnCreerCompte);
        btnAnonyme = findViewById(R.id.btnAnonyme);

        // On lie les boutons au gros onClick
        btnCreerCompte.setOnClickListener(this);
        btnAnonyme.setOnClickListener(this);

        // Clic sur un utilisateur de la liste
        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) listUsers.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Content de te revoir " + user.getPrenom(), Toast.LENGTH_SHORT).show();

                // Redirection vers ton écran de sélection (Maths/Géo)
                lancerAccueilNonCo();
            }
        });

        // Charger la liste au démarrage
        getUsers();
    }

    // Le gros onClick avec des if
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnCreerCompte) {
            // Ouvre la page de création
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.btnAnonyme) {
            // Mode Anonyme : on va directement à l'accueil du jeu
            Toast.makeText(this, "Mode Anonyme", Toast.LENGTH_SHORT).show();
            lancerAccueilNonCo();
        }
    }

    // Méthode pour éviter de répéter le code de navigation
    private void lancerAccueilNonCo() {
        // Remplace 'ChoixActivity' par le nom de ton fichier pour l'écran de sélection
        Intent intent = new Intent(MainActivity.this, accueilnoncoActivity.class);
        startActivity(intent);
    }

    // On rafraîchit la liste quand on revient de la création de compte
    @Override
    protected void onResume() {
        super.onResume();
        getUsers();
    }

    // La classe asynchrone demandée par ton TP
    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {
            @Override
            protected List<User> doInBackground(Void... voids) {
                return DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .UserDao()
                        .getAll();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                // Utilise le toString() que tu as mis dans User.java pour l'affichage
                ArrayAdapter<User> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, users);
                listUsers.setAdapter(adapter);
            }
        }
        new GetUsers().execute();
    }
}