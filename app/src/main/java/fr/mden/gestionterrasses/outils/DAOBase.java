package fr.mden.gestionterrasses.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase
{
    protected final static int VERSION = 1; // Version de la base de données
    protected final static String NOM = "GestionEmplacements.db"; // Nom du fichier de base de données

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context pContext)
    {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open()
    {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close()
    {
        mDb.close();
    }

    public SQLiteDatabase getDb()
    {
        return mDb;
    }
}
