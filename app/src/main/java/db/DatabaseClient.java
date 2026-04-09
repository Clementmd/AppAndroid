package db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    private static DatabaseClient instance;
    private AppDatabase appDatabase;

    private DatabaseClient(final Context context) {
        // On construit la base "EcoleDesLoustics" avec le callback de remplissage
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "EcoleDesLoustics")
                .addCallback(roomDatabaseCallback)
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // --- LE REMPLISSAGE AUTOMATIQUE ---
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Europe
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('France', 'Paris');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Espagne', 'Madrid');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Italie', 'Rome');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Allemagne', 'Berlin');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Royaume-Uni', 'Londres');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Portugal', 'Lisbonne');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Belgique', 'Bruxelles');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Suisse', 'Berne');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Grèce', 'Athenes');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Pays-Bas', 'Amsterdam');");

            // Afrique
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Maroc', 'Rabat');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Algérie', 'Alger');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Sénégal', 'Dakar');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Égypte', 'Le Caire');");

            // Amérique
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Canada', 'Ottawa');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('États-Unis', 'Washington');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Brésil', 'Brasilia');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Argentine', 'Buenos Aires');");

            // Asie
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Chine', 'Pekin');");
            db.execSQL("INSERT INTO Capitale (pays, nomCapitale) VALUES ('Japon', 'Tokyo');");
        }
    };
}