package com.aplicacion.oserj.profile;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.aplicacion.oserj.profile.Firebase.Coordenadas;
import com.aplicacion.oserj.profile.Firebase.FirebaseReferences;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    EditText editTextLatitud;
    EditText editTextLongitud;
    static float longitud;
    static float latitud;
    public static String usuario;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        usuario = this.getIntent().getStringExtra("usuario");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public boolean onSupportNavigateUp(){
        //code it to launch an intent to the activity you want
        Intent intent = new Intent(MapsActivity.this,Perfil.class);
        startActivity(intent);
        finish();
        return true;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        editTextLatitud = (EditText) findViewById(R.id.latitud);
        editTextLongitud = (EditText) findViewById(R.id.longitud);


        LatLng sydney = new LatLng(0, 0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        final DatabaseReference myRef = database.getReference(FirebaseReferences.USUARIOID).child(FirebaseReferences.COORDENADAS);//.child(FirebaseReferences.CARROS);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Coordenadas coordenadas = dataSnapshot.getValue(Coordenadas.class);
                latitud = coordenadas.getLatitud();
                longitud = coordenadas.getLongitud();
                Log.d("Database", dataSnapshot.toString());
                editTextLatitud.setText(String.valueOf(latitud));
                editTextLongitud.setText(String.valueOf(longitud));
                LatLng sydney = new LatLng(latitud, longitud);
                mMap.clear();

                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13.0f));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error",databaseError.getMessage());
            }
        });
        myRef.child("booleana").setValue("true");

    }
}
