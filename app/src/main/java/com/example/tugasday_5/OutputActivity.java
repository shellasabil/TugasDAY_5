package com.example.tugasday_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OutputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // Mendapatkan data dari intent
        Intent intent = getIntent();
        String kodeBarang = intent.getStringExtra("kode_barang");
        String namaBarang = intent.getStringExtra("nama_barang");
        double hargaBarang = intent.getDoubleExtra("harga_barang", 0);
        int jumlahBarang = intent.getIntExtra("jumlah_barang", 0);
        double totalHarga = intent.getDoubleExtra("total_harga", 0);
        double diskonPembelian = intent.getDoubleExtra("diskon_pem", 0);
        double diskonMember = intent.getDoubleExtra("diskon_member", 0);
        double jumlahBayar = intent.getDoubleExtra("jumlah_bayar", 0);

        // Menampilkan data di TextView
        TextView textViewKodeBarang = findViewById(R.id.textViewKodeBarang);
        textViewKodeBarang.setText(getString(R.string.kodebrg) + " : " + kodeBarang);

        TextView textViewNamaBarang = findViewById(R.id.textViewNamaBarang);
        textViewNamaBarang.setText(getString(R.string.namabrg) + " : " + namaBarang);

        TextView textViewHargaBarang = findViewById(R.id.textViewHarga);
        textViewHargaBarang.setText(getString(R.string.harga) + ": Rp" + String.format("%.2f", hargaBarang));

        TextView textViewJumlahBarang = findViewById(R.id.textViewJumlahBayar);
        textViewJumlahBarang.setText(getString(R.string.jumlahbrg) + " : " + jumlahBarang);

        TextView textViewTotalHarga = findViewById(R.id.textViewTotalHarga);
        textViewTotalHarga.setText(getString(R.string.totalharga) + ": Rp" + String.format("%.2f", totalHarga));

        TextView textViewDiskonPembelian = findViewById(R.id.textViewDiskonHarga);
        textViewDiskonPembelian.setText(getString(R.string.diskharga) + ": Rp" + String.format("%.2f", diskonPembelian));

        TextView textViewDiskonMember = findViewById(R.id.textViewDiskonMember);
        textViewDiskonMember.setText(getString(R.string.diskmem) + ": Rp" + String.format("%.2f", diskonMember));

        TextView textViewJumlahBayar = findViewById(R.id.textViewJumlahBayar);
        textViewJumlahBayar.setText(getString(R.string.jmlbyr) + ": Rp" + String.format("%.2f", jumlahBayar));



        // Tombol untuk membagikan bukti transaksi ke email
        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasi logika untuk membagikan bukti transaksi ke email
                String buktiTransaksi = "Detail Transaksi:\n\n" +
                        "Kode Barang: " + kodeBarang + "\n" +
                        "Nama Barang: " + namaBarang + "\n" +
                        "Harga Barang: Rp " + String.format("%.2f", hargaBarang) + "\n" +
                        "Jumlah Barang: " + jumlahBarang + "\n" +
                        "Total Harga: Rp " + String.format("%.2f", totalHarga) + "\n" +
                        "Diskon Pembelian: Rp " + String.format("%.2f", diskonPembelian) + "\n" +
                        "Diskon Member: Rp " + String.format("%.2f", diskonMember) + "\n" +
                        "Jumlah Bayar: Rp " + String.format("%.2f", jumlahBayar);

                // Panggil metode untuk mengirim email
                sendEmail(buktiTransaksi);
            }
        });
    }

    // Metode untuk mengirim email
    private void sendEmail(String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bukti Transaksi");
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(emailIntent, "Pilih Aplikasi Email"));
    }
}