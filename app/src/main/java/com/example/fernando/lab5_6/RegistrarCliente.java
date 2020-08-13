package com.example.fernando.lab5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarCliente extends AppCompatActivity {

    private EditText et_nombre, et_direccion, et_email, et_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        et_nombre = (EditText)findViewById(R.id.txt_nombre2);
        et_direccion = (EditText)findViewById(R.id.txt_direccion);
        et_email = (EditText)findViewById(R.id.txt_email);
        et_numero = (EditText)findViewById(R.id.txt_numero);
    }
    //Metodo del boton Registrar
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String direccion = et_direccion.getText().toString();
        String email = et_email.getText().toString();
        String numero = et_numero.getText().toString();

        if(!nombre.isEmpty() && !direccion.isEmpty() && !email.isEmpty() && !numero.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put ("nombre", nombre);
            registro.put("direccion", direccion);
            registro.put("email", email);
            registro.put("numero", numero);

            BaseDeDatos.insert("personas", null, registro);

            BaseDeDatos.close();
            et_nombre.setText("");
            et_direccion.setText("");
            et_email.setText("");
            et_numero.setText("");

            Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    //Metodo del boton Atras
    public void Atras_NC(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}