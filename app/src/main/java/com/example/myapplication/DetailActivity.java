package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.Console;

public class DetailActivity extends AppCompatActivity {

    TextView tvNama, tvNPM, tvAlamat, tvProdi;
    RadioGroup rgKelas;
    RadioButton rbIK, rbTK;
    CheckBox cbxTeknologi, cbxKuliner;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //definisi widget
        tvNama = (TextView) findViewById(R.id.tv_nama);
        tvNPM = (TextView) findViewById(R.id.tv_npm);
        tvAlamat = (TextView) findViewById(R.id.tv_alamat);
        tvProdi = (TextView) findViewById(R.id.tv_prodi);
        cbxTeknologi = (CheckBox) findViewById(R.id.cb_teknologi);
        cbxKuliner = (CheckBox) findViewById(R.id.cb_kuliner);
        rgKelas = (RadioGroup) findViewById(R.id.rg_kelas);
        rbIK = (RadioButton) findViewById(R.id.rd_IK);
        rbTK = (RadioButton) findViewById(R.id.rd_TK);

        //tangkap extra yang dikirim dari main activity
        Intent i = getIntent();
        tvNama.setText(i.getStringExtra("x_nama"));
        tvNPM.setText(i.getStringExtra("x_npm"));
        tvAlamat.setText(i.getStringExtra("x_alamat"));
        tvProdi.setText(i.getStringExtra("x_prodi"));
        cbxTeknologi.setChecked(i.getBooleanExtra("x_teknologi", false));
        cbxKuliner.setChecked(i.getBooleanExtra("x_kuliner", false));

        String kelas = i.getStringExtra("x_kelas");
        if (kelas.equals("Tek. Komputer")){
            rbTK.setChecked(true);
        }else{
            rbIK.setChecked(true);
        }

        /*
        menampilkan tombol back ada 2 tahap
        1. pada manifest harap ditentukan dahulu parent-nya
        android:parentActivityName=".MainActivity"
        2. agar memiliki fungsi yang sama dengan back button kode perlu di override
        onOptionsItemSelected(MenuItem item)
        */

        getSupportActionBar().setTitle("Detail Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
