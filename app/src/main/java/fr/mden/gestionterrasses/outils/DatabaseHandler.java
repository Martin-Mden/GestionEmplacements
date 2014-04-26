package fr.mden.gestionterrasses.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Propriétés de la table Emplacement :
    public static final String EMPLACEMENT_ID = "id";
    public static final String EMPLACEMENT_COORD_X = "coordX";
    public static final String EMPLACEMENT_COORD_Y = "coordY";
    public static final String EMPLACEMENT_RUE_1 = "rue1";
    public static final String EMPLACEMENT_RUE_2 = "rue2";
    public static final String EMPLACEMENT_VILLE = "ville";
    public static final String EMPLACEMENT_SUPERFICIE = "superficie";
    public static final String EMPLACEMENT_NB_PLACES_PARKING = "nbPlacesParking";

    // Infos sur la table Emplacement et création dans la base de données
    public static final String EMPLACEMENT_TABLE_NAME = "Emplacement";
    public static final String EMPLACEMENT_TABLE_CREATE =
            "CREATE TABLE " + EMPLACEMENT_TABLE_NAME + " (" +
                    EMPLACEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMPLACEMENT_COORD_X + " INTEGER NOT NULL, " +
                    EMPLACEMENT_COORD_Y + " INTEGER NOT NULL, " +
                    EMPLACEMENT_RUE_1 + " TEXT NOT NULL, " +
                    EMPLACEMENT_RUE_2 + " TEXT, " +
                    EMPLACEMENT_VILLE + " TEXT NOT NULL, " +
                    EMPLACEMENT_SUPERFICIE + " INTEGER NOT NULL, " +
                    EMPLACEMENT_NB_PLACES_PARKING + " INTEGER NOT NULL);";

    public static final String EMPLACEMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + EMPLACEMENT_TABLE_NAME + ";";

    /**
     * Constructeur de la classe DatabaseHandler
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // On lance la création de la base de données
        db.execSQL(EMPLACEMENT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // On supprime la table existante avant d'en recréer une
        db.execSQL(EMPLACEMENT_TABLE_DROP);
        onCreate(db);
    }
}
