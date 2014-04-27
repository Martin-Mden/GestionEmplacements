package fr.mden.gestionterrasses.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.mden.gestionterrasses.app.R;
import fr.mden.gestionterrasses.metier.Emplacement;
import fr.mden.gestionterrasses.metier.EmplacementDAO;


public class AjoutEmplacementActivity extends Activity
{
    // Champs du formulaire
    EditText coordX, coordY, rue1, rue2, ville, superficie, nbPlacesParking;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_emplacement);

        // Récupération des champs du formulaire
        coordX = (EditText) findViewById(R.id.coordX);
        coordY = (EditText) findViewById(R.id.coordY);
        rue1 = (EditText) findViewById(R.id.rue1);
        rue2 = (EditText) findViewById(R.id.rue2);
        ville = (EditText) findViewById(R.id.ville);
        superficie = (EditText) findViewById(R.id.superficie);
        nbPlacesParking = (EditText) findViewById(R.id.nbPlacesParking);

        Button ajouter = (Button) findViewById(R.id.bouton_ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionAjouter();
            }
        });

        Button annuler = (Button) findViewById(R.id.bouton_annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button vider = (Button) findViewById(R.id.bouton_vider);
        vider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordX.setText("");
                coordY.setText("");
                rue1.setText("");
                rue2.setText("");
                ville.setText("");
                superficie.setText("");
                nbPlacesParking.setText("");
            }
        });
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
                actionAjouter();
                break;

            // Action pour annuler l'ajout de l'emplacement
            case R.id.action_annuler_ajout:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void actionAjouter()
    {
        // On vérifie que tous les champs obligatoires ont été remplis
        boolean erreurFormulaire = false;
        String messageErreur = "Veuillez renseigner :\n";
        if((String.valueOf(coordX.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Coordonnée X"; }
        if((String.valueOf(coordY.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Coordonnée Y"; }
        if((String.valueOf(rue1.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Nom de la rue"; }
        if((String.valueOf(ville.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Ville"; }
        if((String.valueOf(superficie.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Superficie"; }
        if((String.valueOf(nbPlacesParking.getText())).equals("")) { erreurFormulaire = true; messageErreur += "\n - Nombre de places de parking"; }

        if(!erreurFormulaire)
        {
            Emplacement e = new Emplacement(
                    Integer.parseInt(String.valueOf(coordX.getText())),
                    Integer.parseInt(String.valueOf(coordY.getText())),
                    String.valueOf(rue1.getText()),
                    String.valueOf(rue2.getText()),
                    String.valueOf(ville.getText()),
                    Integer.parseInt(String.valueOf(superficie.getText())),
                    Integer.parseInt(String.valueOf(nbPlacesParking.getText()))
            );

            EmplacementDAO emplacementDAO = new EmplacementDAO(getApplicationContext());
            emplacementDAO.open();
            emplacementDAO.ajouter(e);

            finish();
            Toast.makeText(this.getApplicationContext(), "Emplacement ajouté.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Il y a une erreur dans le formulaire, on l'indique à l'utilisateur
            AlertDialog.Builder builder = new AlertDialog.Builder(AjoutEmplacementActivity.this);
            builder.setTitle("Formulaire incorrect");
            builder.setMessage(messageErreur + "\n");
            builder.setPositiveButton("J'ai compris", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
    }
}
