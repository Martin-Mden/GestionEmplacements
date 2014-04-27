package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.mden.gestionterrasses.app.R;
import fr.mden.gestionterrasses.metier.Emplacement;
import fr.mden.gestionterrasses.metier.EmplacementDAO;

public class MainActivity extends Activity
{
    // Liste des éléments de l'activité
    private ListView listeEmplacements;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des élements de l'activité
        listeEmplacements = (ListView) findViewById(R.id.liste_emplacements);
        Button boutonAjouterEmplacement = (Button) findViewById(R.id.bouton_ajouter_emplacement);

        // +---------------------------------+
        // | BOUTON "Ajouter un emplacement" |
        // +---------------------------------+

        boutonAjouterEmplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On lance l'activité "AjoutEmplacement"
                Intent ajoutEmplacement = new Intent(MainActivity.this, AjoutEmplacementActivity.class);
                startActivity(ajoutEmplacement);
            }
        });

        // +----------------------------------------------------+
        // | OPTION DES ELEMENTS DANS LA LISTE DES EMPLACEMENTS |
        // +----------------------------------------------------+

        // Clic court : Accès à la fiche de l'emplacement
        this.listeEmplacements.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // On affiche la fiche présentant les infos de l'emplacement
                Intent affichageEmplacement = new Intent(MainActivity.this, AffichageEmplacementActivity.class);

                // Passage de l'ID de l'emplacement cliqué à l'activité d'affichage
                Bundle b = new Bundle();
                HashMap<String, String> infosEmplacement = (HashMap<String, String>)listeEmplacements.getItemAtPosition(position);
                b.putInt("id", Integer.parseInt(infosEmplacement.get("id")));
                affichageEmplacement.putExtras(b);

                // Lancement de l'activité AffichageEmplacementActivity
                startActivity(affichageEmplacement);
            }
        });

        // Clic long : Accès aux options via une popup
        this.listeEmplacements.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                // On créé la popup
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Sélectionnez une action :");

                // Options sélectionnables
                CharSequence options[] = new CharSequence[]{"Modifier l'emplacement", "Supprimer l'emplacement"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int optionSelectionnee) {
                        switch (optionSelectionnee)
                        {
                            // Modification de l'emplacement
                            case 0:
                                break;

                            // Suppression de l'emplacement
                            case 1:
                                break;
                        }
                    }
                });

                builder.show();
                return true;
            }
        });

        refreshListeEmplacements();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        refreshListeEmplacements();
    }

    private void refreshListeEmplacements()
    {
        // +------------------------------------------+
        // | REMPLISSAGE DE LA LISTE DES EMPLACEMENTS |
        // +------------------------------------------+

        EmplacementDAO emplacementDAO = new EmplacementDAO(this.getApplicationContext());
        emplacementDAO.open();

        List<Emplacement> listeDesEmplacements = emplacementDAO.tousLesEmplacements();

        // Liste des associations "Première ligne/seconde ligne
        List<HashMap<String, String>> lesEmplacements = new ArrayList<HashMap<String, String>>();

        // Élément en cours de traitement
        HashMap<String, String> element;
        //HashMap<Integer, HashMap<String, String>> elements;

        // Pour chaque emplacement trouvé, on défini l'élément (association des infos) et on l'ajoute à la liste.
        for(Emplacement e : listeDesEmplacements)
        {
            element= new HashMap<String, String>();
            element.put("id", String.valueOf(e.getId()));
            element.put("text1", e.getRue1());
            element.put("text2", e.getVille() + " (Superficie : " + e.getSuperficie() + " m²)");
            lesEmplacements.add(element);
        }

        // Création de l'adapter avec toutes les données au dessus
        ListAdapter adapter = new SimpleAdapter(this, lesEmplacements, android.R.layout.simple_list_item_2, new String[]{"text1", "text2"}, new int[]{android.R.id.text1, android.R.id.text2});

        // On lit l'adapter à la liste des emplacements
        listeEmplacements.setAdapter(adapter);

        // On affiche le nombre d'éléments de la liste en haut
        int nombre = listeEmplacements.getCount();
        TextView afficheNombreEmplacements = (TextView) findViewById(R.id.nombre_emplacements);
        switch (nombre)
        {
            case 0:
                afficheNombreEmplacements.setText("Aucun emplacement.");
                break;
            case 1:
                afficheNombreEmplacements.setText("Vous avez entré 1 emplacement.");
                break;
            default:
                afficheNombreEmplacements.setText("Vous avez entré " + nombre + " emplacements.");
        }
    }

    // +-----------------------------+
    // | GESTION DE LA BAR D'ACTIONS |
    // +-----------------------------+

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            // Action pour ajouter un emplacement
            case R.id.action_ajouter:
                Intent ajoutEmplacement = new Intent(MainActivity.this, AjoutEmplacementActivity.class);
                startActivity(ajoutEmplacement);
                break;

            // Action pour accéder à la liste des paramètres de l'application
            case R.id.action_parametres:
                Intent parametres = new Intent(MainActivity.this, ParametresActivity.class);
                startActivity(parametres);
                break;

            // Action pour quitter l'application
            case R.id.action_quitter:
                System.exit(0); // Ferme complétement l'activité et l'application
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
