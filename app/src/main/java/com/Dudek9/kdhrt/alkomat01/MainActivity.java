package com.Dudek9.kdhrt.alkomat01;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));



            MobileAds.initialize(this, "ca-app-pub-1448095865400513~1707885149");

            adView = new AdView(this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-1448095865400513/7507006730");

            adView = findViewById(R.id.adView2);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void click(View view) {
        Intent intent;
        intent =new Intent(MainActivity.this,app2.class);
        startActivity(intent);
    }

    public void clicksave(View view) {
        Intent intent;
        intent= new Intent(MainActivity.this,Zapisane.class);
        startActivity(intent);
    }
}
