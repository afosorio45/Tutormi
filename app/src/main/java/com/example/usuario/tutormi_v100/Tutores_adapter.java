package com.example.usuario.tutormi_v100;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by usuario on 2/11/2017.
 */

public class Tutores_adapter extends RecyclerView.Adapter<Tutores_adapter.ViewHolder>{
    private static final String DEBUG_TAG = "Tutores Adapter";

    public Context context;
    public ArrayList<Tutor> tutoresLista;

    public Tutores_adapter(Context context, ArrayList<Tutor> tutoresLista) {
        this.context = context;
        this.tutoresLista = tutoresLista;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String name = tutoresLista.get(position).getName();
        int color = tutoresLista.get(position).getColorResource();
        TextView initial = viewHolder.initial;
        TextView nameTextView = viewHolder.name;
        nameTextView.setText(name);
        initial.setBackgroundColor(color);
        initial.setText(Character.toString(name.charAt(0)));
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    public void animateCircularDelete(final View view, final int list_position) {
        int centerX = view.getWidth();
        int centerY = view.getHeight();
        int startRadius = view.getWidth();
        int endRadius = 0;
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.d(DEBUG_TAG, "onAnimationEnd for Edit adapter position " + list_position);
                Log.d(DEBUG_TAG, "onAnimationEnd for Edit TutordId " + getItemId(list_position));

                view.setVisibility(View.INVISIBLE);
                tutoresLista.remove(list_position);
                notifyItemRemoved(list_position);
            }
        });
        animation.start();
    }

    /*
    En caso de ser necesario estos 3 metodos
     */

    @Override
    public int getItemCount() {
        if (tutoresLista.isEmpty()) {
            return 0;
        } else {
            return tutoresLista.size();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.tutores_view_holder, viewGroup, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView initial;
        private TextView name;
        private TextView ahorro;
        private TextView meta;
        private ProgressBar progressBar;

        public ViewHolder(View v) {
            super(v);
            initial = (TextView) v.findViewById(R.id.initial);
            name = (TextView) v.findViewById(R.id.name);
            /*

            ahorro = (TextView) v.findViewById(R.id.txt_ahorrado);
            meta = (TextView) v.findViewById(R.id.txt_meta);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBarMoney);
            */

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int requestCode = getAdapterPosition();
                    String name = tutoresLista.get(requestCode).getName();
                    int color = tutoresLista.get(requestCode).getColorResource();
                    ActivityCompat options;
                    Activity act = (AppCompatActivity) context;
                    /*


                    Pair<View, String> p4 = Pair.create((View) meta, MainActivity.TRANSITION_META);
                    Pair<View, String> p5 = Pair.create((View) progressBar, MainActivity.TRANSITION_PROGRESSBAR);

                    ActivityOptionsCompat options;
                    Activity act = (AppCompatActivity) context;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, p1, p2, p3, p4, p5);
                    int requestCode = getAdapterPosition();
                    String name = natillerasList.get(requestCode).getName();
                    int color = natillerasList.get(requestCode).getColorResource();

                    Log.d(DEBUG_TAG, "SampleMaterialAdapter itemView listener for Edit adapter position " + requestCode);

                /*
                Agregar codigo para redireccionar a pantalla que muestra detalles de la natillera

                    Intent transitionIntent = new Intent(context, DetallesActivity.class);
                    transitionIntent.putExtra("nombre", name);
                    transitionIntent.putExtra("inicial", Character.toString(name.charAt(0)));
                    transitionIntent.putExtra("color", color);
                    ((AppCompatActivity) context).startActivityForResult(transitionIntent, requestCode, options.toBundle());
                    */
                    Intent transitionIntent = new Intent(context, DetallesTutor_activity.class);
                    transitionIntent.putExtra("nombre", name);
                    transitionIntent.putExtra("inicial", Character.toString(name.charAt(0)));
                    transitionIntent.putExtra("color", color);
                    act.startActivityForResult(transitionIntent, requestCode);
                }
            });
        }
    }
}
