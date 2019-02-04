package com.example.yarince.sigma;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MenuDesplegable2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    private RecyclerView mRecyclerViewMenu;

    ArrayList<BotonMenu> listMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_desplegable2);


        mRecyclerViewMenu = (RecyclerView) findViewById(R.id.dashboard_rv_activity_dashboard);
        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(this, 2));

        listMenu = new ArrayList<>();

        llenarItemMenu();

        //Instanciamos el AdapterMenu
        AdapterMenu adapterMenu = new AdapterMenu(listMenu);
        mRecyclerViewMenu.setAdapter(adapterMenu);


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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_desplegable2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment mFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_accesos_directos) {
            mFragment = new AccesosRapidosFragment();
            fragmentSeleccionado=true;
//            Intent intent = new Intent(this,MenuDesplegable2.class);
//            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,AbastecimientoOtrosActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        if(fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_menu,mFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




private void llenarItemMenu() {
        listMenu.add(new BotonMenu("ASISTENCIA", "Control de asistencia de cuadrillas", R.drawable.ic_art_track_black_24dp, R.drawable.circle_background_blue));
        listMenu.add(new BotonMenu("ABASTECIMIENTO", "Control de abastecimiento de vehiculos", R.drawable.ic_directions_car_black_24dp, R.drawable.circle_background_yellow));
        listMenu.add(new BotonMenu("TAREA", "Ver tareas asignadas", R.drawable.ic_assignment_black_24dp, R.drawable.circle_background_green));
        listMenu.add(new BotonMenu("EVALUACIONES", "Control de asistencia de cuadrillas", R.drawable.ic_assignment_turned_in_black_24dp, R.drawable.circle_background_purple));
        }
}