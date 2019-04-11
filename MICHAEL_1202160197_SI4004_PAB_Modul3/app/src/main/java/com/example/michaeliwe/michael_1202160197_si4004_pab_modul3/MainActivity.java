package com.example.michaeliwe.michael_1202160197_si4004_pab_modul3;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    private RecyclerView recycler_view;
    private ArrayList<User> users;
    private Adapter adapter;
    ArrayAdapter<String> array_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////

        recycler_view = findViewById(R.id.recycler_view);

        int gridColumnCount =getResources().getInteger(R.integer.grid_column_count);
        recycler_view.setLayoutManager(new GridLayoutManager(this,gridColumnCount));

        ////////////////

        users = new ArrayList<>();
        if (savedInstanceState!=null){
            users.clear();
            for (int i = 0; i <savedInstanceState.getStringArrayList("nama").size() ; i++) {
                users.add(new User(savedInstanceState.getStringArrayList("nama").get(i),
                        savedInstanceState.getStringArrayList("title").get(i),
                        savedInstanceState.getIntegerArrayList("jk").get(i)));
            }
        } else {
            users.clear();
        }

        ////////////////

        adapter = new Adapter(users,this);
        recycler_view.setAdapter(adapter);

        ////////////////

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(users, from ,to);
                adapter.notifyItemMoved(from,to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                users.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(recycler_view);

        ////////////////

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrayList<String>tempListNama = new ArrayList<>();
        ArrayList<String>tempListPekerjaan = new ArrayList<>();
        ArrayList<Integer>tempListGender = new ArrayList<>();
        for (int i = 0; i <users.size() ; i++) {
            tempListNama.add(users.get(i).getNama());
            tempListPekerjaan.add(users.get(i).getTitle());
            tempListGender.add(users.get(i).getJk());
        }
        outState.putStringArrayList("nama",tempListNama);
        outState.putStringArrayList("pekerjaan",tempListPekerjaan);
        outState.putIntegerArrayList("gender",tempListGender);
        super.onSaveInstanceState(outState);

    }

    public void onclick_add(View view){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        final TextView mNama,mPekerjaan;
        final Spinner mGender;
        mNama = dialog.findViewById(R.id.nama);
        mPekerjaan = dialog.findViewById(R.id.title);

        Button tambah = dialog.findViewById(R.id.btn_tambah);
        Button batal = dialog.findViewById(R.id.btn_cancel);

        mGender = dialog.findViewById(R.id.jk);

        String[]list={"Male","Female"};

        ArrayAdapter<String>adapterX = new ArrayAdapter(dialog.getContext(),android.R.layout.simple_spinner_item,list);
        mGender.setAdapter(adapterX);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.add(new User(mNama.getText().toString(),mPekerjaan.getText().toString(),mGender.getSelectedItemPosition()+1));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
