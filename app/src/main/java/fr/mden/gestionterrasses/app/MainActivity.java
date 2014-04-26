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

        // Test ListView
        ListView listeEmplacements = (ListView) findViewById(R.id.liste_emplacements);
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

        List<HashMap<String, String>> lesEmplacements = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        for(int i = 0; i < infosEmplacements.length; i++)
        {
            element = new HashMap<String, String>();
            element.put("text1", infosEmplacements[i][0]);
            element.put("text2", infosEmplacements[i][1]);
            lesEmplacements.add(element);
        }

        ListAdapter adapter = new SimpleAdapter(this, lesEmplacements, android.R.layout.simple_list_item_2, new String[]{"text1", "text2"}, new int[]{android.R.id.text1, android.R.id.text2});

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_ajouter:
                Intent ajoutEmplacement = new Intent(MainActivity.this, AjoutEmplacementActivity.class);
                startActivity(ajoutEmplacement);
                break;

            case R.id.action_parametres:
                Intent parametres = new Intent(MainActivity.this, ParametresActivity.class);
                startActivity(parametres);
                break;

            case R.id.action_quitter:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
