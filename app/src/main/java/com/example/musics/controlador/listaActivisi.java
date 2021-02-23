package com.example.musics.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musics.R;
import com.example.musics.adaptador.DiscoAdapters;
import com.example.musics.model.Disco;
import com.example.musics.vistas.detailActi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit_data.RetrofitApiService;
import retrofit_data.RetrofitClient;
public class listaActivisi extends AppCompatActivity implements DiscoAdapters.RecyclerDiscoClick,SearchView.OnQueryTextListener {


    public static DiscoAdapters adapter;
    private RecyclerView ryDisco;
    private SearchView svSearch;
    public static ArrayList<Disco> discos=new ArrayList<>();
    public  static Disco disc;
    private RetrofitApiService retrofitApiService;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ryDisco=findViewById(R.id.rv_Discos);
        svSearch=findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
       ryDisco.setLayoutManager(layoutManager);
        getItems();
        initListener();



    }




private void getItems(){
    retrofitApiService.getDiscos().enqueue(new Callback<List<Disco>>() {
        @Override
        public void onResponse(Call<List<Disco>> call, Response<List<Disco>> response){
            discos = (ArrayList<Disco>) response.body();

            if(disc!=null){
                discos.add(disc);
            }

          adapter=new DiscoAdapters( discos,listaActivisi.this);

           ryDisco.setAdapter(adapter);

        }

        @Override
        public void onFailure(Call<List<Disco>> call, Throwable t) {
Toast.makeText(listaActivisi.this,"Error: "+t.getMessage(),Toast.LENGTH_LONG).show();
        }
    });

}

    private  void initListener(){
        svSearch.setOnQueryTextListener(this);
    }





    @Override
    public void itemClick(Disco item) {
        Intent intent=new Intent(listaActivisi.this, detailActi.class);
        intent.putExtra("itemDetail",  item);
        startActivity(intent);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       adapter.filter(newText);

        return false;
    }



}
