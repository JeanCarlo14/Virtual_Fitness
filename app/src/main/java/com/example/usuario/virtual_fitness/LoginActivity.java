package com.example.usuario.virtual_fitness;

import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_CONTACTS;
import static android.os.Build.VERSION_CODES.M;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    private static String url_one_patient = "http://virtualfitness.atwebpages.com/androidphp/get_patient_details.php";

    private static final int REQUEST_PERMISSION = 0; // Id para los permisos
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PATIENTS = "personas";


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    // products JSONArray
    JSONArray personas = null;

    // private ProgressDialog pDialog;
    private ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        populateAutoComplete();
        personas = new JSONArray();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                consultaDatos();
                return false;
            }
        });


        // alambramos el Button consultar
        Button button_Consulta = (Button) findViewById(R.id.email_sign_in_button);
        //Programamos el evento onclick
        button_Consulta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                consultaDatos();
            }

        });

        // alambramos el Button registrar
        Button button_registrar= (Button) findViewById(R.id.btn_Registrar);
        //Programamos el evento onclick
        button_registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intento = new Intent(getApplicationContext(), Actividad_Registrar.class);
                startActivity(intento);
            }

        });


        if(existeLogeo()!="false"){
            Intent intento = new Intent(getApplicationContext(), ActividadMenu.class);
            startActivity(intento);
        }//"true" valor default


    } // Fin Oncreate

    private String existeLogeo(){
        SharedPreferences pref = getSharedPreferences("LoginPref", MODE_PRIVATE);
        return  pref.getString("correo","false");
    }


    public void Mensaje2(String msg){Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    private void consultaDatos(){
        if(attemptLogin()!=true) {
            // Loading products in Background Thread
            new LoadAllProducts().execute();
        }
    }

    private void populateAutoComplete() {
        if (!mayRequestPermissions()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }


    private boolean mayRequestPermissions() { // Pedir permisos contactos y ubicación
        if (Build.VERSION.SDK_INT < M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS,ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS,ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
        }
        return false;
    }
    private void resetError(){
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
    }
    public boolean attemptLogin() {
        resetError();

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Revisar campo contraseña vacio o muy corta
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Campo requerido");
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError("Contraseña muy corta");
            focusView = mPasswordView;
            cancel = true;
        }

        // Revisar campo correo vacio o dirrecion de correo invalida

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Campo requerido");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("Dirección de correo invalida");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return cancel;
        }

        return false;
    }

    private void errorCorreo(){
        resetError();

        String email = mEmailView.getText().toString();
        View focusView = null;

        mEmailView.setError("Correo no registrado ");

        focusView = mEmailView;
        focusView.requestFocus();

    }
    private void verificarPass(){
        resetError();
        try {
            JSONObject c = personas.getJSONObject(0);
            String password = mPasswordView.getText().toString();
            boolean cancel = false;
            View focusView = null;

            if(password.compareTo(c.getString("contrasena"))!=0){
                mPasswordView.setError("Contraseña incorrecta");
                focusView = mPasswordView;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            }

            else{

                guardarLogeo(c.getString("nombre"),c.getString("apellidos"));
                Intent intento = new Intent(getApplicationContext(), ActividadMenu.class);
                startActivity(intento);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void guardarLogeo(String nombre, String apellidos){
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("LoginPref", MODE_PRIVATE).edit();
        editor.putString("correo",mEmailView.getText()+"");
        editor.putString("nombre",nombre);
        editor.putString("apellidos",apellidos);
        editor.commit();
    }
    public static boolean isEmailValid(String email) {
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


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void Mensaje(String msg){getSupportActionBar().setTitle(msg);};

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

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
            pDialog = new ProgressDialog(LoginActivity.this);
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

            String correo= "'"+mEmailView.getText().toString()+"'";
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
                        verificarPass();
                    }
                    else{
                        errorCorreo();
                    }



                }
            });

        }
    }


}

