package com.hi.skinsapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        GridAutofitLayoutManager manager = new GridAutofitLayoutManager(this, getResources().getDimensionPixelSize(R.dimen.activity_column_width));
        mRecyclerView.setLayoutManager(manager);

        mManager = getAssets();
        SkinsAdapter adapter = new SkinsAdapter(listImage(), mManager, mItemSelected);
        mRecyclerView.setAdapter(adapter);

        setSupportActionBar(mToolbar);
    }

    public String[] listImage(){
        try{
            String [] list = mManager.list("thumb");
            String [] sortlist = new String[list.length];
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (String num : list){
                String [] number = num.split(".png");
                arrayList.add(Integer.parseInt(number[0]));
            }

            Collections.sort(arrayList);

            for (int i = 0; i< arrayList.size(); i++){
                sortlist[i] = arrayList.get(i) + ".png";
            }
            return sortlist;
        }
        catch (IOException e){
         return new String[0];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
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
