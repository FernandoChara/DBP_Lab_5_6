package com.example.fernando.lab5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BusquedaCliente extends AppCompatActivity {

    private EditText et_nombre;
    private TextView et_direccion, et_email, et_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_cliente);

        et_nombre = (EditText)findViewById(R.id.txt_nombreB);
        et_direccion = (TextView)findViewById(R.id.txt_direccionB);
        et_email = (TextView)findViewById(R.id.txt_emailB);
        et_numero = (TextView)findViewById(R.id.txt_numeroB);


    }

    //Método para Buscar una persona
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = et_nombre.getText().toString();

        if(!name.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select direccion, email, numero from personas where nombre = '" +name+"'", null);

            if(fila.moveToFirst()){
                et_direccion.setText(fila.getString(0));
                et_email.setText(fila.getString(1));
                et_numero.setText(fila.getString(2));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this,"No existe la persona", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el nombre de la persona", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo del boton Atras
    public void Atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }


}