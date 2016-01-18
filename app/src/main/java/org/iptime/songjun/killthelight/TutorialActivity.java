package org.iptime.songjun.killthelight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by songjun on 2016. 1. 13..
 */
public class TutorialActivity extends Activity {
    Handler hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(getApplicationContext(), DeviceListActivity.class));
            }
        }, 3000);

    }
}
