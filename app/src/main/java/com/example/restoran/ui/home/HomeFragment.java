package com.example.restoran.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.restoran.Data.Makanan;
import com.example.restoran.R;
import com.example.restoran.TambahData.TambahDataActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View myFragment;
    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Makanan, MakananViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstances()
    {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_home,container,false);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Makanan dan minuman");

        recyclerView = myFragment.findViewById(R.id.list_makanan);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Makanan>()
                .setQuery(query, Makanan.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Makanan, MakananViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MakananViewHolder makananViewHolder, int i, @NonNull final Makanan makanan) {
                makananViewHolder.bindToMakanan(makanan);
                Glide.with(HomeFragment.this).load(makanan.getGambar_makanan()).into(makananViewHolder.ivGambar);


                makananViewHolder.cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle extras = new Bundle();

                        Intent intent = new Intent(view.getContext(), TambahDataActivity.class);

                        extras.putInt("dummy", makanan.getDummy());

                        extras.putString("nama_makanan", makanan.getNama_makanan());
                        extras.putInt("harga_makanan", makanan.getHarga_makanan());
                        extras.putString("gambar_makanan", makanan.getGambar_makanan());

                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MakananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MakananViewHolder(inflater.inflate(R.layout.item_makanan, parent, false));
            }
        };

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if (mAdapter != null) {
            mAdapter.startListening();
        }

        System.out.println(mAdapter);

        return myFragment;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }


}