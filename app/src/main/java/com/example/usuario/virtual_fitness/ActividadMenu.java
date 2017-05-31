package com.example.usuario.virtual_fitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActividadMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*TextView Mi_textview = (TextView) findViewById(R.id.textView_correo_Navi);
        Mi_textview.setText("Hola");*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);

        SharedPreferences pref = getSharedPreferences("LoginPref", MODE_PRIVATE);

        TextView txt_correo_Navi = (TextView) hView.findViewById(R.id.textView_correo_Navi);
        txt_correo_Navi.setText(pref.getString("correo","No logueado"));

        TextView txt_nombreCompleto= (TextView) hView.findViewById(R.id.txt_nombre_completo);
        txt_nombreCompleto.setText(pref.getString("nombre","Sin Nombre")+" "+pref.getString("apellidos","Sin Apellidos"));


        OnclickDelButton(R.id.button1);
        OnclickDelButton(R.id.button2);
        OnclickDelButton(R.id.button3);
        OnclickDelButton(R.id.btn_reporteD);
        OnclickDelButton(R.id.button4);
        Mensaje("Virtual Fitness");
    }

    public void Mensaje(String msg){getSupportActionBar().setTitle(msg);};



    public void OnclickDelButton(int ref) {

        // Ejemplo  OnclickDelButton(R.id.MiButton);
        // 1 Doy referencia al Button
        View view =findViewById(ref);
        Button miButton = (Button) view;
        //  final String msg = miButton.getText().toString();
        // 2.  Programar el evento onclick
        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.button1:
                        Intent intento1 = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intento1);
                        break;

                    case R.id.button2:
                        Intent intento = new Intent(getApplicationContext(), Actividad02.class);
                        startActivity(intento);
                        break;

                    case R.id.button3:
                        Intent intento2 = new Intent(getApplicationContext(), Actividad03.class);
                        startActivity(intento2);
                        break;
                    case R.id.btn_reporteD:
                        /*String[] to = { "contraloriaservicios@heredia.go.cr" };
                        String[] cc = { "" };
                        enviar(to, cc, "Reporte de Daños",
                                "Esto es un email enviado desde una app de Android");*/
                        Intent intento5 = new Intent(getApplicationContext(), ReporteDanos.class);
                        startActivity(intento5);
                        break;
                    case R.id.button4:
                        Intent intento3 = new Intent(getApplicationContext(), Actividad04.class);
                        startActivity(intento3);
                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton

    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        //String[] to = direccionesEmail;
        //String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_log_out) {
            cerrar_Sesion();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cerrar_Sesion(){
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("LoginPref", MODE_PRIVATE).edit();
        editor.remove("correo");
        editor.remove("nombre");
        editor.remove("apellidos");
        editor.commit();
        Intent intento = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intento);
    }
}
