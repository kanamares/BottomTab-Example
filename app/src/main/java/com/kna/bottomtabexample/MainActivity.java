package com.kna.bottomtabexample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
	    mBottomBar.useFixedMode();
	    mBottomBar.setActiveTabColor("#cc0000");

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
	            getSupportFragmentManager().beginTransaction()
			            .replace(R.id.container, BlankFragment.newInstance(getMessage(menuItemId)))
			            .commit();
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

	    mBottomBar.selectTabAtPosition(2, false);
    }

    private String getMessage(int menuItemId) {
        switch (menuItemId) {
            case R.id.bb_menu_recents:
                return "recents";
            case R.id.bb_menu_favorites:
                return "favorites";
            case R.id.bb_menu_nearby:
                return "nearby";
            case R.id.bb_menu_friends:
	            return  "friends";
            case R.id.bb_menu_food:
	            return  "food";
            default:
	            return "";
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }
}
