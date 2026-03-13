package db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilisateur")
public class User {
    @PrimaryKey
    private long nom;

    private long prenom;

    public long getNom() {
        return nom;
    }

    public long getPrenom() {
        return prenom;
    }

    public void setNom(long nom) {
        this.nom = nom;
    }

    public void setPrenom(long prenom) {
        this.prenom = prenom;
    }
}
