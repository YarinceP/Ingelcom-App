package com.example.yarince.sigma;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

import java.util.Calendar;
import java.util.Map;

public class Catering extends AppCompatActivity implements View.OnClickListener {

    Button mBotonFecha;
    ImageButton imageButtonFotoKm;
    ImageButton imageButtonFotoBomba;
    EditText mEditTextFecha;
    private int dia, mes, ano;

    private MagicalPermissions magicalPermissions;
    private final static int RESIZE_PHOTO_PIXELS_PERCENTAGE = 30;
    private MagicalCamera magicalCamera;
    private ImageView imageViewFotoKm;
    private ImageView imageViewFotoBomba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_abastecimiento);
        //Mostra la flecha para volver atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBotonFecha = (Button) findViewById(R.id.btnFecha);
        mEditTextFecha = (EditText) findViewById(R.id.etFecha);
        mBotonFecha.setOnClickListener(this);

        imageButtonFotoKm = (ImageButton) findViewById(R.id.img_btnFotoKm);
        imageButtonFotoBomba = (ImageButton) findViewById(R.id.img_btnFotoBomba);

        imageViewFotoKm = (ImageView) findViewById(R.id.fotoKm);
        imageViewFotoBomba = (ImageView) findViewById(R.id.fotoBomba);
        //imageViewFotoKm = (ImageView) findViewById(R.id.fotoKm2);

        imageButtonFotoKm.setOnClickListener(this);
        imageButtonFotoBomba.setOnClickListener(this);

        String[] permissions = new String[]{

                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };
        magicalPermissions = new MagicalPermissions(this, permissions);
        //Instanciamos la camara a traves del constructor Magical cammera

        magicalCamera = new MagicalCamera(this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);


        //Establecemos la fecha actual
        final Calendar calendar = Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        //Mostramos en el mEditTextFecha la fecha actual
        mEditTextFecha.setText(dia + "/" + (mes + 1) + "/" + ano);

    }


    @Override
    public void onClick(View v) {

        if (v == mBotonFecha) {
            final Calendar calendar = Calendar.getInstance();
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH);
            ano = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mEditTextFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, ano, mes, dia);
            datePickerDialog.show();

        }

        if (v == imageButtonFotoBomba) {
            //pressed(v);
            magicalCamera.takePhoto();
        }
        if (v == imageButtonFotoKm) {

            //pressed(v);
            magicalCamera.takePhoto();
        }
    }

    //Un metodo sobre escrito

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Map<String, Boolean> map = magicalPermissions.permissionResult(requestCode, permissions, grantResults);
        for (String permission : map.keySet()) {
            Log.d("PERMISSIONS", permission + "was" + map.get(permission));
        }

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {//La peticion de la foto ha ido bien

            if (imageButtonFotoKm.isClickable()) {//p

                magicalCamera.resultPhoto(requestCode, resultCode, data);
                imageViewFotoKm.setImageBitmap(magicalCamera.getPhoto());
                imageButtonFotoKm.setBackground(getResources().getDrawable(R.drawable.circle_background_green));
                String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "Km", "FOTOSINGELCOM", MagicalCamera.JPEG, true);
            } else {

            }


        }


    }

    private void pressed(View v) {
        switch (v.getId()) {
            case R.id.img_btnFotoKm:


        }
    }


    //Metodo que nos hace volver al activity anterior
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


}
