package com.example.usuario.tutormi_v100;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listado_materias extends AppCompatActivity {

    private ListView listView;

    private String Materias[] = new String[]{"Fisica","Matematicas","Ingles","Lectura"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_materias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.miLista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_expandable_list_item_1,Materias);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int posicion = i;

                String itemValue = (String) listView.getItemAtPosition(posicion);

                Intent intent = new Intent(getApplicationContext(),Detalle_materias.class);

                intent.putExtra("materias",Materias[posicion]);



                startActivity(intent);
            }
        });
    }

}
