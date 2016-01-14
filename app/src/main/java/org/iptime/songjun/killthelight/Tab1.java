package org.iptime.songjun.killthelight;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

/**
 fuck you
 */
public class Tab1 extends Fragment {
    int status,fuck;
    Button onoff;
    ImageView onoffbg;
    BluetoothSPP bt;
    SharedPre sharedPre;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);
        onoff = (Button) v.findViewById(R.id.btn_onoff);
        onoffbg =(ImageView) v.findViewById(R.id.img_onoff);
        sharedPre = new SharedPre(getContext(), "lightstatus");

        bt = new BluetoothSPP(getContext());

//        sharedPre.get("lightstatus", "0");
        status=sharedPre.get("lightstatus",0);

        if (status ==.0) {
            //0초기 1켜짐 2꺼짐  // fuck 키기 you 끄기
            onoffbg.setImageResource(R.drawable.raeba);
        }
        else if(status==1){
            onoffbg.setImageResource(R.drawable.on);
        }
        else{
            onoffbg.setImageResource(R.drawable.off);
        }

//        onoff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
            return v;
        }
    public void onoff(View v){
        if (status == 2) {
            onoffbg.setImageResource(R.drawable.on);
//            bt.send("fuck", true);
            status = 1;
            sharedPre.put("lightstatus", status);
            status=sharedPre.get("lightstatus",status);
            Toast.makeText(getContext(), "on", Toast.LENGTH_LONG).show();
        }
        else if(status==3){
            onoffbg.setImageResource(R.drawable.off);
            bt.send("you", true);
            status = 2;
            sharedPre.put("lightstatus", status);
            status=sharedPre.get("lightstatus",status);
        }
    }
}