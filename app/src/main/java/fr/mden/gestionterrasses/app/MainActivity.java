package fr.mden.gestionterrasses.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // +------------------------------------------+
        // | REMPLISSAGE DE LA LISTE DES EMPLACEMENTS |
        // +------------------------------------------+

        // On récupère l'élément ListView de l'activité pour pouvoir travailler avec
        ListView listeEmplacements = (ListView) findViewById(R.id.liste_emplacements);

        // Récupération des données (entrées manuelles pour le moment)
        String[][] infosEmplacements = new String[][]{
                {"Ligne 1", "Infos 1"},
                {"Ligne 2", "Infos 2"},
                {"Ligne 3", "Infos 3"},
                {"Ligne 4", "Infos 4"},
                {"Ligne 5", "Infos 5"},
                {"Ligne 6", "Infos 6"},
                {"Ligne 7", "Infos 7"},
                {"Ligne 8", "Infos 8"},
                {"Ligne 9", "Infos 9"},
                {"Ligne 10", "Infos 10"},
                {"Ligne 11", "Infos 11"}};

        // Liste des associations "Première ligne/seconde ligne
        List<HashMap<String, String>> lesEmplacements = new ArrayList<HashMap<String, String>>();

        // Élément en cours de traitement
        HashMap<String, String> element;

        // Pour chaque emplacement trouvé, on défini l'élément (association des infos) et on l'ajoute à la liste.
        for(int i = 0; i < infosEmplacements.length; i++)
        {
            element = new HashMap<String, String>();
            element.put("text1", infosEmplacements[i][0]);
            element.put("text2", infosEmplacements[i][1]);
            lesEmplacements.add(element);
        }

        // Création de l'adapter avec toutes les données au dessus
        ListAdapter adapter = new SimpleAdapter(this, lesEmplacements, android.R.layout.simple_list_item_2, new String[]{"text1", "text2"}, new int[]{android.R.id.text1, android.R.id.text2});

        // On lit l'adapter à la liste des emplacements
        listeEmplacements.setAdapter(adapter);
    }


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
