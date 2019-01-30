package com.example.yarince.sigma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class Attendance extends AppCompatActivity {

    Spinner idTypeTask;
    EditText fechaCodigoTarea_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        idTypeTask = findViewById(R.id.idSpinnerTypeTask);

        ArrayAdapter<CharSequence> adapterTypeTaskList = ArrayAdapter.createFromResource(this,
                R.array.typeTask, android.R.layout.simple_list_item_activated_1);
        idTypeTask.setAdapter(adapterTypeTaskList);



        fechaCodigoTarea_et = findViewById(R.id.fechaCodigoTarea_et);

        Time fechaHoy = new Time(Time.getCurrentTimezone());
        fechaHoy.setToNow();
        int dia = fechaHoy.monthDay;
        int mes = fechaHoy.month;
        int ano = fechaHoy.year;
        mes = mes + 1;

        if (mes < 10) {
            fechaCodigoTarea_et.setText(ano + "0" + mes + "" + dia);
        } else {
            fechaCodigoTarea_et.setText(ano + "" + mes + "" + dia);
        }
        fechaCodigoTarea_et.setEnabled(false);


    }


}
