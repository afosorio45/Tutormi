package com.example.usuario.tutormi_v100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by usuario on 3/11/2017.
 */

public class DetallesTutor_activity extends AppCompatActivity{

    private TextView initial;
    private TextView tutor_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tutor);

        Bundle bun = getIntent().getExtras();

        initial = (TextView) findViewById(R.id.initial);
        tutor_nombre = (TextView) findViewById(R.id.nombre);

        initial.setText(bun.getString("inicial"));
        initial.setBackgroundColor(bun.getInt("color"));
        tutor_nombre.setText(bun.getString("nombre"));
    }
}
