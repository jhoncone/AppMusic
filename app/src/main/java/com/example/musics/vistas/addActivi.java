package com.example.musics.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musics.R;
import com.example.musics.controlador.listaActivisi;
import com.example.musics.model.Disco;

import java.util.Random;


public class addActivi extends AppCompatActivity {
    EditText eartist,eescrip,eanio,eprice,eimagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

         eartist=(EditText)findViewById(R.id.txtArtista);
         eescrip =(EditText)findViewById(R.id.txtDescri);
        eanio =(EditText)findViewById(R.id.txtAnio);
       eprice=(EditText) findViewById(R.id.txtPrecio);
        eimagens=(EditText)findViewById(R.id.txtImags);
        Button btnadd=findViewById(R.id.btnAdd);

        String tipo=getIntent().getStringExtra("tipo");


    if(tipo.equals("Modificar")){
        String artista=getIntent().getStringExtra("artista");
        String descrip=getIntent().getStringExtra("descripcion");
        int anio=getIntent().getIntExtra("año",0);
        double precio=getIntent().getDoubleExtra("precio",0.0);

        eartist.setText(artista);
        eescrip.setText(descrip);
        eanio.setText(String.valueOf(anio));
        eprice.setText(String.valueOf(precio));

        btnadd.setText("Guardar cambios");


        btnadd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                int i=getIntent().getIntExtra("i",-1);
                int imagen=getIntent().getIntExtra("imagen",-1);
                Disco disco=new Disco(eimagens.getText().toString(),eartist.getText().toString(),eescrip.getText().toString(),
                    Integer.parseInt(eanio.getText().toString()) , Double.parseDouble(eprice.getText().toString()),imagen);


                listaActivisi.discos.set(i,disco);
                listaActivisi.adapter.notifyDataSetChanged();
                Intent intent=new Intent(addActivi.this,listaActivisi.class);
                startActivity(intent);




            }
        });


    }


    else if(tipo.equals("Agregar")){
        btnadd.setText("Agregar");
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String artis;
                artis=eartist.getText().toString();
                int resId[]={R.drawable.imageneg,R.drawable.imagd,R.drawable.imagend,R.drawable.imagedd,R.drawable.imagenss,R.drawable.imagendaf,R.drawable.imags};
                Random rand = new Random();
                int index = rand.nextInt((resId.length- 1) - 0 + 1) + 0;

                Disco disco=new Disco(eimagens.getText().toString(),artis,eescrip.getText().toString(),
                        Integer.parseInt(eanio.getText().toString()),Double.parseDouble(eprice.getText().toString()),resId[index]);
                listaActivisi.discos.add(disco);
                listaActivisi.adapter.notifyDataSetChanged();

                listaActivisi.disc=disco;

                listaActivisi.adapter.notifyDataSetChanged();
                //Toast.makeText(v.getContext(),"hol",Toast.LENGTH_SHORT).show();

           eartist.setText("");
            eescrip.setText("");
           eanio.setText("");
           eprice.setText("");
           eimagens.setText("");
           // Intent intent=new Intent(addActivi.this,listaActivisi.class);
            //startActivity(intent);

                startActivity(new Intent(v.getContext(), listaActivisi.class));

            }
        });
    }


    }
}