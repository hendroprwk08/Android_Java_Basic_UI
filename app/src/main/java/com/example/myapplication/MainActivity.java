package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //definisi widget
    EditText etNama, etNPM, etAlamat;
    Spinner spProdi;
    CheckBox cbxTeknologi, cbxKuliner;
    RadioGroup rgKelas;
    RadioButton rbIK, rbTI;
    Button btSave, btEdit, btCancel, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hubungkan XML dengan java
        etNama = (EditText) findViewById(R.id.et_nama);
        etNPM = (EditText) findViewById(R.id.et_npm);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        spProdi = (Spinner) findViewById(R.id.sp_prodi);
        cbxTeknologi = (CheckBox) findViewById(R.id.cb_teknologi);
        cbxKuliner = (CheckBox) findViewById(R.id.cb_kuliner);
        rgKelas = (RadioGroup) findViewById(R.id.rg_kelas);
        rbIK = (RadioButton) findViewById(R.id.rd_IK);
        rbTI = (RadioButton) findViewById(R.id.rd_TK);
        btSave = (Button) findViewById(R.id.bt_save);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        btEdit = (Button) findViewById(R.id.bt_edit);
        btExit = (Button) findViewById(R.id.bt_exit);

        //memberikan fungsi pada tombol
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hai bro", Toast.LENGTH_SHORT).show();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menampilkan dialog
                new AlertDialog.Builder(MainActivity.this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Sabar")
                    .setMessage("Ini dialog box")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),
                                    "Anda baru menekan tombol OK",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),
                                    "Tombol Batal ditekan",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }).show();
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notifikasi
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Aplikasi buatanku");
                bigText.setSummaryText("Ini adalah notifikasi dariku");

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Aplikasi buatanku");
                mBuilder.setContentText("Notifikasi ini menggunakan versi terbaru");
                mBuilder.setPriority(Notification.PRIORITY_MAX);
                mBuilder.setStyle(bigText);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND); //suara
                mBuilder.setVibrate(new long[] {1000, 1000, 1000, 1000, 1000, 1000}); //getar

                NotificationManager mNotificationManager =
                        (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("notify_001",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }

                mNotificationManager.notify(0, mBuilder.build());
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //tutup aplikasi
            }
        });
    }
}
