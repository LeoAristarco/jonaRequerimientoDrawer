package com.example.jonatan.jonanavigationdrawer.vistas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jonatan.jonanavigationdrawer.R;

public class ActivityDescripcionNoticia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_noticia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recibirDescripcionDeNoticias();

    }

    private void recibirDescripcionDeNoticias() {
        Bundle bundle = getIntent().getExtras();
        String numero =bundle.getString("numero");

        TextView out = (TextView)findViewById(R.id.idNumero);
        out.setText(numero);
    }

}
