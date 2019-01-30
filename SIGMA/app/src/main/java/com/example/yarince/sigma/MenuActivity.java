package com.example.yarince.sigma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewMenu;

    ArrayList<BotonMenu> listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_menu);


        mRecyclerViewMenu = (RecyclerView) findViewById(R.id.dashboard_rv_activity_dashboard);
        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(this, 2));

        listMenu = new ArrayList<>();

        llenarItemMenu();

        //Instanciamos el AdapterMenu
        AdapterMenu adapterMenu = new AdapterMenu(listMenu);
        mRecyclerViewMenu.setAdapter(adapterMenu);

    }

    private void llenarItemMenu() {
        listMenu.add(new BotonMenu("ASISTENCIA", "Control de asistencia de cuadrillas", R.drawable.ic_art_track_black_24dp, R.drawable.circle_background_blue));
        listMenu.add(new BotonMenu("ABASTECIMIENTO", "Control de abastecimiento de vehiculos", R.drawable.ic_directions_car_black_24dp, R.drawable.circle_background_yellow));
        listMenu.add(new BotonMenu("TAREA", "Ver tareas asignadas", R.drawable.ic_assignment_black_24dp, R.drawable.circle_background_green));
        listMenu.add(new BotonMenu("EVALUACIONES", "Control de asistencia de cuadrillas", R.drawable.ic_assignment_turned_in_black_24dp, R.drawable.circle_background_purple));
    }
}
