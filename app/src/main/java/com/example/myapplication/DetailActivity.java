package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityDetailBinding;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //tangkap extra yang dikirim dari main activity
        Intent i = getIntent();
        binding.tvNama.setText(i.getStringExtra("x_nama"));
        binding.tvAlamat.setText(i.getStringExtra("x_alamat"));
        binding.tvProdi.setText(i.getStringExtra("x_prodi"));
        binding.cbTeknologi.setChecked(i.getBooleanExtra("x_teknologi", false));
        binding.cbKuliner.setChecked(i.getBooleanExtra("x_kuliner", false));

        String kelas = i.getStringExtra("x_domisili");
        assert kelas != null;
        if (kelas.equals("Luar Kota")) {
            binding.rbDK.setChecked(true);
        } else {
            binding.rbLK.setChecked(true);
        }

        /*
        menampilkan tombol back ada 2 tahap
        1. pada manifest harap ditentukan dahulu parent-nya
        android:parentActivityName=".MainActivity"
        2. agar memiliki fungsi yang sama dengan back button kode perlu di override
        onOptionsItemSelected(MenuItem item)
        */

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
