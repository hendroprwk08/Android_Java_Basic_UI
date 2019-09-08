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
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    RadioButton rbKelas;
    Button btSave, btEdit, btCancel, btExit, btDetail, btSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hubungkan XML dengan java
        etNama = (EditText) findViewById(R.id.et_nama);
        etNPM = (EditText) findViewById(R.id.et_npm);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        spProdi = (Spinner) findViewById(R.id.sp_prodi);
        cbxTeknologi = (CheckBox) findViewById(R.id.cb_teknologi);
        cbxKuliner = (CheckBox) findViewById(R.id.cb_kuliner);
        rgKelas = (RadioGroup) findViewById(R.id.rg_kelas);
        btSave = (Button) findViewById(R.id.bt_save);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        btEdit = (Button) findViewById(R.id.bt_edit);
        btExit = (Button) findViewById(R.id.bt_exit);
        btDetail = (Button) findViewById(R.id.bt_detail);
        btSnack = (Button) findViewById(R.id.bt_snack);

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
                })
                .setNeutralButton("Netral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Tombol Netral ditekan",
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

        btDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, npm, alamat, prodi, kelas;
                Boolean teknologi, kuliner;

                //definisikan nilai
                nama = etNama.getText().toString();
                npm = etNPM.getText().toString();
                alamat = etAlamat.getText().toString();
                prodi = spProdi.getSelectedItem().toString();
                teknologi = cbxTeknologi.isChecked();
                kuliner = cbxKuliner.isChecked();

                //konfirmasi
                if (nama.length() == 0 || npm.length() == 0 || alamat.length() == 0){
                    Toast.makeText(getApplicationContext(),"Nama, NPM dan alamat wajid diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*
                triknya: pilih button group kemudian
                ambil radio button mana yang dipilih
                */

                int selectedId = rgKelas.getCheckedRadioButtonId();
                rbKelas = (RadioButton) findViewById(selectedId);
                kelas = rbKelas.getText().toString(); //ambil hasil kelasnya

                /*
                Selipkan data yang ingin dikirim ke detail activity
                dengan putExtra
                */

                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("x_nama", nama);
                i.putExtra("x_npm", npm);
                i.putExtra("x_alamat", alamat);
                i.putExtra("x_prodi", prodi);
                i.putExtra("x_teknologi", teknologi); //boolean
                i.putExtra("x_kuliner", kuliner); //boolean
                i.putExtra("x_kelas", kelas);
                getApplicationContext().startActivity(i);
            }
        });

        btSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = findViewById(R.id.main_layout_id);
                String message = "Snackbar message";
                int duration = Snackbar.LENGTH_SHORT;
                Snackbar.make(v, message, duration).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tambah:
                View view = findViewById(R.id.main_layout_id); //layout main activity harus constraint
                String message = "Snackbar message";
                int duration = Snackbar.LENGTH_SHORT; //alt + enter pada "Snackbar" untuk mendownload dependency
                Snackbar.make(view, message, duration).show();
        return true;
            case R.id.selip:
                Toast.makeText(getApplicationContext(), "action Selip", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.auto_update:
                Toast.makeText(getApplicationContext(), "action Auto Renew", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}