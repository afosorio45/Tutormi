package com.example.usuario.tutormi_v100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Tutores_activity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Tutores_adapter adapter;
    private int numero;
    public static final String TRANSITION_INITIAL = "initial_transition";
    public static final String TRANSITION_NAME = "name_transition";
    private ArrayList<Tutor> tutores = new ArrayList<>();
    private int[] colors;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutores);
        names = getResources().getStringArray(R.array.tutores_array);
        colors = getResources().getIntArray(R.array.initial_colors);

        initTutores();

        if (adapter == null) {
            adapter = new Tutores_adapter(this, tutores);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutores_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initTutores() {
        numero = (int) (Math.random() * 14) + 1;
        for (int i = 0; i < numero; i++) {
            Tutor tutor = new Tutor();
            tutor.setId((long) i);
            int aleat = (int) (Math.random() * 24);
            tutor.setName(names[aleat]);
            tutor.setColorResource(colors[i]);
            //Log.i(DEBUG_TAG, "Card created with id " + card.getId() + ", name " + card.getName() + ", color " + card.getColorResource());
            tutores.add(tutor);
        }
    }

}
