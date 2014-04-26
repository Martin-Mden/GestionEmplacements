package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.mden.gestionterrasses.app.R;


public class AjoutEmplacementActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_emplacement);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.ajout_emplacement, menu);
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
            // Action pour valider l'ajout de l'emplacement
            case R.id.action_valider_ajout:
                finish();
                break;

            // Action pour annuler l'ajout de l'emplacement
            case R.id.action_annuler_ajout:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
