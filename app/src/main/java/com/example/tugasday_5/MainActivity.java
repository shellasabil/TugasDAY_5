package com.example.tugasday_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNama;
    private EditText editTextKodeBarang;
    private EditText editTextJumlahBarang;
    private RadioGroup radioGroup;
    private Button calculateButton;

    // Konstanta untuk diskon pembelian
    private static final double DISKON_PEMBELIAN = 100000;
    private static final double BATAS_PEMBELIAN = 10000000;

    // Konstanta untuk diskon member
    private static final double DISKON_GOLD = 0.1;
    private static final double DISKON_SILVER = 0.05;
    private static final double DISKON_BIASA = 0.02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextKodeBarang = findViewById(R.id.editTextKodeBarang);
        editTextJumlahBarang = findViewById(R.id.editTextJumlahBarang);
        radioGroup = findViewById(R.id.radioGroup);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        String kodeBarang = editTextKodeBarang.getText().toString().trim();
        int jumlahBarang = Integer.parseInt(editTextJumlahBarang.getText().toString().trim());
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        if (kodeBarang.isEmpty()) {
            Toast.makeText(this, "Masukkan kode barang", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedRadioButton == null) {
            Toast.makeText(this, "Pilih jenis keanggotaan", Toast.LENGTH_SHORT).show();
            return;
        }

        double hargaBarang = getHargaBarang(kodeBarang);
        double totalHarga = hargaBarang * jumlahBarang;
        double diskonPembelian = 0;

        if (totalHarga > BATAS_PEMBELIAN) {
            diskonPembelian = DISKON_PEMBELIAN;
            totalHarga -= DISKON_PEMBELIAN;
        }

        double diskonMember = 0;
        String jenisMember = selectedRadioButton.getText().toString();

        switch (jenisMember) {
            case "Gold":
                diskonMember = DISKON_GOLD;
                break;
            case "Silver":
                diskonMember = DISKON_SILVER;
                break;
            case "Biasa":
                diskonMember = DISKON_BIASA;
                break;
        }

        double diskonHarga = totalHarga * diskonMember;
        double jumlahBayar = totalHarga - diskonHarga;

        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtra("kode_barang", kodeBarang);
        intent.putExtra("nama_barang", getNamaBarang(kodeBarang));
        intent.putExtra("harga_barang", hargaBarang);
        intent.putExtra("jumlah_barang", jumlahBarang);
        intent.putExtra("total_harga", totalHarga);
        intent.putExtra("diskon_pem", diskonPembelian);
        intent.putExtra("diskon_member", diskonHarga);
        intent.putExtra("jumlah_bayar", jumlahBayar);
        startActivity(intent);
    }

    // Metode getNamaBarang dan getHargaBarang disini

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return "Iphone X";
            case "AV4":
                return "Asus Vivobook 14";
            case "LV3":
                return "Lenovo V14 Gen 3";
            default:
                return "Nama Barang Tidak Diketahui";
        }
    }

    private double getHargaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return 5725300;
            case "AV4":
                return 9150999;
            case "LV3":
                return 6666666;
            default:
                return 0;
        }
    }
}


