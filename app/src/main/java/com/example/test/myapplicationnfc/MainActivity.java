package com.example.test.myapplicationnfc;

import android.content.ComponentName;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sonymobile.nfclock.NfcLockService;

import java.io.DataOutputStream;

public class MainActivity extends AppCompatActivity {

    public TextView tv1,tv2;
    public EditText et;
    public Button btn;

    static {
        System.loadLibrary("nfclock_jni");
        Log.e("zhouxi","load_OK");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        et = (EditText) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = NfcLockService.setNfcLockWithContext(MainActivity.this,et.getText().toString());
                if(a){

                Toast.makeText(v.getContext(),"密码正确",Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(v.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        t.start();

//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//
//        if(!nfcAdapter.isEnabled()){
//            nfcAdapter.enableReaderMode(this, new NfcAdapter.ReaderCallback() {
//                @Override
//                public void onTagDiscovered(Tag tag) {
//
//                }
//            },0,null);
//
//        }

        Log.e("test", Build.TYPE+"");
    }

    Thread t = new Thread(){
        @Override
        public void run() {
            super.run();

            NfcLockService.setNfcLockWithContext(MainActivity.this);
        }
    };

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @return 应用程序是/否获取Root权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd="chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }
}

