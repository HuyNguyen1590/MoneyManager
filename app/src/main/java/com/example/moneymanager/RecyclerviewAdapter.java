package com.example.moneymanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private List<Model> models;

    public RecyclerviewAdapter(List<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_item, parent, false);
        return new ViewHolder(v); }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.ten_giao_dich.setText(models.get(position).getTen_giao_dich());
        holder.ghi_chu.setText(models.get(position).getGhi_chu());
        holder.so_tien.setText(models.get(position).getSo_tien()+"");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa")
                        .setMessage("Bạn có muốn xóa bản ghi này?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Model model = models.get(position);
                                ((MainActivity)v.getContext()).deleteDataInRC(model);
                                Toast.makeText(v.getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ten_giao_dich, ghi_chu, so_tien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_giao_dich = itemView.findViewById(R.id.ten_giao_dich);
            ghi_chu = itemView.findViewById(R.id.ghi_chu);
            so_tien = itemView.findViewById(R.id.so_tien);
        }
    }
}
