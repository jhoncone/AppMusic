package com.example.musics.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musics.R;
import com.example.musics.vistas.homeactivi;

import SQLite.AdminSQLiteOpenHelper;

public class loginactivi extends AppCompatActivity {
Button btnLogin,btnRegister;
EditText eUser,ePasword;
ImageView images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivi);
        btnLogin = (Button) findViewById(R.id.btnLogi);
        btnRegister = (Button) findViewById(R.id.btnRegis);
        eUser=(EditText)findViewById(R.id.e_user);
        ePasword=(EditText)findViewById(R.id.epassword);
        images=(ImageView)findViewById(R.id.imageView2);
        images.setImageResource(R.drawable.imags);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(v.getContext(),"user",null,1);

                SQLiteDatabase baseDatos=admin.getWritableDatabase();

               String usuari=eUser.getText().toString();
               String pasword=ePasword.getText().toString();


                if(!usuari.isEmpty()&&!pasword.isEmpty()){

                    Cursor fila=baseDatos.rawQuery
                            ("select usuario,password from users where password=" + pasword ,null);
                                 //   ("select password from users where usuario=" + usuari,null);
                    if(fila.moveToFirst()) {
                        String eus=fila.getString(0);
                        String usu=fila.getString(1);


                        if(eus.equals(usuari)&&usu.equals(pasword)){
                            baseDatos.close();
                            Toast.makeText(v.getContext(),"usuario encontrado: "+eus,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(loginactivi.this, homeactivi.class);

                            i.putExtra("USERNAME", eus);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(v.getContext(),"digite correctamente ambos datos del usuario",Toast.LENGTH_SHORT).show();
                            baseDatos.close();
                        }

                    }

                    else{
                       Toast.makeText(v.getContext(),"No hay usuario digitado",Toast.LENGTH_SHORT).show();
                        baseDatos.close();
                    }



                }
                else {
                    Toast.makeText(v.getContext(), "Digite datos o Registrese", Toast.LENGTH_SHORT).show();
                    eUser.setText("");
                    ePasword.setText("");
                }
            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar(v);
            }
        });

    }


    public void Registrar(View view){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"user",null,1);

        SQLiteDatabase baseDatos=admin.getWritableDatabase();

        String password=ePasword.getText().toString();
        String usuario=eUser.getText().toString();

        if(!password.isEmpty()&&!usuario.isEmpty()){
            ContentValues registro=new ContentValues();
            registro.put("password",password);
            registro.put("usuario",usuario);

            baseDatos.insert("users",null,registro);
            baseDatos.close();
            eUser.setText("");
            ePasword.setText("");


            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this,"DEbes llenar ambos campos",Toast.LENGTH_SHORT).show();

        }
    }


}
