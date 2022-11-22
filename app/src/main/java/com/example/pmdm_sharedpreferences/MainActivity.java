package com.example.pmdm_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;
    private Button btnBorrar;
    private ImageButton btnEliminarNombre;
    private ImageButton btnEliminarEdad;

    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnEliminarNombre = findViewById(R.id.btnEliminarNombre);
        btnEliminarEdad = findViewById(R.id.btnEliminarEdad);

        sp= getSharedPreferences(Constantes.PERSONAS, MODE_PRIVATE); //el private no permite que nadie más lo toque

        //ESTO PARA COGERLO DEL SHARED
        //Como el bundle nos pide una clave para encontarlo pero también un default por si no lo encuentra
        String nombre = sp.getString(Constantes.NOMBRE, "");
        int edad = sp.getInt(Constantes.EDAD, -1);

        if(!nombre.isEmpty()){
            txtNombre.setText(nombre);
        }
        if(edad!= -1){
            txtEdad.setText(String.valueOf(edad));
        }

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnEliminarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Constantes.NOMBRE);
                editor.apply();
                txtNombre.setText("");
            }
        });

        btnEliminarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
                txtEdad.setText("");
            }
        });

        //ESTO PARA GUARDARLO EN EL SHARED
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constantes.NOMBRE, nombre);
                editor.putInt(Constantes.EDAD, edad);
                //Commit (deja parado el hilo de ejecucion hasta que se escriba (sincrono)) o
                // apply(hace un hilo independiente para que el programa siga, este suele ser lo comun si no vamos a leer despues de escribir(asincrono))
                // para cerrar y que escriba.
                editor.apply();
            }
        });

    }


}