package fr.mden.gestionterrasses.metier;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.mden.gestionterrasses.outils.DAOBase;

public class EmplacementDAO extends DAOBase
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
     * Constructeur de la classe EmplacementDAO
     * @param pContext
     */
    public EmplacementDAO(Context pContext)
    {
        super(pContext);
    }

    /**
     * Ajouter un nouvel emplacement en base de données
     * @param e Le nouvel emplacement
     */
    public void ajouter(Emplacement e)
    {

    }

    /**
     * Supprimer un emplacement de la base de données
     * @param id L'ID de l'emplacement à supprimer
     */
    public void supprimer(int id)
    {

    }

    /**
     * Modifier un emplacement de la base de données
     * @param e L'emplacement à modifier
     */
    public void modifier(Emplacement e)
    {

    }

    /**
     * Sélectionner un emplacement de la base de données
     * @param id L'ID de l'emplacement à sélectionner
     * @return L'emplacement sélectionné
     */
    public Emplacement selectionner(int id)
    {
        Cursor c = mDb.rawQuery("SELECT * FROM " + EMPLACEMENT_TABLE_NAME + " WHERE " + EMPLACEMENT_ID + " = ?", new String[]{String.valueOf(id)});
        // Création et remplissage de l'emplacement en fonction des données récupérées
        Emplacement selection = new Emplacement(
                c.getInt(0), // ID
                c.getInt(1), // Coordonnée X
                c.getInt(2), // Coordonnée Y
                c.getString(3), // Rue 1
                c.getString(4), // Rue 2
                c.getString(5), // Ville
                c.getInt(6), // Superficie
                c.getInt(7) // Nombre de places de parking
        );

        return selection;
    }

    public List<Emplacement> tousLesEmplacements()
    {
        List<Emplacement> lesEmplacements = new ArrayList<Emplacement>();
        Cursor c = mDb.rawQuery("SELECT " + EMPLACEMENT_ID + " FROM " + EMPLACEMENT_TABLE_NAME, new String[]{});
        for (int i = 0; i < c.getCount(); i++)
        {
            lesEmplacements.add(this.selectionner(c.getInt(i)));
        }

        return lesEmplacements;
    }
}
