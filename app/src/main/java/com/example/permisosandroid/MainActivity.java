package com.example.permisosandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Se escribira en el manifest, antes o despues de la etiqueta application
    // Permiso camara: <uses-permission android:name="android.permission.CAMERA" />
    // Sera necesario, una vez escrito en el manifest, solicitarselo al usuario.


    Button btnPermiso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPermiso = findViewById(R.id.btnPermiso);

        btnPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermiso();
            }
        });
    }

    private void solicitarPermiso(){

        // Comprobamos el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            realizarPermiso();
        } else {
            abrirCamaraToast();
        }
    }

    private void realizarPermiso(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            // el usuario rechazo los permisos antes
            Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_SHORT).show();
        } else {
            // pedir permisos | requestCode inventado
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 999);
        }
    }

    private void abrirCamaraToast(){
        Toast.makeText(this, "Abriendo camara", Toast.LENGTH_SHORT).show();
    }

    // metodo que se lanza para comprobar el permiso, metodo propio del lenguaje.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 999){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // se aceptaron los permisos
                abrirCamaraToast();
            }else{
                // permiso no encontrado
                Toast.makeText(this, "Permisos rechazados por primera vez", Toast.LENGTH_SHORT).show();
            }
        }
    }
}