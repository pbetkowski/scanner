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

    TextView textView;
    Button button1;
    Button button2;

    String barcode = "";

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.t1);
        button1 =  (Button) findViewById(R.id.b1);
        button2 =  (Button) findViewById(R.id.b2);
    }

    public void scan(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ScannerHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    public void read(View view)
    {
            textView.append(barcode);  //nie wyświetla
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
            barcode = code;        // przyjmuje wartość zeskanowanego kodu ale się nie wyświetla
            Toast.makeText(MainActivity.this, code, Toast.LENGTH_LONG).show();  //wyświetla poprawnie
            setContentView(R.layout.activity_main);
            scannerView.stopCamera();
        }
    }
}


