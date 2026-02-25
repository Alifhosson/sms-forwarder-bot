package com.alif.smsbot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        tv.setText("✅ SMS Forwarder is Running\n\nKeep this app in background.");
        tv.setTextSize(20);
        tv.setTextColor(Color.GREEN);
        tv.setGravity(Gravity.CENTER);
        
        setContentView(tv);
    }
}
