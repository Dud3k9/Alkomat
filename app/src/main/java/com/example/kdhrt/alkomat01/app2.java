package com.example.kdhrt.alkomat01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class app2 extends AppCompatActivity {

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
        promil.setText(""+(alkomat.policzP(procent,ml,ilosc,plec,masa))+"‰o");
    }
}
