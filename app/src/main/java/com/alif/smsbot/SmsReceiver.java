package com.alif.smsbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    String body = sms.getMessageBody();
                    String sender = sms.getOriginatingAddress();

                    if (sender != null && (sender.toLowerCase().contains("bkash") || 
                        sender.toLowerCase().contains("nagad") || 
                        sender.toLowerCase().contains("16247"))) {
                        
                        sendToBot(body, sender);
                    }
                }
            }
        }
    }

    private void sendToBot(final String msg, final String from) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // ⚠️ এখানে আপনার বটের সঠিক URL দিন
                    URL url = new URL("https://ipshop0001.vercel.app/sms-webhook");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setDoOutput(true);

                    JSONObject json = new JSONObject();
                    json.put("message", msg);
                    json.put("from", from);

                    OutputStream os = conn.getOutputStream();
                    os.write(json.toString().getBytes("UTF-8"));
                    os.flush();
                    os.close();

                    conn.getResponseCode(); 
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
