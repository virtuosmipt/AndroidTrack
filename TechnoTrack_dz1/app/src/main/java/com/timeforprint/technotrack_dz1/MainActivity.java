package com.timeforprint.technotrack_dz1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        },1100);*/
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    int logoTimer = 0;
                    while(logoTimer < 2000) {
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        timer.start();


    }


}
