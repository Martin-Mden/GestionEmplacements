package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.mden.gestionterrasses.app.R;
import fr.mden.gestionterrasses.metier.EmplacementDAO;


public class ParametresActivity extends Activity
{
    ListView listeParametres;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        // Récupération des éléments
        listeParametres = (ListView) findViewById(R.id.liste_parametres);

        // On ajoute les éléments de paramètre dans la ListView
        List<HashMap<String, String>> lesParametres = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;

        // Videur de base de données
        element = new HashMap<String, String>();
        element.put("id", "vider_liste");
        element.put("text1", "Vider la liste");
        lesParametres.add(element);

        ListAdapter adapter = new SimpleAdapter(this, lesParametres, android.R.layout.simple_list_item_1, new String[]{"text1"}, new int[]{android.R.id.text1});

        // On lit l'adapter à la liste des emplacements
        listeParametres.setAdapter(adapter);

        // Clic sur un élément de paramétrage
        listeParametres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> unElement = (HashMap<String, String>)listeParametres.getItemAtPosition(position);
                String parametreSelectionne = unElement.get("id");

                if(parametreSelectionne.equals("vider_liste"))
                {
                    // Suppression de tous les éléments de la base de données
                    EmplacementDAO emplacementDAO = new EmplacementDAO(getApplicationContext());
                    emplacementDAO.open();
                    emplacementDAO.viderBaseDeDonnees();
                    finish();
                    Toast.makeText(getApplicationContext(), "Tous les emplacements ont été supprimés.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

        return super.onOptionsItemSelected(item);
    }
}
