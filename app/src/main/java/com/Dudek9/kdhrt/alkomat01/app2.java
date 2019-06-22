package com.Dudek9.kdhrt.alkomat01;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalTime;

public class app2 extends AppCompatActivity {

    SharedPreferences utils;
    String wynik;
    double wynikD;
    double suma=0;
    String path= "sdcard/Android/data/Alkomat";
    TextView promil1;
    TextView promil;
    TextView time;
    private final int MEMORY_ACESS=5;
    LocalTime localTime = null;
    NotificationCompat.Builder notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        utils=getSharedPreferences("wyniki", Activity.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        RadioButton m = findViewById(R.id.mezczyzna);
        promil1=findViewById(R.id.promil1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        if(ActivityCompat.shouldShowRequestPermissionRationale(app2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE));
        else
            ActivityCompat.requestPermissions(app2.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MEMORY_ACESS);


        promil1.setText(""+load()+"‰");
        suma=load();

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
        promil = findViewById(R.id.promil);
        EditText masaS = findViewById(R.id.waga);
        EditText mlS = findViewById(R.id.ml);
        EditText procentS = findViewById(R.id.procent);
        EditText iloscS = findViewById(R.id.ilosc);
        // EditText nazwaS = findViewById(R.id.nazwa);
        boolean plec;

        if((!m.isChecked()&&!k.isChecked())||
                promil.getText().toString().equals("")||
                masaS.getText().toString().equals("")||
                mlS.getText().toString().equals("")||
                procentS.getText().toString().equals("")||
                iloscS.getText().toString().equals("")) {
            Toast.makeText(this, "Podaj wszystkie wartosci", Toast.LENGTH_SHORT).show();
        }else {
            if (m.isChecked())
                plec = true;
            else
                plec = false;
            double masa = Double.parseDouble(String.valueOf(masaS.getText()));
            double ml = Double.parseDouble(String.valueOf(mlS.getText()));
            double procent = Double.parseDouble(String.valueOf(procentS.getText()));
            double ilosc = Double.parseDouble(String.valueOf(iloscS.getText()));
            //String nazwa=String.valueOf(nazwaS.getText());

            Alkomat alkomat = new Alkomat();

            wynikD = alkomat.policzP(procent, ml, ilosc, plec, masa);
            wynik = "" + (alkomat.policzP(procent, ml, ilosc, plec, masa)) + "‰";
            promil.setText(wynik);
        }
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
            try {
                FileOutputStream fos= new FileOutputStream(file,true);
                OutputStreamWriter writer=new OutputStreamWriter(fos);
                writer.append(wynik);
                writer.close();
                fos.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

    public void add(View view) {
        suma+=wynikD;
        promil1.setText(""+suma+"‰");
        save(suma);
        time=findViewById(R.id.czas);
        time.setText(""+time().toString()+"h");
        noificationBuild();
    }

    public void save(double suma){
        SharedPreferences.Editor editor=utils.edit();
        editor.putFloat("suma",(float)suma);
        editor.commit();
    }

    public float load(){
        float load=utils.getFloat("suma",0);
        return load;
    }

    public void clean(View view) {
        promil = findViewById(R.id.promil);
        time=findViewById(R.id.czas);
        suma=0;
        save(0);
        promil1.setText("0.0‰");
        promil.setText("0.0‰");
        time.setText("0.0h");
    }

    public LocalTime time(){
        float time= (float) (suma/0.15);

        int hours=(int)time;
        time-=hours;
        time*=100;
        float miutes=Math.round(time);
        miutes/=100;
        miutes*=60;
        miutes=Math.round(miutes);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localTime=LocalTime.of(hours,(int)miutes);
        }
        return localTime;
    }

    public void noificationBuild(){
        notification=new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Pozostały czas do wytrzeźwienia:")
                .setContentText(localTime.toString()+"h")
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notification.build());
    }
}

