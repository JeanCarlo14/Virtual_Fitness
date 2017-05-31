package com.example.usuario.virtual_fitness;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ReporteDanos extends AppCompatActivity {
    String correo; //emisor
    String contrasenna;
    Session session;
    public String ale;
    String APP_DIRECTORY="myPictureApp/";
    String MEDIA_DIRECTORY=APP_DIRECTORY+"media";
    String TEMPORAL_PICTURE_NAME="temporal.jpg";
    private  final int PHOTO_CODE=100;
    private final int SELECT_PICTURE=200;
    private ImageView imageView;
    int opcion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_danos);


        correo="alexandra.aguilar.najera@gmail.com";
        contrasenna="ale!_aguilar";
        OnclickDelButton(R.id.envioCorreo);
        OnclickDelButton(R.id.button6);




    }//fin oncreate

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

    public void OnclickDelButton(int ref) {


        View view =findViewById(ref);
        Button miButton = (Button) view;

        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.envioCorreo:
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
                                message.setSubject("Reporte de daño ");
                                message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse("alexandra.aguilar.najera@gmail.com"));//correo receptor
                                //message.setContent(mensaje.getText().toString(),"text/html; chartset=utf-8");

                                MimeMultipart multipart=new MimeMultipart();
                                if(opcion!=0){
                                    BodyPart adjunto=new MimeBodyPart();
                                    DataSource source = new FileDataSource(ale);
                                    adjunto.setDataHandler(new DataHandler(source));
                                    if(opcion==PHOTO_CODE)
                                        adjunto.setFileName(ale);
                                    if(opcion==PHOTO_CODE)
                                        adjunto.setFileName(ale);

                                    multipart.addBodyPart(adjunto);}


                                BodyPart texto = new MimeBodyPart();
                                EditText Mi_edittext = (EditText) findViewById(R.id.editText2);
                                texto.setText(Mi_edittext.getText().toString());
                                multipart.addBodyPart(texto);
                                message.setContent(multipart);


                                Mensaje("Antes de enviar");
                                Transport.send(message);
                                Mensaje("Daño Reportado");


                            }
                        }
                        catch (Exception e){
                            Mensaje("Cayo en cath");
                            e.printStackTrace();
                        }

                        Intent intento = new Intent(getApplicationContext(), ActividadMenu.class);
                        startActivity(intento);

                        break;

                    case R.id.button6:
                        imageView=(ImageView)findViewById(R.id.imageView5);
                        final CharSequence[] options ={"Tomar foto","Elegir de Galería","Cancelar"};
                        final AlertDialog.Builder builder= new AlertDialog.Builder(ReporteDanos.this);
                        builder.setTitle("Elige una opcion");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int seleccion) {
                                if(options[seleccion]=="Tomar foto"){
                                    openCamera();
                                }
                                else{
                                    if(options[seleccion]=="Elegir de Galería"){
                                        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        intent.setType("image/*");
                                        startActivityForResult(intent.createChooser(intent, "Seleccione app de imagen"),SELECT_PICTURE);

                                    }else
                                    {
                                        if(options[seleccion]=="Cancelar"){
                                            dialog.dismiss();
                                            imageView.setImageResource(R.drawable.logo);
                                        }
                                    }
                                }
                            }


                        });
                        builder.show();


                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton

    public void MensajeOK(String msg){
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        ;};
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if(resultCode==RESULT_OK){
                    opcion=PHOTO_CODE;
                    String dir=Environment.getExternalStorageDirectory()+ File.separator+MEDIA_DIRECTORY
                            +File.separator+TEMPORAL_PICTURE_NAME;
                    ale=dir;
                    decodeBitmap(dir);

                }
                break;
            case SELECT_PICTURE:
                if(resultCode==RESULT_OK){
                    opcion=SELECT_PICTURE;
                    Uri path=data.getData();
                    ale=getPath(path);
                    imageView.setImageURI(path);


                }

        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        imageView.setImageBitmap(bitmap);

    }


    private void openCamera() {
        File file= new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path=Environment.getExternalStorageDirectory()+ File.separator+MEDIA_DIRECTORY
                +File.separator+TEMPORAL_PICTURE_NAME;

        File newFile= new File(path);
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }

}//fin clase