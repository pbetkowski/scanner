package com.example.javaandroid.skaner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {

    static TextView textView;
    Button button1;

    static String barcode = "";

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 =  (Button) findViewById(R.id.b1);

    }

    public void scan(View view)
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

            barcode = result.getText();
            Toast.makeText(MainActivity.this, barcode, Toast.LENGTH_LONG).show();  //wyświetla poprawnie
            setContentView(R.layout.activity_main);
            textView = (TextView) findViewById(R.id.t1);
            textView.setText(barcode);
            scannerView.stopCamera();
        }
    }
}


