package com.stellarloyalty.ir;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private RecyclerView mListMenu;

    private LinearLayoutManager mLayoutManager;

    private TypedArray mMenuItems;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);

        mListMenu = (RecyclerView) findViewById(R.id.rv_table);
        mListMenu.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mListMenu.setLayoutManager(mLayoutManager);

        mMenuItems = getResources().obtainTypedArray(R.array.menu_items);
        mMenuAdapter = new MenuAdapter(MainActivity.this, mMenuItems);
        mListMenu.setAdapter(mMenuAdapter);
        mListMenu.addOnItemTouchListener(new MenuAdapter.MenuClickListener(MainActivity.this, MainActivity.this));



    }

    @Override
    public void onItemClick(View view, int position) {
        switch ((int) view.getTag()) {
            case R.string.menu_vuforia: {
                break;
            }

            case R.string.menu_catchoom: {
                Intent catchoomIntent = new Intent(MainActivity.this, CatchoomActivity.class);
                startActivity(catchoomIntent);
                break;

            }

            case R.string.menu_moodstocks: {
                break;
            }
        }

    }
}
