package db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "capitale")
public class Capitale {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String pays;
    private String nomCapitale;

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }
    public String getNomCapitale() { return nomCapitale; }
    public void setNomCapitale(String nomCapitale) { this.nomCapitale = nomCapitale; }
}