package com.example.yarince.sigma;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AbastecimientoOtrosActivity extends AppCompatActivity {

    Spinner mSpinnerTipoMaquinaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimiento_otros);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Spinner llenado de elementos
        mSpinnerTipoMaquinaria = findViewById(R.id.idTipoMaquinaria_sp_activity);
        ArrayAdapter<CharSequence> adapterTipoMaquinariaList = ArrayAdapter.createFromResource(this,
                R.array.maquinaria_tipo, android.R.layout.simple_list_item_activated_1);
        mSpinnerTipoMaquinaria.setAdapter(adapterTipoMaquinariaList);









        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //Metodo que nos hace volver al activity anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}
