package com.example.alfinda.modul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class OpsiMenu extends AppCompatActivity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {

            case R.id.menuListMakanan:
                mIntent = new Intent(this, LayarListMakanan.class);
                startActivity(mIntent);
                return true;
            case R.id.menuListUser:
                mIntent = new Intent(this, LayarListUser.class);
                startActivity(mIntent);
                return true;
            case R.id.menuListSuka:
                mIntent = new Intent(this, LayarListSuka.class);
                startActivity(mIntent);
                return true;
            case R.id.logout:
                SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.clear();
                editor.apply();

                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
