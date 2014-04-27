package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import fr.mden.gestionterrasses.app.R;
import fr.mden.gestionterrasses.metier.Emplacement;
import fr.mden.gestionterrasses.metier.EmplacementDAO;


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

        switch (id)
        {
            case R.id.action_supprimer_emplacement:

                // Demande de confirmation avant suppression
                AlertDialog.Builder builder = new AlertDialog.Builder(AffichageEmplacementActivity.this);
                builder.setTitle("Demande de confirmation");
                builder.setMessage("Voulez-vous vraiment supprimer cet emplacement ?");

                // NON
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // OUI
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Suppression de l'emplacement sélectionné
                        EmplacementDAO emplacementDAO = new EmplacementDAO(AffichageEmplacementActivity.this);
                        emplacementDAO.open();
                        emplacementDAO.supprimer(idSelectionne);
                        finish();
                        Toast.makeText(getApplicationContext(), "Emplacement supprimé.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
