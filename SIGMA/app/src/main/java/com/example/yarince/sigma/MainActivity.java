package com.example.yarince.sigma;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private CardView attendanceCard, cateringCard, taskCard;
    private RecyclerView mRecyclerViewMenu;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<BotonMenu> listMenu;

    private Button mBotonInicio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBotonInicio=(Button)findViewById(R.id.inicio_sesion_activity_main_btn);
        mBotonInicio.setOnClickListener(this);

//        mRecyclerViewMenu =(RecyclerView)findViewById(R.id.dashboard_rv_activity_dashboard);
//        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(this,2));
//
//        listMenu=new ArrayList<>();
//
//        llenarItemMenu();
//
//        //Instanciamos el AdapterMenu
//        AdapterMenu adapterMenu= new AdapterMenu(listMenu);
//        mRecyclerViewMenu.setAdapter(adapterMenu);
//


//        attendanceCard = (CardView) findViewById(R.id.attendance_card_dashboard);
//        cateringCard = (CardView) findViewById(R.id.catering_card_dashboard);
//        taskCard = (CardView) findViewById(R.id.task_card_dashboard);
//
//        //Click Listener para las CardView
//        attendanceCard.setOnClickListener(this);
//        cateringCard.setOnClickListener(this);
//        taskCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Catering.class);
        startActivity(intent);
    }


//    @Override
//    public void onClick(View v) {
//        Intent intent;
//
//        switch (v.getId()) {
//            case R.id.attendance_card_dashboard:
//                intent = new Intent(this, Attendance.class);
//                startActivity(intent);
//                break;
//            case R.id.catering_card_dashboard:
//                intent = new Intent(this, Catering.class);
//                startActivity(intent);
//                break;
//            case R.id.task_card_dashboard:
//                intent = new Intent(this, Task.class);
//                startActivity(intent);
//            default:
//                break;
//        }

 //   }
}
