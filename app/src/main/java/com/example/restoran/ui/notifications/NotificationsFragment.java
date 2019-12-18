package com.example.restoran.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restoran.Data.Pesanan;
import com.example.restoran.R;
import com.example.restoran.ui.home.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NotificationsFragment extends Fragment {

    View myFragment;

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Pesanan, PesananViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;

    private NotificationsViewModel notificationsViewModel;

    public NotificationsFragment(){
        // Required empty public constructor
    }

    public static NotificationsFragment newInstances(){

        NotificationsFragment notificationsFragment = new NotificationsFragment();
        return notificationsFragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_notifications,container,false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("list pemesanan");

        recyclerView = myFragment.findViewById(R.id.list_pesanan);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(container.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        Query query = mDatabase;

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Pesanan>()
                .setQuery(query, Pesanan.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Pesanan, PesananViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PesananViewHolder pesananViewHolder, int i, @NonNull Pesanan pesanan) {
                pesananViewHolder.bindToMakanan(pesanan);
                Glide.with(NotificationsFragment.this).load(pesanan.getGambar_pesanan()).into(pesananViewHolder.ivPesanGambar);
            }

            @NonNull
            @Override
            public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new PesananViewHolder(inflater.inflate(R.layout.item_pesanan, parent, false));
            }
        };

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);

        if(mAdapter != null){
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