package com.example.musics.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musics.R;
import com.example.musics.controlador.listaActivisi;

public class homeactivi extends AppCompatActivity {
Button listar,add;
ImageView bgapp,clover;
Animation frombottom;
LinearLayout textSplash,texhome,menus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivi);

        add=(Button)findViewById(R.id.btnAdd);
        listar=(Button)findViewById(R.id.btnListar);

        String name=getIntent().getStringExtra("USERNAME");
       TextView textuser=(TextView)findViewById(R.id.textView);
        textuser.setText("Bienvenido "+name);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homeactivi.this, addActivi.class);
                intent.putExtra("tipo","Agregar");
                startActivity(intent);
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(v.getContext(), listaActivisi.class));
            }
        });
    }

}