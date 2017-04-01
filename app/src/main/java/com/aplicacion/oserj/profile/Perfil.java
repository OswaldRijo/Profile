package com.aplicacion.oserj.profile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Map;

public class Perfil extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    FirebaseDatabase firebaseDatabase;
    Button buttonSistema, buttonLocalizacion,buttonConfig;
    private String user;
    private TextView usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        user = this.getIntent().getStringExtra("usuario");
        usuario = (TextView) findViewById(R.id.usuario);
        usuario.setText(user);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bienvenido " + "");
        toolbar.setTitleTextColor(Color.GRAY);
        buttonConfig = (Button) findViewById(R.id.cuenta);
        buttonLocalizacion = (Button) findViewById(R.id.localizacion);
        buttonConfig.setOnClickListener(this);
        buttonLocalizacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==buttonConfig){
            Intent i = new Intent(Perfil.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        if(v==buttonLocalizacion){
            Intent i = new Intent(Perfil.this, MapsActivity.class);
            i.putExtra("usuario",user);
            startActivity(i);
            finish();
        }
    }
}
