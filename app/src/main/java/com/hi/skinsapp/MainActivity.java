package com.hi.skinsapp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView mImageView;
    private AssetManager mManager;
    private RelativeLayout mLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mImageView = findViewById(R.id.skinImage);
        mLayout= findViewById(R.id.itemLayout);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mManager = getAssets();
        SkinsAdapter adapter = new SkinsAdapter(listImage(), mManager);
        mRecyclerView.setAdapter(adapter);


        setSupportActionBar(mToolbar);
    }

    public String[] listImage(){
        try{
            return mManager.list("thumb");
        }
        catch (IOException e){
         return new String[0];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);//удалить?
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) searchItem.getActionView();
        //searchView.setQueryHint("Search");
        ///searchView.setOnQueryTextListener(this);
        //searchView.setIconified(false);
        return true;
    }

   /* public void listOfSkinsImage(){
       try{
           paths = mManager.list("skins_image");
           for (int i = 0; i < paths.length; i++){
               InputStream is = mManager.open("skins_image/" + paths[i]);
               Log.d("loading image", paths[i]);
               Bitmap bitmap = BitmapFactory.decodeStream(is);

               mImageView = new ImageView(this);
               mImageView.setImageBitmap(bitmap);
               RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
               mImageView.setLayoutParams(params);
               mLayout.addView(mImageView);
           }
       }
       catch (IOException e){
           Log.e("loading image", e.getMessage());
           return;
       }
    }*/
}
