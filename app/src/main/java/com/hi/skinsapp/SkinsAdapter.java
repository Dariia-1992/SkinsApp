package com.hi.skinsapp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class SkinsAdapter extends RecyclerView.Adapter<SkinsAdapter.ViewHolder> {

    public interface OnItemSelected{
        void onItemSlected(int position);
    }

    private String [] paths;
    private AssetManager manager;
    private OnItemSelected listener;

    public SkinsAdapter(String[] paths, AssetManager manager, OnItemSelected listener){
        this.paths = paths;
        this.manager = manager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skin_card_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        try {
            InputStream is = manager.open("thumb/" + paths[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            viewHolder.skinImage.setImageBitmap(bitmap);
            viewHolder.numberText.setText("# " + (i+1));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemSlected(i);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return paths != null ? paths.length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView skinImage;
        TextView numberText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skinImage = itemView.findViewById(R.id.skinImage);
            numberText = itemView.findViewById(R.id.numberText);
        }
    }
}
