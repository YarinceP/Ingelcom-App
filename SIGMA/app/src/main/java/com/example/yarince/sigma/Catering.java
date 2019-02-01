package com.example.yarince.sigma;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Map;

public class Catering extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

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


    //Campos del formulario Nuevo Abastecimientos
    EditText mIdVehiculoEditText, mPrecioEditText, mMontoAbastecidoEditText, mKilometrajeEditText, mTipoCombustibleEditText, mTipoMaquinariaEditText;
    Button mGuardarButton;
    ProgressDialog progreso;

    //Nos permitiran la conexion directamente con nuestro Api (Web Services)
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_abastecimiento);
        //Mostra la flecha para volver atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        mIdVehiculoEditText = (EditText) findViewById(R.id.id_vehiculo_et);
        mKilometrajeEditText = (EditText) findViewById(R.id.kilometraje_et_catering_activity);
        mMontoAbastecidoEditText = (EditText) findViewById(R.id.monto_abastecido_et_catering_activity);
        mPrecioEditText = (EditText) findViewById(R.id.precio_litro_et_catering_activity);
        mTipoCombustibleEditText = (EditText) findViewById(R.id.tipo_combustible_et_catering_activity);
        mTipoMaquinariaEditText = (EditText) findViewById(R.id.tipo_maquinaria_et_catering_activity);
        mGuardarButton = (Button) findViewById(R.id.btnGuardar);
        request = Volley.newRequestQueue(this);
        mGuardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });




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

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View vista=inflater.inflate(R.layout.form_abastecimiento,container,false);
//        mIdVehiculoEditText=(EditText)vista.findViewById(R.id.id_vehiculo_et);
//        mKilometrajeEditText=(EditText)vista.findViewById(R.id.kilometraje_et_catering_activity);
//        mMontoAbastecidoEditText=(EditText)vista.findViewById(R.id.monto_abastecido_et_catering_activity);
//        mPrecioEditText=(EditText)vista.findViewById(R.id.precio_litro_et_catering_activity);
//        mTipoCombustibleEditText=(EditText)vista.findViewById(R.id.tipo_combustible_et_catering_activity);
//        mTipoMaquinariaEditText=(EditText)vista.findViewById(R.id.tipo_maquinaria_et_catering_activity);
//        mGuardarButton=(Button)vista.findViewById(R.id.btnGuardar);
//
//        request= Volley.newRequestQueue(this);
//
//        mGuardarButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cargarWebService();
//            }
//        });
//
//        return vista;
//    }

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

            if (imageButtonFotoKm.isClickable()) {
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


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "No se guardaron los datos" + error.toString(), Toast.LENGTH_LONG);
        Log.i("ERROR: ", error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Se ha Registrado Correctamente", Toast.LENGTH_LONG).show();
        progreso.hide();
        mTipoMaquinariaEditText.setText("");
        mTipoCombustibleEditText.setText("");
        mKilometrajeEditText.setText("");
        mMontoAbastecidoEditText.setText("");
        mIdVehiculoEditText.setText("");
        mPrecioEditText.setText("");

    }

    private void cargarWebService() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Enviando Datos....");
        progreso.show();


        String url = "http://192.168.0.5/Api/wsJSONlogin.php?" +
                "id_vehiculo=" + mIdVehiculoEditText.getText().toString() +
                "&kilometraje=" + mKilometrajeEditText.getText().toString() +
                "&precio_combustible=" + mPrecioEditText.getText().toString() +
                "&monto_abastecido=" + mMontoAbastecidoEditText.getText().toString() +
                "&tipo_combustible=" + mTipoCombustibleEditText.getText().toString() +
                "&tipo_maquinaria=" + mTipoMaquinariaEditText.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }
}
