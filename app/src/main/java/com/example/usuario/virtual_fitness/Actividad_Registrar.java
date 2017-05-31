package com.example.usuario.virtual_fitness;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by ABC on 15-07-2016.
 */
public class Actividad_Registrar extends Activity {
//    private ProgressDialog pDialog;

    //  JSONParser jsonParser = new JSONParser();
    EditText inputCorreo;
    EditText inputNombre;
    EditText inputEmail;
    EditText inputApellidos;
    EditText contrasena;
    EditText confirmacion_Contrasena;
    // url to create new product
    //virtual-fitness.000webhostapp.com
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static String url_one_patient = "http://virtualfitness.atwebpages.com/androidphp/get_patient_details.php";
    // private ProgressDialog pDialog;
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    // products JSONArray
    JSONArray personas = null;
    private static final String TAG_PATIENTS = "personas";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad__registrar);

        // Edit Text
        inputCorreo = (EditText) findViewById(R.id.edt_Correo);
        inputNombre = (EditText) findViewById(R.id.edt_Nombre);
        inputApellidos = (EditText) findViewById(R.id.edt_Apellidos);
        contrasena = (EditText) findViewById(R.id.edt_Contrasena);
        confirmacion_Contrasena = (EditText) findViewById(R.id.edt_ConfirContrasena);

        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btn_Guardar);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                //  new CreateNewPatient().execute();
                if(verificarDatos()==false) {
                    // Loading products in Background Thread
                    new LoadAllProducts().execute();
                }
            }
        });

        // Create button
        Button btnCancel = (Button) findViewById(R.id.btn_Cancelar);
        // button click event
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                limpiarCampos();
                Intent intento = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intento);
            }
        });

    }// Fin del Oncreate

    private void resetError(){
        // Reset errors.
        inputCorreo.setError(null);
        inputNombre.setError(null);
        inputApellidos.setError(null);
        contrasena.setError(null);
        confirmacion_Contrasena.setError(null);
    }

    private  boolean verificarDatos(){
        resetError();

        // Store values at the time of the login attempt.
        String inp_correo = inputCorreo.getText().toString();
        String inp_nombre = inputNombre.getText().toString();
        String inp_apellidos = inputApellidos.getText().toString();
        String inp_contra = contrasena.getText().toString();
        String inp_confir_contra = confirmacion_Contrasena.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Verificando que no este vacio campo nombre
        if (TextUtils.isEmpty(inp_nombre)) {
            inputNombre.setError("Campo requerido");
            focusView = inputNombre;
            cancel = true;
        }
        // Verificando que no este vacio campo apellidos
        if (TextUtils.isEmpty(inp_apellidos)) {
            inputApellidos.setError("Campo requerido");
            focusView = inputApellidos;
            cancel = true;
        }
        // Revisar Email vacio o invalidp
        if (TextUtils.isEmpty(inp_correo)) {
            inputCorreo.setError("Campo requerido");
            focusView = inputCorreo;
            cancel = true;
        }
        else if (!isEmailValid(inp_correo)) {
            inputCorreo.setError("Dirección de correo invalida");
            focusView = inputCorreo;
            cancel = true;
        }
        if (TextUtils.isEmpty(inp_contra)) {
            contrasena.setError("Campo requerido");
            focusView = contrasena;
            cancel = true;
        }
        else if (!isPasswordValid(inp_contra)) {
            contrasena.setError("Contraseña muy corta");
            focusView = contrasena;
            cancel = true;
        }
        if (TextUtils.isEmpty(inp_confir_contra)) {
            confirmacion_Contrasena.setError("Campo requerido");
            focusView = confirmacion_Contrasena;
            cancel = true;
        }
        else if(!TextUtils.equals(inp_contra,inp_confir_contra)){
            confirmacion_Contrasena.setError("No coincide con la contraseña");
            focusView = confirmacion_Contrasena;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
            return cancel;
        }

        return false;


    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /*confirmacion*/
    String correo; //emisor
    String contrasenna;
    String mensaje;
    Button enviar;
    Session session;

    public void confirmacion(){
        Random rand=new Random();
        int randomNum = rand.nextInt((999999 - 100000) + 1) + 100000;
        Mensaje(randomNum+"");
        mensaje= "Su numero de confirmación es: "+String.valueOf(randomNum);
        enviar=(Button)findViewById(R.id.btn_Registrar);
        correo="alexandra.aguilar.najera@gmail.com";
        contrasenna="ale!_aguilar";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");


        try{
            session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo,contrasenna);
                }
            });
            if(session!=null){
                Mensaje("sesion no nula");
                javax.mail.Message message=new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.setSubject("Codigo confirmación VirtualFitness ");
                message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(inputCorreo.getText().toString()));//correo receptor
                message.setContent(mensaje.toString(),"text/html; chartset=utf-8");
                Transport.send(message);

            }
        }
        catch (Exception e){
            Mensaje("Cayo en cath");
            e.printStackTrace();
        }
        Intent intento = new Intent(getApplicationContext(), Confirmacion.class);
        intento.putExtra("confirmacion", randomNum);
        intento.putExtra("correo", inputCorreo.getText().toString());
        intento.putExtra("nombre", inputNombre.getText().toString());
        intento.putExtra("apellido", inputApellidos.getText().toString());
        intento.putExtra("contrasena", contrasena.getText().toString());
        startActivity(intento);

    }




    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
    /*confirmacion*/

    private void limpiarCampos(){
        inputCorreo.setText("");
        inputNombre.setText("");
        inputApellidos.setText("");
        contrasena.setText("");
        confirmacion_Contrasena.setText("");
    }

    private void errorCorreo(){
        resetError();

        View focusView = null;

        inputCorreo.setError("Correo ya fue registrado");

        focusView = inputCorreo;
        focusView.requestFocus();
    }

    /**
     *
     * Background Async Task to Create new Patient
     */
   /* class CreateNewPatient extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
      /*  @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Actividad_Registrar.this);
            pDialog.setMessage("Uploading to server...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         */
       /* protected String doInBackground(String... args) {
            String name = inputCorreo.getText().toString();
            String gender = inputNombre.getText().toString();
            String email = inputApellidos.getText().toString();
            String contra =contrasena.getText().toString();
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
                    Intent i = new Intent(getApplicationContext(), Actividad01.class);
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
      /*  protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }*/

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Actividad_Registrar.this);
            pDialog.setMessage("Verificando datos. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        int success = 0;

        /**
         * getting un usuario from url
         */
        protected String doInBackground(String... args) {

            String correo= "'"+inputCorreo.getText().toString()+"'";
            if(correo ==""){
                correo = "vacio";
            }

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("correo", correo));


            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_one_patient, "GET", params);
            try {
                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    try {
                        // personas found
                        // Getting Array of patients
                        personas = json.getJSONArray(TAG_PATIENTS);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    if (success == 1) {
                        errorCorreo();
                    }
                    else{
                        confirmacion();
                    }



                }
            });

        }
    }


}

