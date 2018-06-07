package com.example.javaandroid.skaner;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button1;
    Button button2;

    private static final int Camera = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //kontrolki
        textView = findViewById(R.id.t1);
        button1 =  findViewById(R.id.b1);
        button2 =  findViewById(R.id.b2);
    }

    private void scan(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ScannerHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ScannerHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(Result result) {

            String code = result.getText();
            Toast.makeText(MainActivity.this, code, Toast.LENGTH_LONG).show();

            setContentView(R.layout.activity_main);
            scannerView.stopCamera();
        }
    }
}


