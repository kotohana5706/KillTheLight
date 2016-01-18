package org.iptime.songjun.killthelight;

import android.app.ActionBar;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class MainActivity extends AppCompatActivity {
    BluetoothSPP bt;
    ImageView im;
    String receive;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (ImageView) findViewById(R.id.img_onoff);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.send("n",true);
            }
        });

        bt=new BluetoothSPP(this);

            if(!bt.isBluetoothAvailable())

            {
                Toast.makeText(getApplicationContext()
                        , "블루투스를 켜주세요"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }

            bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener()

            {
                public void onDeviceConnected(String name, String address) {
                    Toast.makeText(getApplicationContext()
                            , "연결되었습니다", Toast.LENGTH_SHORT).show();
                }

                public void onDeviceDisconnected() {
                    Toast.makeText(getApplicationContext()
                            , "연결이끊겼습니다"
                            , Toast.LENGTH_SHORT).show();
                }

                public void onDeviceConnectionFailed() {
                }
            });

        bt.setAutoConnectionListener(new BluetoothSPP.AutoConnectionListener() {
            public void onNewConnection(String name, String address) {
            }

            public void onAutoConnectionStarted() {
            }
        });
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                receive = message;
                if (receive.equals("to=true"))
                    im.setImageResource(R.drawable.off);
                else if (receive.equals("to=false"))
                    im.setImageResource(R.drawable.on);

            }
        });

    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService();
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            bt.enable();
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "블루투스 켜주세요"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void setup() {
        bt.autoConnect("HC-06");
    }
}
