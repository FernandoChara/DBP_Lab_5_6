package com.example.fernando.lab5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BorrarCliente extends AppCompatActivity {

    private EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_cliente);

        et_nombre = (EditText)findViewById(R.id.txt_nombreE);
    }

    //Método para eliminar una persona
    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_nombre.getText().toString();

        if(!codigo.isEmpty()){

            int cantidad = BaseDeDatos.delete("personas", "nombre='" +codigo+"'", null);
            BaseDeDatos.close();

            et_nombre.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Persona eliminada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La persona no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el nombre de la persona", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo del boton Atras
    public void Atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}