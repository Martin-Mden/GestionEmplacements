package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.mden.gestionterrasses.app.R;


public class ParametresActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.parametres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Récupération de l'id de l'élément sélectionné
        int id = item.getItemId();

        // En fonction de l'ID, on applique les actions spécifiques
        switch (id)
        {
            // Action pour sauvegarder les modifications effectuées dans les paramètres
            case R.id.action_sauvegarder_modifications:
                finish();
                break;

            // Action pour annuler les modifications effectuées dans les paramètres
            case R.id.action_annuler_modifications:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
