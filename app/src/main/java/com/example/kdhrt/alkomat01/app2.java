package com.example.kdhrt.alkomat01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class app2 extends AppCompatActivity {

    String wynik;
    String path= "sdcard/Android/data/Alkomat";
    private final int MEMORY_ACESS=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        RadioButton m = findViewById(R.id.mezczyzna);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(app2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE));
        else
            ActivityCompat.requestPermissions(app2.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MEMORY_ACESS);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MEMORY_ACESS:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }else
                    Toast.makeText(getApplicationContext(),"brak pozwolenia",Toast.LENGTH_LONG).show();
        }
    }

    public void oblicz(View view) {
        RadioButton m = findViewById(R.id.mezczyzna);
        RadioButton k = findViewById(R.id.kobieta);
        TextView promil = findViewById(R.id.promil);
        EditText masaS = findViewById(R.id.waga);
        EditText mlS = findViewById(R.id.ml);
        EditText procentS = findViewById(R.id.procent);
        EditText iloscS = findViewById(R.id.ilosc);
        // EditText nazwaS = findViewById(R.id.nazwa);
        boolean plec;

        if(m.isChecked())
            plec=true;
        else
            plec=false;
        double masa = Double.parseDouble(String.valueOf(masaS.getText()));
        double ml = Double.parseDouble(String.valueOf(mlS.getText()));
        double procent = Double.parseDouble(String.valueOf(procentS.getText()));
        double ilosc = Double.parseDouble(String.valueOf(iloscS.getText()));
        //String nazwa=String.valueOf(nazwaS.getText());

        Alkomat alkomat=new Alkomat();

        wynik=""+(alkomat.policzP(procent,ml,ilosc,plec,masa))+"‰";
        promil.setText(wynik);
    }

    public void zapisz(View view) {
        if(wynik!=null) {
            createDir();
            createFile();
        }
    }

    public void createDir(){
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();

        }
    }

    public void createFile() {
        File file=new File(path+"/data.txt");
        if(!file.exists()){
            try {
                FileOutputStream fos= new FileOutputStream(file);
                OutputStreamWriter writer=new OutputStreamWriter(fos);
                writer.append(wynik);
                writer.close();
                fos.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
