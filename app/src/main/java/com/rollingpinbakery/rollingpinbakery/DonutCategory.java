package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;

public class DonutCategory extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView listView;
    ArrayList<Product> products;
    private static StoreProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(getBaseContext(), AdminProductAdd.class));
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            Intent editIntent = new Intent(this, MainActivity.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Store) {
            Intent editIntent = new Intent(this, Store.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Account) {
            Intent editIntent = new Intent(this, Account.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Register) {
            Intent editIntent = new Intent(this, Register.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Admin) {
            Intent editIntent = new Intent(this, AdminMainActivity.class);
            startActivity(editIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        String bread = "Bread";
        listView = findViewById(R.id.listView);
        products = (ArrayList<Product>) AppDatabase.getAppDatabase(this).productDao().getProductByType("Donut");
        adapter = new StoreProductAdapter(this, products);
        listView.setAdapter(adapter);
    }

}

