package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    List<Model> models = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerviewAdapter adapter;
    ModelDAO dao;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khởi tạo Room database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "quan_li_tien").allowMainThreadQueries().build();
        dao = db.modelDAO();

        //init
        recyclerView = findViewById(R.id.todo_list);
        fab = findViewById(R.id.floatingActionButton);
        //recyclerview data
        models = new ArrayList<>();
        models = dao.getAll();
        adapter = new RecyclerviewAdapter(models);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.model_add_item, null);
                final EditText ten_giao_dich, ghi_chu, so_tien;
                ten_giao_dich = view.findViewById(R.id.ten_giao_dich);
                ghi_chu = view.findViewById(R.id.ghi_chu);
                so_tien = view.findViewById(R.id.so_tien);
                builder.setView(view)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Model model = new Model();
                                model.setTen_giao_dich(ten_giao_dich.getText().toString());
                                model.setGhi_chu(ghi_chu.getText().toString());
                                model.setSo_tien(Integer.parseInt(so_tien.getText().toString()));
                                insertDataInRC(model);
                            }
                        })
                        .setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }
    public void insertDataInRC(Model model){
        dao.insertAll(model);
        models.add(model);
        adapter.notifyDataSetChanged();
    }
    public void deleteDataInRC(Model model){
        dao.delete(model);
        models.remove(model);
        adapter.notifyDataSetChanged();
    }
}
