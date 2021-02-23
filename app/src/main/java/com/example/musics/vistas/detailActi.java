package com.example.musics.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musics.R;
import com.example.musics.model.Disco;
import com.squareup.picasso.Picasso;

public class detailActi extends AppCompatActivity {
    private ImageView imgItemDetail;
    private TextView artistDetail, descriDetail,anioDetail,pricDetail;

    private Disco itemDetail;


    private String domain_image="http://localhost/servicio1040/drawable/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        initViews();
        initValues();

    }
    private void initViews() {
        imgItemDetail = findViewById(R.id.images);
        artistDetail = findViewById(R.id.txtArtistas);
        descriDetail= findViewById(R.id.txtDescris);
        pricDetail=findViewById(R.id.txtPrice);
        anioDetail=findViewById(R.id.txtAnio);

    }

    private void initValues(){
        itemDetail = (Disco) getIntent().getExtras().getSerializable("itemDetail");

        artistDetail.setText(itemDetail.getArtista());
        descriDetail.setText(itemDetail.getDescripcion());
      int anio=itemDetail.getAño();
      String an=String.valueOf(anio);
        anioDetail.setText(an);
        double pric=itemDetail.getPrecio();
        String pri=String.valueOf(pric);
       pricDetail.setText("$"+pri);
        Picasso.get().load(domain_image+itemDetail.getImagen())
                .into(imgItemDetail);

    }
}