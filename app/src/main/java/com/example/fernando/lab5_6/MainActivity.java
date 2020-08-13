package com.example.fernando.lab5_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Metodo del boton Nuevo Cliente
    public void NuevoCliente(View view){
        Intent nuevoCliente = new Intent(this, RegistrarCliente.class);
        startActivity(nuevoCliente);
    }

    public void buscandoCliente(View view){
        Intent Cliente = new Intent(this, BusquedaCliente.class);
        startActivity(Cliente);
    }

    public void borrandoCliente(View view){
        Intent Cliente = new Intent(this, BorrarCliente.class);
        startActivity(Cliente);
    }

    public void modificandoCliente(View view){
        Intent Cliente = new Intent(this, ModificarCliente.class);
        startActivity(Cliente);
    }

}