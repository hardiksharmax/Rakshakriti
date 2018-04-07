package com.ardunio.rakshakriti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


import java.util.regex.Pattern;

/**
 * Created by edusip3 on 6/3/2017.
 */

public class OtpReciever extends BroadcastReceiver {
    private static SmsListener mListener;

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

//        if (Build.VERSION.SDK_INT >= 19) { //KITKAT
//            SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
//            smsMessage = msgs[0];
//        }

        Object[] pdus = (Object[]) data.get("pdus");

        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String otp;

            Pattern p = Pattern.compile("(\\w+)=\"*((?<=\")[^\"]+(?=\")|([^\\s]+))\"*");
            String sender = smsMessage.getDisplayOriginatingAddress();
            System.out.println("OtpReciever.onReceive " + sender);
            if (sender.equals("+917982102971")) {

                //You must check here if the sender is your provider and not another one with same text.

                String messageBody = smsMessage.getMessageBody();

                //Pass on the text to our listener.


                if (messageBody != null) {
                    System.out.println("OtpReciever.onReceive " + messageBody);


                    String lat = messageBody.substring(6, 13);
                    String lng = messageBody.substring(20, 27);
                    System.out.println("OtpReciever.onReceive " + lat +  " " +lng);
                    if (mListener != null)
                        mListener.messageReceived(Double.valueOf(lat), Double.valueOf(lng));
                } else {
                    Toast.makeText(context, "Some problem while reading OTP. Please enter it manually", Toast.LENGTH_LONG).show();
                    //something went wrong
                }
            }


        }
    }

}
