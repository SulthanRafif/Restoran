package com.example.restoran.TambahData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.restoran.MainActivity;
import com.example.restoran.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TambahDataActivity extends AppCompatActivity {

    StorageReference strRef;

    Button tambah;

    public Uri imguri;

    String gambarURL;

    NumberPicker jumlahMakanan;
    TextView namaMakanan, hargaMakanan;
    public ImageView imageMakanan;

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, TambahDataActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        jumlahMakanan = findViewById(R.id.jumlah_pemesanan);

        jumlahMakanan.setMaxValue(100);
        jumlahMakanan.setMinValue(1);


        strRef = FirebaseStorage.getInstance().getReference();

        tambah = findViewById(R.id.tambah);

        jumlahMakanan = findViewById(R.id.jumlah_pemesanan);

        namaMakanan = findViewById(R.id.nama_makanan);
        namaMakanan.setText(extras.getString("nama_makanan"));

        gambarURL = extras.getString("gambar_makanan");

        hargaMakanan = findViewById(R.id.harganya_makanan);
        hargaMakanan.setText(String.valueOf(extras.getInt("harga_makanan")));

        imageMakanan = findViewById(R.id.image_makanan);
        setDisplayImage(extras.getString("gambar_makanan"), TambahDataActivity.this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nambah();
            }
        });
    }

    private void Nambah() {

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        final StorageReference reference = strRef.child(System.currentTimeMillis()+".");

        final String pesanMakan = namaMakanan.getText().toString();
        final int pesanHarga = (extras.getInt("harga_makanan") * jumlahMakanan.getValue());
        final String pesanJumlah = "" +  jumlahMakanan.getValue();
        final String gambar = gambarURL;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("list pemesanan").child(pesanMakan);

        ref.child("harga_pesanan").setValue(pesanHarga);
        ref.child("jumlah_pesanan").setValue(pesanJumlah);
        ref.child("gambar_pesanan").setValue(gambar);
        ref.child("nama_pesanan").setValue(pesanMakan);

        Toast.makeText(this, "Pemesanan berhasil", Toast.LENGTH_SHORT).show();
        return;




    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void setDisplayImage(String imageUrl, Context context){
        Glide.with(context)
                .load(imageUrl)
                .into(imageMakanan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK & data != null ){
            imguri = data.getData();
            imageMakanan.setImageURI(imguri);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
