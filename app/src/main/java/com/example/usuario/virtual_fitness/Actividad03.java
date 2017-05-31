package com.example.usuario.virtual_fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Actividad03 extends AppCompatActivity {
    private  int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad03);
        Mensaje("Maquinas");
        CargarSpinner();
    } // Fin del Oncreate de la Actividad 03

    public void Mensaje(String msg){getSupportActionBar().setTitle(msg);};
    public void MensajeToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    private void CargarSpinner() {
        Spinner s1;
        final String[] presidents = {
                "Ejemplo de Estiramientos",
                "Contractor Pectoral con Dorsal",
                "Eski",
                "Extensor",
                "Presión de Piernas",
                "Remado Sentado",
                "Rotación Diagonal Doble",
                "Rotación Vertical",
                "Simulador de Cabalgata",
                "Simulador de Caminata "
        };

        //---Spinner View---
        s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presidents);




        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                ImageView Mi_imageview = (ImageView) findViewById(R.id.imageView);

                switch (position){
                    case 0: Mi_imageview.setImageResource(R.drawable.estiramientos);
                        break;
                    case 1: Mi_imageview.setImageResource(R.drawable.contractorpectoralcondorso);
                        break;
                    case 2: Mi_imageview.setImageResource(R.drawable.eski);
                        break;
                    case 3: Mi_imageview.setImageResource(R.drawable.extensor);
                        break;
                    case 4: Mi_imageview.setImageResource(R.drawable.presiondepiernas);
                        break;
                    case 5: Mi_imageview.setImageResource(R.drawable.remadosentado);
                        break;
                    case 6: Mi_imageview.setImageResource(R.drawable.rotaciondiagonaldoble);
                        break;
                    case 7: Mi_imageview.setImageResource(R.drawable.rotacionvertical);
                        break;
                    case 8: Mi_imageview.setImageResource(R.drawable.simuladordecabalgata);
                        break;
                    case 9: Mi_imageview.setImageResource(R.drawable.simuladordecaminata);
                        break;

                    default: break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s1.setAdapter(adapter);


    }// fin de CargarSpinner


} // [07:03:59 p.m.] Fin de la Clase Actividad 03
