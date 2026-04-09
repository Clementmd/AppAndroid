package db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CapitaleDAO {

    @Insert
    void insert(Capitale capitale);

    @Query("SELECT COUNT(*) FROM capitale")
    int getCount();

    @Query("SELECT * FROM capitale ORDER BY RANDOM() LIMIT 5")
    List<Capitale> getSessionQuestions();
}