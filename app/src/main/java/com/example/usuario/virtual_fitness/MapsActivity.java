package com.example.usuario.virtual_fitness;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 9.971157;
    double lng = -84.129138;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        registerForContextMenu(fab4);

        OnclickDelFloatingActionButton(R.id.fab);
        OnclickDelFloatingActionButton(R.id.fab2);
        OnclickDelFloatingActionButton(R.id.fab3);
        OnclickDelFloatingActionButton(R.id.fab4);


    }

    public void OnclickDelFloatingActionButton(int ref) {

        // Ejemplo  OnclickDelButton(R.id.MiButton);
        // 1 Doy referencia al Button
        View view =findViewById(ref);
        FloatingActionButton miButton = (FloatingActionButton) view;
        //  final String msg = miButton.getText().toString();
        // 2.  Programar el evento onclick
        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.fab:
                        // acercar
                        mMap.animateCamera(CameraUpdateFactory.zoomIn());
                        break;

                    case R.id.fab2:
                        // alejar
                        mMap.animateCamera(CameraUpdateFactory.zoomOut());
                        break;

                    case R.id.fab3:
                        miUbicacion();
                        break;

                    case R.id.fab4:
                        openContextMenu(findViewById(R.id.fab4));
                        break;

                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelFloatingActionButton


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.fab4) {
            menu.setHeaderTitle("Tipos de mapa");
            menu.add(0, 1, 0, "Satelital");
            menu.add(0, 2, 0, "Terreno");
            menu.add(0, 3, 0, "Hibrido");
            menu.add(0, 4, 0, "Normal");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int opcionseleccionada = item.getItemId();
        switch (item.getItemId()) {
            case 1: mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); break;
            case 2: mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); break;
            case 3: mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); break;
            case 4: mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); break;
            default: break;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        pos_Gimnasios();
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(9.9961661, -84.1196966999999);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        LatLng sydney3 = new LatLng(10.0009657, -84.11567230000000);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("p"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        /*
        *  gim(int lat, int lon){
        *      LatLng sydney3 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("p"));
        *
        *  }
        *
        * */
    }

    public void locGim(double lat, double lon, String nombre){
        LatLng gimnasio = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(gimnasio).title(nombre).icon(BitmapDescriptorFactory.fromResource(R.drawable.gym)));

    }

    public void pos_Gimnasios(){
        locGim(9.9961661,-84.1196966999999,"Parque de los Angeles");
        locGim(10.0009657,-84.11567230000000,"Parque de las Embarazadas");
        locGim(9.993892,-84.114640,"Barrio del Carmen");
        locGim(9.993751501139073,-84.11025524139404,"Plaza la Puebla");
        locGim(9.997500,-84.109901,"Maria Auxiliadora");
        locGim(9.987509,-84.112317,"La Esperanza");
        locGim(9.981807,-84.112680,"Nispero 3");
        locGim(9.973397,-84.114607,"Los Lagos");
        locGim(9.965619,-84.117240,"las Flores");
        locGim(9.968747,-84.121628,"OVI#1");
        locGim(9.969143,-84.120730,"Area Deportiva Lagunilla");
        locGim(9.972887,-84.122578,"Real Santa Maria");
        locGim(9.976402,-84.123026,"La Pradera Silvestre");
        locGim(9.977611,-84.121603,"Arbol de Plata");
        locGim(9.970140,-84.132677,"Onix");
        locGim(9.973821,-84.140767,"Maria Ofelia");
        locGim(9.976244,-84.140125,"La Guaria");
        locGim(9.978623,-84.135670,"Casa Blanca");
        locGim(9.981907,-84.136975,"Portal del Valle");
        locGim(9.982618,-84.131398,"Vistas del Valle");
        locGim(9.981677,-84.131430,"San Bernardo");
        locGim(9.984556,-84.147298,"Aprovia");
        locGim(9.985535,-84.150605,"La Aurora");
        locGim(9.990897,-84.150551,"Areas Deportivas la Aurora");
        locGim(9.993334,-84.153949,"Abuelos Felices");
        locGim(9.995523,-84.152365,"Urbanización Jerez");
        locGim(9.995352,-84.148959,"Cafetos");
        locGim(9.992950,-84.147531,"Santa Catalina");
        locGim(9.992244,-84.144462,"La Victoria");
        locGim(9.995668,-84.145686,"Los Laureles");
        locGim(9.995164,-84.143543,"Santillana del Mar");
        locGim(9.992714,-84.142103,"Urbanización Aroñanzas");
        locGim(9.991232,-84.140571,"Malinches Oeste");
        locGim(9.988431,-84.141973,"Urbanización Vista Nosara");
        locGim(9.990162,-84.139904,"Malinches");
        locGim(9.990811,-84.137206,"Santa Cecilia");
        locGim(9.991994,-84.138984,"Berta Eugenia");
        locGim(9.988673420194980,-84.13558602333060,"San Francisco");
        locGim(9.986794,-84.137600,"La Misión");
        locGim(9.987483,-84.137669,"Dulce nombre de Jesus");
        locGim(9.984612,-84.135619,"La Cumbre");
        locGim(9.986076,-84.132402,"Monte Rosa");
        locGim(9.987423,-84.130077,"El Trebol");
        locGim(9.989494,-84.133445,"Urbanización las Hortencias");
        locGim(9.989633,-84.132142,"La Liliana");
        locGim(9.989699,-84.128843,"Urbanización la Liliana");
        locGim(9.993426,-84.136691,"Tenerife");
        locGim(9.991170,-84.125384,"Urbanización Verolis");
        locGim(9.993218,-84.124166,"La Esmeralda");
        locGim(9.995284,-84.126688,"San Francisco");
        locGim(9.985277423600856,-84.11495447158813,"Villa Paola");
        locGim(9.993349991076322,-84.12783980369568,"La Emilia");
        locGim(9.990898656752357,-84.12028670310974,"Bernardo Benavides");
        locGim(9.986365752024566,-84.11981463432312,"Urbanización Quesada");
        locGim(9.985812077209511,-84.12205696105957,"Doña Nidia");
        locGim(9.990782429066218,-84.1158127784729,"Los Adoquine");
        locGim(9.997597517636843,-84.12590861320496,"Residencial Cubujuqui");
        locGim(9.998294867545535,-84.12676692008972,"Cubujuqui");
        locGim(10.00058765897001,-84.12670254707336,"Los Mormones");
        locGim(10.000900406887519,-84.13327932357788,"Santa Ines");
        locGim(10.003007223157992,-84.13435220718384,"Santa Ines Casco");
        locGim(10.003767955772863,-84.13921236991882,"Doña Emilce");
        locGim(10.00374682433539,-84.14067149162292,"Villas del Boulevar");
        locGim(10.005770682034969,-84.14031475782394,"Urbanizaciòn la Coordillera");
        locGim(10.002823378917475,-84.14387941360474,"El Milenio");
        locGim(10.00532111266261,-84.14515614509583,"Calle del Rey");
        locGim(10.005888488660258,-84.14382040500641,"Cedric");
        locGim(10.008057610399067,-84.13960933685303,"Urbanización Maria Fernanda");
        locGim(10.00360101815326,-84.12610173225403,"Monte Bello");
        locGim(10.002944357496167,-84.12222594022751,"Polideportivo Fatima");
        locGim(10.006231872084376,-84.12660598754883,"El Claretiano");
        locGim(10.010177057045285,-84.1247820854187,"El Banco");
        locGim(10.009141638008508,-84.12345170974731,"Reall España");
        locGim(10.011381522933014,-84.1218638420105,"El Pino");
        locGim(10.010050273481847,-84.12025451660156,"Villa Maria");
        locGim(10.010916641891933,-84.12712097167969,"Vivi");
        locGim(10.01235460235194,-84.13064539432526,"CTP Mercedes Norte");
        locGim(10.012186611248596,-84.13161635398865,"Amaranto");
        locGim(10.015696434003242,-84.12482500076294,"Urbanización Coraico");
        locGim(10.005099233803502,-84.11940653623788,"Escuela de Fátima");
        locGim(9.97071670925205,-84.1497315914421,"Los Arcos");
    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Posición")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.pos)));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }

    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locListener);

    }

}
