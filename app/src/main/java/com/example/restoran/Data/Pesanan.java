package com.example.restoran.Data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Pesanan {

    public String gambar_pesanan;
    public String nama_pesanan;
    public String jumlah_pesanan;
    public int harga_pesanan;

    public Pesanan(){

    }

    public Pesanan(String gambar_pesanan, String nama_pesanan, String jumlah_pesanan, int harga_pesanan) {
        this.gambar_pesanan = gambar_pesanan;
        this.nama_pesanan = nama_pesanan;
        this.jumlah_pesanan = jumlah_pesanan;
        this.harga_pesanan = harga_pesanan;
    }

    public String getGambar_pesanan() {
        return gambar_pesanan;
    }

    public String getNama_pesanan() {
        return nama_pesanan;
    }

    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public int getHarga_pesanan() {
        return harga_pesanan;
    }

    public void setGambar_pesanan(String gambar_pesanan) {
        this.gambar_pesanan = gambar_pesanan;
    }

    public void setNama_pesanan(String nama_pesanan) {
        this.nama_pesanan = nama_pesanan;
    }

    public void setJumlah_pesanan(String jumlah_pesanan) {
        this.jumlah_pesanan = jumlah_pesanan;
    }

    public void setHarga_pesanan(int harga_pesanan) {
        this.harga_pesanan = harga_pesanan;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama_pesanan", nama_pesanan);
        result.put("gambar_pesanan", gambar_pesanan);
        result.put("harga_pesanan", harga_pesanan);
        result.put("jumlah_pesanan", jumlah_pesanan);
        return result;
    }

}
