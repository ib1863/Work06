package com.example.work06;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder{
    TextView numberView;
    public myViewHolder(@NonNull View itemview, recyclerViewInterface recyclerViewInterface)
    {
        super(itemview);
        numberView = itemview.findViewById(R.id.tv1);
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION)
                    {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
