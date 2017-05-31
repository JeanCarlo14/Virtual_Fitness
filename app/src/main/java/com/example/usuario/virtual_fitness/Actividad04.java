package com.example.usuario.virtual_fitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Actividad04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad04);
        CargarSpinner();
    }//fin oncreate


    private void CargarSpinner() {
        Spinner s1;
        final String[] rutinas = {
                "Antes del Ejercicio",
                "Calentamiento",
                "Durante el Ejercicio",
                "Luego del ejercicio",
                "Como sigo"

        };


        //---Spinner View---
        s1 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rutinas);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

               switch (position){
                    case 0:
                        TextView Mi_textview = (TextView) findViewById(R.id.textView11);
                        Mi_textview.setText("\n ANTES DEL EJERCICIO\n" +
                                "\n" +
                                "Aplique tratamientos con frío o calor\n" +
                                "\n" +
                                "Aplique tratamientos con frío o con calor en las zonas que estará ejercitando. Si sus articulaciones están calientes, enrojecidas o hinchadas, emplee hielo antes de iniciar su rutina de ejercicios. Si sus articulaciones presentan dolor y rigidez, pero no están calientes o hinchadas emplee calor en las articulaciones afectadas antes de empezar el ejercicio. El calor relaja articulaciones y músculos y ayuda a aliviar el dolor. En algunas personas, el frío también reduce el dolor y la inflamación.\n" +
                                "\n" +
                                "Existen varias maneras de aplicar frío o calor. Algunos de los métodos que quizá desee intentar son:\n" +
                                "\n" +
                                "• Tomar una ducha tibia (no demasiado caliente) antes de hacer ejercicio;\n" +
                                "\n" +
                                "• Aplicar una bolsa o compresa caliente o una lámpara de calor en el área adolorida e inflamada;\n" +
                                "\n" +
                                "• Sentarse en un baño de hidromasaje con agua tibia; y/o\n" +
                                "\n" +
                                "• Envolver una bolsa con hielo o vegetales congelados en una toalla y colocarla en la zona que presenta dolor.\n" +
                                "\n" +
                                "También puede adquirir en una farmacia bolsas rellenas de un material gelatinoso, que son muy prácticas y pueden permanecer en el congelador para su uso frecuente.\n" +
                                "\n" +
                                "Asegúrese de aplicar calor o frío correctamente. Los tratamientos con calor deber ser reconfortantes y cómodos, no demasiado calientes. Aplique calor durante unos 20 minutos y cada vez que utilice terapia de frío, hágalo de 10 a 15 minutos. Para obtener mayor información sobre cómo emplear la terapia de calor o frío, visite las secciones “Dolor” y “Terapias alternativas y complementarias para el dolor” de nuestro sitio en español.");



                        break;
                    case 1:
                        TextView Mi_textview1 = (TextView) findViewById(R.id.textView11);
                        Mi_textview1.setText("\nCalentamiento\n\n" +
                                "Ya sea que haga ejercicios de amplitud de movimiento, de resistencia o de fortalecimiento, es importante que dedique de 5 a 15 minutos en movimientos de calentamiento antes de hacer ejercicio. Esto reducirá la posibilidad de incurrir en una lesión, al ayudar a su cuerpo a prepararse para hacer los ejercicios.\n" +
                                "\n" +
                                "Para empezar su rutina de calentamiento, camine despacio o realice una versión más lenta de los ejercicios que planea hacer y entonces estírese lentamente. Para alcanzar un estiramiento beneficioso de los músculos y tejidos ubicados alrededor de las articulaciones, mueva cada articulación hasta el límite del rango de movimiento de dicha articulación, aguante esta posición unos cinco segundos y descanse. El estiramiento NO debe causar dolor, simplemente haga cada movimiento hasta que sienta un ligero estiramiento y sosténgalo en ese punto. No se estire demasiado hasta el punto que duela. Cerciórese de estirar todos los músculos que formarán parte de su rutina de ejercicios.\n" +
                                "\n" +
                                "Use vestimenta y calzado cómodos\n" +
                                "\n" +
                                "Su vestimenta debe ser holgada y cómoda de modo que facilite el movimiento. Llevar varias capas de ropa le ayudará a adaptarse a los cambios de temperatura y a los cambios en el nivel de cada actividad. Su calzado debe proporcionarle buen sostén y las suelas deben ser de material antideslizante y amortiguador de golpes. También podría ayudarle el uso de plantillas que amortigüen los golpes.\n" +
                                "\n" +
                                "Beba suficientes líquidos\n" +
                                "\n" +
                                "Es muy importante hidratarse antes de empezar su rutina de ejercicio y continuar el proceso de hidratación después de haberlos concluido. Asegúrese de beber la cantidad recomendada de ocho vasos con agua de ocho onzas cada uno durante el transcurso de un día. Es recomendable tener una botella con agua o una bebida deportiva a la mano, mientras hace ejercicio.\n" +
                                "\n" +
                                "No haga muchos ejercicios demasiado rápido\n" +
                                "\n" +
                                "Iniciar un programa de ejercicio debe ser un proceso gradual que se extiende durante varias semanas o más tiempo. Puede presentarse un dolor muscular leve de 12 a 24 horas posteriores al ejercicio. Sin embargo, si tiene más dolor en las articulaciones que persiste después de dos horas de finalizar el ejercicio, quiere decir que probablemente se sobrepasó y debe disminuir un poco la cantidad de repeticiones o su intensidad para la próxima vez. A esto se le llama “la regla de dolor de las dos horas” y no significa que deba dejar el ejercicio, simplemente disminuya el ritmo. Recuerde que el no hacer ejercicio puede hacer que su artritis empeore. Si no obtiene buenos resultados, dialogue con su equipo de proveedores médicos.");

                        break;
                    case 2:
                        TextView Mi_textview2 = (TextView) findViewById(R.id.textView11);
                        Mi_textview2.setText("DURANTE EL EJERCICIO\n" +
                                "\n" +
                                "Tómese su tiempo\n" +
                                "\n" +
                                "Ejecute sus ejercicios a un ritmo cómodo y uniforme que le permita hablar con otra persona sin quedarse sin aliento. A este ritmo los músculos tendrán tiempo de relajarse entre cada repetición. Para ejercicios de amplitud de movimiento (elasticidad) y de flexibilidad, es mejor hacer cada ejercicio en forma lenta y completa en lugar de hacer muchas repeticiones a un ritmo veloz. Poco a poco, puede aumentar el número de repeticiones a medida que su condición física mejore.\n" +
                                "\n" +
                                "Respire mientras hace los ejercicios\n" +
                                "\n" +
                                "No contenga la respiración. Debe exhalar el aire de los pulmones mientras hace el ejercicio, y debe inhalar (tomar aire) mientras se relaja entre repeticiones. Contar en voz alta durante el ejercicio le ayudará a respirar profunda y regularmente.\n" +
                                "\n" +
                                "Esté atento ante “signos de alerta”\n" +
                                "\n" +
                                "Interrumpa el ejercicio si experimenta un dolor agudo o uno más intenso de lo normal. La presencia de dolor es una señal de que algo negativo podría estar sucediendo. De igual forma, suspenda el ejercicio de inmediato si siente opresión en el pecho o pérdida de aliento grave, está mareado, lánguido o con náuseas. Si aparecen estos síntomas, comuníquese enseguida con su médico.\n" +
                                "\n" +
                                "Conozca las señales de su cuerpo\n" +
                                "\n" +
                                "Durante las primeras semanas del programa de ejercicio, es probable que note que su corazón late más rápidamente, que su respiración se vuelve más agitada y que sus músculos se sienten tensos al hacer ejercicio. Quizá se sienta más cansado en la noche, pero despertará sintiéndose como nuevo a la mañana siguiente. Éstas son reacciones normales al ejercicio y significan que su cuerpo se está adaptando a las actividades nuevas y está poniéndose en forma. Si siente dolor muscular o calambres, masajee suavemente y estire el músculo afectado con cuidado. Cuando el dolor haya pasado, continúe haciendo sus ejercicios con movimientos lentos y suaves." );
                        break;
                   case 3:
                       TextView Mi_textview3=(TextView) findViewById(R.id.textView11);
                       Mi_textview3.setText("Enfriamiento\n" +
                               "\n" +
                               "Es importante enfriar su cuerpo después de realizar ejercicios, debido a que así, puede reducir las posibilidades de lesionarse. Para este proceso, simplemente repita los mismos ejercicios que hizo durante la rutina de calentamiento.\n" +
                               "\n" +
                               "Asegúrese de que el período de enfriamiento dure de 5 a 15 minutos, permitiendo que su frecuencia cardíaca y la respiración vuelvan a la normalidad. Concluir su rutina de ejercicios con ligeros estiramientos puede disminuir el dolor muscular.");
                       break;
                   case 4:
                       TextView Mi_textview4=(TextView) findViewById(R.id.textView11);
                       Mi_textview4.setText("\nComo sigo?\n\nantenga una actitud positiva sobre sí mismo y sobre su programa de ejercicio. Empero, también debe recordar que habrá días en los cuales no se sienta con ánimos de hacer mucho ejercicio. En esos días, reduzca la cantidad y la intensidad de sus ejercicios.\n" +
                               "\n" +
                               "Las claves para mantener su programa de ejercicio son:\n" +
                               "\n" +
                               "• Haga del ejercicio una costumbre cotidiana.\n" +
                               "\n" +
                               "• Tenga el hábito de hacer, al menos, alguna clase de ejercicio durante los días en que no se sienta motivado. Haga un esfuerzo por continuar su rutina, debido a que la interrupción de la misma puede disminuir los beneficios que se obtienen del ejercicio.\n" +
                               "\n" +
                               "• Escuche las señales de su cuerpo. Sepa cuándo disminuir o cambiar de ejercicio, según sea necesario.\n" +
                               "\n" +
                               "SUPERANDO OBSTÁCULOS\n" +
                               "\n" +
                               "Todos podemos encontrar mil excusas para no hacer ejercicio. Aquí enumeramos algunos de los problemas más comunes que puede hallar y formas de superarlos.\n" +
                               "\n" +
                               "“No he hecho ejercicio en mucho tiempo, ¿y si no puedo?” Es muy normal sentir dudas cuando se trata de hacer algo que no se ha hecho por mucho tiempo. Para superar estos sentimientos, trate de no ver a su rutina de ejercicios como competencia con otras personas. Al contrario, concéntrese en sus propias capacidades y haga únicamente lo que pueda. Piense positivamente, cada logro, por más pequeño que sea, le ayudará a mejorar su auto-estima y la confianza en sí mismo.\n" +
                               "\n" +
                               "“No estoy en condición. Me voy a demorar mucho en ver resultados” El establecer metas podría ayudarle a reconocer y a manejar sus problemas. Intente los siguientes consejos:\n" +
                               "\n" +
                               "Decida qué es lo que desea lograr (meta a largo plazo).\n" +
                               "\n" +
                               "Determine los pasos necesarios para alcanzar esta meta. Haga una lista de sus opciones y luego escoja una o dos a las que le gustaría dedicarse.\n" +
                               "\n" +
                               "Elabore planes con metas a corto plazo que le ayuden con el cumplimiento de las opciones escogidas. Estos planes identifican acciones específicas y realistas que podrá cumplir a corto plazo. Dichas actividades deberán ser de su agrado y sentir que sí las puede llevar a cabo y que además contribuyan a la realización de su meta a largo plazo. Haga un contrato de ejercicio consigo mismo (observe el ejemplo siguiente). Su equipo de proveedores médicos puede asesorarle en la elaboración de este contrato. Una vez hecho, coloque su contrato en un lugar donde pueda verlo todos los días.\n" +
                               "\n" +
                               "Ejecute su plan. Mantenga un diario de ejercicios en el que registre todas sus rutinas. Documente su progreso y cualquier dificultad que pueda surgir. Solicite a sus amigos y familiares que le comenten sus observaciones sobre su progreso.\n" +
                               "\n" +
                               "Revise los resultados de sus planes a corto plazo al final de cada semana.\n" +
                               "\n" +
                               "Modifique sus planes si algo no está funcionando. Si lo problemas continúan, solicite ayuda.\n" +
                               "\n" +
                               "“Me duele”. Es normal sentir un poco de dolor cuando se inicia un programa de ejercicio. Recuerde siempre practicar el calentamiento antes de empezar su rutina y el enfriamiento al terminarla, ya que le ayudará a relajar sus músculos y a reducir el dolor. Además recuerde que hacer ejercicio para fortalecer sus músculos y articulaciones, a menudo disminuye el dolor causado por la artritis. No se olvide de aplicar “la regla del dolor de las dos horas”.\n" +
                               "En los días que tenga más dolor en sus articulaciones (periodos agudos o de exacerbación) y si éstas se sienten más hinchadas, reduzca el número de repeticiones o de ejercicios que haga. También puede cambiar el tipo de ejercicio o las partes del cuerpo que esté ejercitando. Si nota un gran cambio en lo que puede hacer, consulte a su médico o terapeuta.\n" +
                               "“Hacer ejercicio es aburrido”. Diversifique su rutina. Realice ejercicios que le gusten. Pregúntele a su terapeuta sobre nuevos ejercicios que puedan añadir variedad a su programa. Escuche su música favorita mientras efectúa los ejercicios. Ejecute su rutina en compañía de amigos o familiares. Métase a una clase grupal. Si practica ciclismo o camina, hágalo en un parque o en un área que le agrade.\n" +
                               "“No tengo tiempo”. Establezca un horario para realizar sus ejercicios. La ejecución de varias rutinas cortas, es igualmente beneficioso, que una rutina larga. Establecer un momento o varios, durante el día, para hacer ejercicios, no debe convertirse en una carga. Piense en su horario de ejercicio como un momento especial para dedicarse a usted.\n" +
                               "“Hace mal tiempo”. Si normalmente hace ejercicio en grupo y no puede llegar a su clase, realice los ejercicios en casa. Si nada o camina, tenga un plan de contingencia para practicar el ejercicio en lugares cerrados. Por ejemplo, camine dentro de un centro comercial si el tiempo afuera no es propicio para una caminata.\n" +
                               "“No me gusta hacer ejercicio solo”. Pídale a sus amigos o familiares que hagan ejercicio con usted o inscríbase en una clase grupal de ejercicios. Una opción a considerar son los programas de ejercicios acuáticos y terrestres de la Arthritis Foundation. Conozca a otros participantes de la clase y al instructor. Si falta a varias clases seguidas, podría pedirles que le llamen por teléfono. El apoyo que su clase le puede brindar a menudo se convierte en una forma de motivación constante.\n" +
                               "“Es mucho esfuerzo”. Tal vez espera demasiado de su programa de ejercicio o es demasiado exigente. ¡Relájese! Hacer ejercicio por diversión es la mejor manera de mantenerse motivado.\n" +
                               "“Pierdo interés y me olvido del asunto”. Si tiene problemas para conservar su programa de ejercicio, analice los aspectos que podrían afectar su actitud. Pregúntese a sí mismo, ¿Porque deseaba iniciar un programa de ejercicio?, ¿Son estas razones todavía válidas? Mantenga un registro de lo que hace al final de cada día y marque los ejercicios que hizo.\n" +
                               "“Las articulaciones ya no me molestan”. El ejercicio probablemente tiene mucho que ver con que sus articulaciones ya no le molesten. En vez de parar su rutina de ejercicios, intente hacer ejercicios o actividades que brinden variedad a su programa.");

                    default:break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s1.setAdapter(adapter);


    }// fin de CargarSpinner
}//fin clase
