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

import java.io.IOException;
import java.io.InputStream;

public class SkinsAdapter extends RecyclerView.Adapter<SkinsAdapter.ViewHolder> {

    private String [] paths;
    private AssetManager manager;

    public SkinsAdapter(String[] paths, AssetManager manager){
        this.paths = paths;
        this.manager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skin_card_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try {
            InputStream is = manager.open("thumb/" + paths[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            viewHolder.skinImage.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (paths == null)
            return 0;
        return paths.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView skinImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skinImage = itemView.findViewById(R.id.skinImage);
        }
    }
}
