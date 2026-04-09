package com.example.ecole_des_loustiques_michaud_clement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecole_des_loustiques_michaud_clement.Accueil.AccueilActivity;

import java.util.List;
import db.DatabaseClient;
import db.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listUsers;
    private Button btnCreerCompte, btnAnonyme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsers = findViewById(R.id.listUsers);
        btnCreerCompte = findViewById(R.id.btnCreerCompte);
        btnAnonyme = findViewById(R.id.btnAnonyme);

        btnCreerCompte.setOnClickListener(this);
        btnAnonyme.setOnClickListener(this);

        listUsers.setOnItemClickListener((parent, view, position, id) -> {
            User user = (User) listUsers.getItemAtPosition(position);
            ouvrirAccueil(user.getPrenom());
        });

        getUsers();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnCreerCompte) {
            startActivity(new Intent(this, AddUserActivity.class));
        }
        else if (id == R.id.btnAnonyme) {
            ouvrirAccueil("Anonyme");
        }
    }

    private void ouvrirAccueil(String prenom) {
        Intent intent = new Intent(this, AccueilActivity.class);
        intent.putExtra("USER_PRENOM", prenom);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsers();
    }

    private void getUsers() {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... voids) {
                return DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().UserDao().getAll();
            }
            @Override
            protected void onPostExecute(List<User> users) {
                listUsers.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, users));
            }
        }.execute();
    }
}