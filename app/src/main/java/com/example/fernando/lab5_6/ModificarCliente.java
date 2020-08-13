package com.example.fernando.lab5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarCliente extends AppCompatActivity {

    private EditText et_nombre, et_direccion, et_email, et_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);

        et_nombre = (EditText)findViewById(R.id.txt_nombreM);
        et_direccion = (EditText)findViewById(R.id.txt_direccionM);
        et_email = (EditText)findViewById(R.id.txt_emailM);
        et_numero = (EditText)findViewById(R.id.txt_numeroM);

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

    //Metodo para modificar datos de un Cliente
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = et_nombre.getText().toString();
        String direccion = et_direccion.getText().toString();
        String email  = et_email.getText().toString();
        String numero = et_numero.getText().toString();

        if(!name.isEmpty() && !direccion.isEmpty() && !email.isEmpty() && !numero.isEmpty()){
            ContentValues registro =new ContentValues();
            registro.put("nombre", name);
            registro.put("direccion", direccion);
            registro.put("email", email);
            registro.put("numero", numero);

            int cantidad = BaseDeDatos.update("personas", registro, "nombre='" +name+"'", null);
            BaseDeDatos.close();

            if(cantidad == 1){
                Toast.makeText(this, "Cliente modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"El cliente no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo del boton Atras
    public void Atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}