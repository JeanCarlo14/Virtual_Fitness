package com.example.usuario.virtual_fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Actividad02 extends AppCompatActivity {
    private  int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad02);
        Mensaje("Mis rutinas");

        OnclickDelRadioButton(R.id.radioButton1);
        OnclickDelRadioButton(R.id.radioButton2);

        CargarSpinner();
    } // Fin del Oncreate de la Actividad 02

    public void Mensaje(String msg){getSupportActionBar().setTitle(msg);};

    public void OnclickDelRadioButton(int ref) {

        // Ejemplo  OnclickDelRadioButton(R.id.MiRadioButton);
        // 1 Doy referencia al RadioButton
        View view =findViewById(ref);
        RadioButton miRadioButton = (RadioButton) view;
        //  final String msg = miRadioButton.getText().toString();
        // 2.  Programar el evento onclick
        miRadioButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.radioButton1:
                        CargarSpinner();
                        break;

                    case R.id.radioButton2:
                        CargarSpinner();
                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelRadioButton

    private void CargarSpinner() {
        Spinner s1;
        final String[] rutinas = {
                "Básico",
                "Intermedio",
                "Avanzado"
        };


        //---Spinner View---
        s1 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rutinas);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                RadioButton Mi_radiobutton1 = (RadioButton) findViewById(R.id.radioButton1);
                TextView Mi_textView = (TextView) findViewById(R.id.textView4);

                switch (position){
                    case 0:
                        if(Mi_radiobutton1.isChecked())
                            Mi_textView.setText("\n Rutina Actividad Física Básico:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina. \n" +
                                    "2. Realizar 10 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 3 series de 10 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 minutos entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante el entrenamiento.\n" +
                                    "6. Finalice con unos estiramientos para evitar lesiones.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana durante un mes. " +
                                    "Despues de un mes puede pasar al siguiente nivel, en este caso intermedio.");
                        else
                            Mi_textView.setText("\n Rutina Para Perder Peso Básico:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina. \n" +
                                    "2. Realizar 10 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 3 series de 10 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar.\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 minutos entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante el entrenamiento.\n" +
                                    "6. Finalice con unos estiramientos para evitar lesiones.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana durante un mes. " +
                                    "Despues de un mes puede pasar al siguiente nivel, en este caso intermedio.");
                        break;
                    case 1:
                        if(Mi_radiobutton1.isChecked())
                            Mi_textView.setText("\n Rutina Actividad Física Intermedio:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina. \n" +
                                    "2. Realizar 15 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 3 series de 20 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar.\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante el proceso.\n" +
                                    "6. Finalice con unos estiramientos.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana durante un mes o mes y medio. " +
                                    "Despues de este tiempo puede pasar al siguiente nivel, en este caso Avanzado.");
                        else
                            Mi_textView.setText("\n Rutina Para Perder Peso Intermedio:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina \n" +
                                    "2. Realizar 30 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 5 series de 20 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar.\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 minutos entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante el proceso\n" +
                                    "6. Finalice con unos estiramientos para evitar lesiones.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana durante un mes. " +
                                    "Despues de un mes puede pasar al siguiente nivel, en este caso Avanzado.");
                        break;
                    case 2:
                        if(Mi_radiobutton1.isChecked())
                            Mi_textView.setText("\n Rutina Actividad Física Avanzado:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina.\n" +
                                    "2. Realizar 20 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 4 series de 25 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar.\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 minutos entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante todo el proceso.\n" +
                                    "6. Finalice con unos estiramientos para evitar lesiones.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana. " +
                                    "Despues de un mes o mes y medio si quiere puede aumentar su rutina; puede realizar " +
                                    "10 repeticiones más cada mes o mes y medio, simpre y cuando la rutina no exceda una hora u hora y media" +
                                    "de tiempo");
                        else
                            Mi_textView.setText("\n Rutina Para Perder Peso Avanzado:\n\n" +
                                    "1. Realizar estiramientos antes de iniciar la rutina \n" +
                                    "2. Realizar 45 minutos de cardio en la maquina de su preferencia" +
                                    "ya sea en la bicilcte estacionaria o simuladora de caminata. \n" +
                                    "3. Realizar 5 series de 30 repeticiones en cada una de las distintas maquinas" +
                                    "que brinda el gimnasio, dependiendo de la parte del cuerpo que quiera trabajar.\n" +
                                    "4. Realize pequeños descansos de 1 minuto o 1:30 minutos entre cada serie. \n" +
                                    "5. Recuerde que debe mantener una buena hidratación durante todo el proceso.\n" +
                                    "6. Finalice con unos estiramientos para evitar lesiones.\n\n" +
                                    "Nota: Puede realizar esta rutina de ejercicio de 2 a 3 veces por semana." +
                                    "Despues de un mes o mes y meido si quiere puede aumentar su rutina;" +
                                    "puede realizar 5 o 10 repeticiones más cada mes, simpre y cuando la rutina no exceda hora y media a dos horas" +
                                    "de tiempo");
                        break;

                    default:break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s1.setAdapter(adapter);


    }// fin de CargarSpinner


} // [06:52:11 p.m.] Fin de la Clase Actividad 02