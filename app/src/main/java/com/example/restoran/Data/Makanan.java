package com.example.restoran.Data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Makanan {

    public String nama_makanan;
    public int harga_makanan;
    public String gambar_makanan;


    public Makanan(){

    }

    public Makanan(String nama_makanan, int harga_makanan, String gambar_makanan) {
        this.nama_makanan = nama_makanan;
        this.harga_makanan = harga_makanan;
        this.gambar_makanan = gambar_makanan;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public int getHarga_makanan() {
        return harga_makanan;
    }

    public void setHarga_makanan(int harga_makanan) {
        this.harga_makanan = harga_makanan;
    }

    public String getGambar_makanan() {
        return gambar_makanan;
    }

    public void setGambar_makanan(String gambar_makanan) {
        this.gambar_makanan = gambar_makanan;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama_makanan", nama_makanan);
        result.put("gambar_makanan", gambar_makanan);
        result.put("harga_makanan", harga_makanan);
        return result;
    }
}
