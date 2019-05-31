package com.example.hsh.homesweethome;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.hsh.homesweethome.Models.Furniture;

public class FurnitureFragmentAdapter extends FragmentStatePagerAdapter {
    int PAGES_COUNT = 3;
    private String pageTitles[] = new String[]{"Review", "AR", "Cart"};
    private Context context;
    private Furniture furniture;

    private String TAG = "Main Fragment";

    public FurnitureFragmentAdapter(FragmentManager fm, Furniture furniture) {
        super(fm);
        this.furniture = furniture;


    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 : return new FurnitureDetailsReviewFragment();
            case 1 : return new FurnitureDetailsMainFragment(furniture);
            case 2 : return new FurnitureDetailsCartFragment();
            default: return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
