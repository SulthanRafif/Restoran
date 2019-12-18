package com.example.restoran.ui.notifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoran.Data.Makanan;
import com.example.restoran.Data.Pesanan;
import com.example.restoran.R;

public class PesananViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivPesanGambar;
    public TextView tvPesanNama;
    public TextView tvPesanHarga;
    public TextView tvPesanJumlah;
    public CardView cvPesan;


    public PesananViewHolder(@NonNull final View itemView) {
        super(itemView);

        tvPesanNama = itemView.findViewById(R.id.tv_nama_pesanan);
        tvPesanHarga = itemView.findViewById(R.id.tv_harga_pesanan);
        ivPesanGambar = itemView.findViewById(R.id.iv_gambar_pesanan);
        tvPesanJumlah = itemView.findViewById(R.id.tv_jumlah_pesanan);

        cvPesan = itemView.findViewById(R.id.cardPesanan);
    }

    public void bindToMakanan(Pesanan pesanan) {

        String harga = "IDR " + pesanan.harga_pesanan;

        String jumlah = "Jumlah " + pesanan.jumlah_pesanan;

        tvPesanNama.setText(pesanan.nama_pesanan);
        tvPesanHarga.setText(harga);
        tvPesanJumlah.setText(jumlah);
    }
}
