package com.example.work06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> {
    private final recyclerViewInterface recyclerViewInterface;
    Context context;
    String[] items;

    public myAdapter(Context context, String[] items, recyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false), recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.numberView.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
