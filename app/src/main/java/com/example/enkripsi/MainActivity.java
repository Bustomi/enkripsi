package com.example.enkripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etInput, etOutput;
    private String publicKey = "";
    private String privateKey = "";
    private byte[] encodeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = (EditText) findViewById(R.id.etInput);
        etOutput = (EditText) findViewById(R.id.etOutput);

        try {
            Map<String, Object> keyMap = rsa.initKey();
            publicKey = rsa.getPublicKey(keyMap);
            privateKey = rsa.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void encrypt(View v){
         String publicKey = getPublicKey();
         byte[] rsaData = etInput.getText().toString().getBytes();

         try {
             encodeData = rsa.encryptByPublicKey(rsaData,publicKey);
             String encodeStar = new BigInteger(1,encodeData).toString();
             etOutput.setText(encodeStar);
         } catch (Exception e) {
             e.printStackTrace();
         }

    }
    public void decrypt(View v){


        try {
            byte[] decodeData = rsa.encryptByPrivateKey(encodeData,getPrivateKey());
            String decodeStar = new String(decodeData);
            etOutput.setText(decodeStar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}
