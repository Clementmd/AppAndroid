package db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CapitaleDAO {

    @Query("SELECT * FROM capitale ORDER BY RANDOM() LIMIT 1")
    Capitale getRandomQuestion();

    @Insert
    void insert(Capitale capitale);

    @Query("SELECT COUNT(*) FROM capitale")
    int getCount();
}