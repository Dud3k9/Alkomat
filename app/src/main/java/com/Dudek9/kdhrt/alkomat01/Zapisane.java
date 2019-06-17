package com.Dudek9.kdhrt.alkomat01;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Zapisane extends AppCompatActivity {

    String path = "sdcard/Android/data/Alkomat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapisane);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        ListView lv = findViewById(R.id.listViev);
        ArrayList<String> ar = new ArrayList<>();
        File file = new File(path+"/data.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            int tmp;

            while ((tmp=isr.read())!=-1) {
                StringBuilder sb = new StringBuilder();
                while(tmp!=8240&&tmp!=-1) {
                    sb.append(((char) tmp));
                    tmp=isr.read();
                }
                ar.add(sb.toString()+(char)(8240));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,ar);
        lv.setAdapter(arrayAdapter);

    }

}
