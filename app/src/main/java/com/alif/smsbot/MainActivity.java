package com.alif.smsbot;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("✅ SMS Forwarder is ACTIVE");
        tv.setTextSize(20);
        tv.setTextColor(Color.GREEN);
        tv.setPadding(50, 100, 50, 0);
        setContentView(tv);
    }
}
