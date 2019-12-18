package com.example.restoran.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoran.Data.Makanan;
import com.example.restoran.R;
import com.example.restoran.TambahData.TambahDataActivity;

public class MakananViewHolder extends RecyclerView.ViewHolder {


    public ImageView ivGambar;
    public TextView tvNama;
    public TextView tvHarga;
    public CardView cv;


    public MakananViewHolder(@NonNull final View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvHarga = itemView.findViewById(R.id.tv_harga);
        ivGambar = itemView.findViewById(R.id.iv_gambar);

        cv = itemView.findViewById(R.id.cardMakanan);
    }


    public void bindToMakanan(Makanan makanan) {

        String harga = "IDR " + makanan.harga_makanan;

        tvNama.setText(makanan.nama_makanan);
        tvHarga.setText(harga);
    }
}
