package com.example.usuario.virtual_fitness;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Confirmacion extends AppCompatActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static String url_create_product = "http://virtualfitness.atwebpages.com/androidphp/add_new_patient.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);


        Button MiBoton = (Button) findViewById(R.id.button5);

        MiBoton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                Intent callingIntent = getIntent();
                int confirmacion = callingIntent.getIntExtra("confirmacion",1);

                EditText Mi_edittext = (EditText) findViewById(R.id.editText);
                if(confirmacion== Integer.parseInt(Mi_edittext.getText().toString())){
                    new CreateNewPatient().execute();
                    guardarLogeo();
                    Intent intento = new Intent(getApplicationContext(), ActividadMenu.class);
                    startActivity(intento);

                }
                else{
                    Mensaje("Error de confirmación");
                    Intent intento = new Intent(getApplicationContext(), Actividad_Registrar.class);
                    startActivity(intento);
                }
            }

        });

    }//fin oncreate
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    private void guardarLogeo(){
        Intent callingIntent = getIntent();
        String corre = callingIntent.getStringExtra("correo");
        String name = callingIntent.getStringExtra("nombre");
        String apelli = callingIntent.getStringExtra("apellido");

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("LoginPref", MODE_PRIVATE).edit();
        editor.putString("correo",corre);
        editor.putString("nombre",name);
        editor.putString("apellidos",apelli);
        editor.commit();
    }


    class CreateNewPatient extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Confirmacion.this);
            pDialog.setMessage("Actualizando el datos...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            Intent callingIntent = getIntent();
            String name = callingIntent.getStringExtra("correo");
            String gender = callingIntent.getStringExtra("nombre");
            String email = callingIntent.getStringExtra("apellido");
            String contra =callingIntent.getStringExtra("contrasena");
            String codigo="123";

            // String Address= inputAddress.getText().toString();
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("correo", name));
            params.add(new BasicNameValuePair("nombre", gender));
            params.add(new BasicNameValuePair("apellidos", email));
            params.add(new BasicNameValuePair("contrasena", contra));
            params.add(new BasicNameValuePair("codigo", codigo));
            // params.add(new BasicNameValuePair("patient_address",Address));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), ActividadMenu.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }//fon otra clase

}//fin clase principal
