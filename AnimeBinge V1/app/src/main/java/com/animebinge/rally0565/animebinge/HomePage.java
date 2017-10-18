/*
 * Programmer: Ryan Ally
 * Course: PROG3210
 * Student ID: 7260565
 * Assignment 1: AnimeBinge
 */

package com.animebinge.rally0565.animebinge;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class HomePage extends AppCompatActivity {

    private ListView lvMenu;
    private ArrayAdapter<String> aAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //lvMenu = (ListView) findViewById(R.id.lvMenu);
        //addMenuItems();
    }
    //http://blog.teamtreehouse.com/add-navigation-drawer-android -- Use Later
//    private void addMenuItems() {
//        String[] menuArray = {"Profile", "Settings", "Sign Out"};
//        aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
//        lvMenu.setAdapter(aAdapter);
//    }
}
