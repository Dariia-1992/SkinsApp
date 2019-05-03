package com.hi.skinsapp;

import android.content.Intent;
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
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private AssetManager mManager;
    private RelativeLayout mLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mLayout= findViewById(R.id.itemLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mManager = getAssets();
        SkinsAdapter adapter = new SkinsAdapter(listImage(), mManager, mItemSelected);
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

    private final SkinsAdapter.OnItemSelected mItemSelected = new SkinsAdapter.OnItemSelected() {
        @Override
        public void onItemSlected(int position) {
            Intent intent = new Intent(MainActivity.this, SkinActivity.class);
            intent.putExtra(SkinActivity.EXTRA_POSITION, position);
            startActivity(intent);
        }
    };
}
