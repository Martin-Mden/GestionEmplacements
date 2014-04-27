package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.mden.gestionterrasses.app.R;


public class AffichageEmplacementActivity extends Activity
{
    private int idSelectionne;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_emplacement);

        // Récupération de l'ID de l'emplacement sélectionné dans l'activité principale
        Bundle b = getIntent().getExtras();
        this.idSelectionne = b.getInt("id");

        TextView texte = (TextView) findViewById(R.id.infos_emplacement);
        texte.setText("ID : " + idSelectionne);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.affichage_emplacement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
