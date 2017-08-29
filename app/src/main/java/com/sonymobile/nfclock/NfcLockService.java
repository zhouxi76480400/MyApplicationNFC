package com.sonymobile.nfclock;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.test.myapplicationnfc.MainActivity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by zhouxi on 28/8/2017.
 */

public class NfcLockService {

    static {

        Log.e("zhouxi","NfcLockService loadOK");
    }

    private static native boolean isUrgentNfcLockStatus();

    private static native void getAuthArray(byte[] paramArrayOfByte);

    public static void aaa(){

        Log.e("test","isUrgentNfcLockStatus:"+isUrgentNfcLockStatus());




        byte[] array = new byte[1024];

        Log.e("test", Arrays.toString(array));
        getAuthArray(array);

        Log.e("test", Arrays.toString(array));

    }

    public static boolean setNfcLockWithContext(final MainActivity c,String pwd){
        TelephonyManager localTelephonyManager = (TelephonyManager) c.getSystemService("phone");
        return checkPassphrase(localTelephonyManager,pwd);
    }

    private static native boolean checkPassphrase(Object paramObject, String paramString);


    static char[] encodeArray = {'0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


    public static void setNfcLockWithContext(final MainActivity c){
        TelephonyManager localTelephonyManager = (TelephonyManager) c.getSystemService("phone");

        int bitCount = encodeArray.length;

        int minLength = 4;
        int maxLength = 16;
        int nowLength = minLength;

        int cycleCount = maxLength - minLength + 1;//要算的轮数

        int bits [];

        boolean isStop = false;

        for(int i = 0 ; i < cycleCount ; i ++){
            if(!isStop){
                bits = new int[maxLength];
                final int capital = (minLength+i);//次方
                Log.e("test","开始算第"+(i+1)+"轮("+capital+")");

                final int finalI = i;
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        c.tv1.setText("开始算第"+(finalI +1)+"轮("+capital+")");
                    }
                });

                while(bits[capital - 1] != bitCount && !isStop){
                    for(int j = 0 ; j < capital ; j ++){
                        if(!isStop){
                            if(j > 0){
                                int prevPos = bits[j-1];
                                if(prevPos == bitCount){
                                    bits[j]++;
                                    bits[j-1] = 0;
                                }
                            }else{
                                bits[j] ++;
                            }


                            boolean isSkip = false;
                            for(int k = 0 ; k < capital ; k++){
                                int bitK = bits[k];
                                if(bitK == bitCount){
                                    isSkip = true;
                                    break;
                                }
                            }
                            if(!isSkip){
                                final StringBuilder sb = new StringBuilder();
                                for(int k = 0 ; k < capital ; k++){
                                    int bitK = bits[k];
                                    char bitKK = encodeArray[bitK];
                                    sb.append(bitKK);
                                }


                                boolean isPassPhrase = checkPassphrase(localTelephonyManager,sb.toString());
                                if(isPassPhrase){
                                    isStop = true;
                                    Log.e("test","test:"+isPassPhrase);
                                    Log.e("test",sb.toString());

                                    c.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            c.tv2.setText(sb.toString());
                                        }
                                    });

                                    break;
                                }

                            }

                        }
                    }
                }
            }
        }
    }



//    public boolean isTaDataMatch(byte[] paramString, int paramInt1, int paramInt2)
//    {
//        if ((paramString == null) || (paramString.length <= 0))
//        {
//            Log.e("NfcLockState", "isTaDataMatch: illegal parameter.");
//            return false;
//        }
//        paramString = Util.convertStringToHash(paramString, paramInt2);
//        if (paramString == null)
//        {
//            Log.e("NfcLockState", "isTaDataMatch: hash is null.");
//            return false;
//        }
//        byte[] arrayOfByte = new byte[paramInt2];
//        try
//        {
//            System.arraycopy(this.mMiscTaData, paramInt1, arrayOfByte, 0, paramInt2);
//            boolean bool = Arrays.equals(arrayOfByte, paramString);
//            return bool;
//        }
//        finally {}
//    }

}
