package com.example.email;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MainActivity extends AppCompatActivity {

    private EditText editsub,editmsg;
    private Button send;
    private final String to = "koshimagoyal97@gmail.com";
    private final String from="govtapp123@gmail.com";
    private String sub,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editsub=(EditText)findViewById(R.id.subject);
                editmsg=(EditText) findViewById(R.id.message);
                sub=editsub.getText().toString();
                body=editmsg.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Mail sender = new Mail("govtapp123@gmail.com", "ga1234567890");
                            sender.sendMail(sub,body, from, to);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"message sent",Toast.LENGTH_LONG).show();
                                }
                            });

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();

            }
            });

    }

}
