package com.example.restoran.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoran.Data.Makanan;
import com.example.restoran.R;
import com.example.restoran.TambahData.TambahDataActivity;

public class MakananViewHolder extends RecyclerView.ViewHolder {

    private AdapterView.OnItemClickListener listener;

    public ImageView ivGambar;
    public TextView tvNama;
    public TextView tvHarga;


    public MakananViewHolder(@NonNull final View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvHarga = itemView.findViewById(R.id.tv_harga);
        ivGambar = itemView.findViewById(R.id.iv_gambar);

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION && listener != null) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), TambahDataActivity.class));
                }

            }
        });

    }


    public void bindToMakanan(Makanan makanan) {

        tvNama.setText(makanan.nama_makanan);
        tvHarga.setText("IDR " + String.valueOf(makanan.harga_makanan));
    }
}
