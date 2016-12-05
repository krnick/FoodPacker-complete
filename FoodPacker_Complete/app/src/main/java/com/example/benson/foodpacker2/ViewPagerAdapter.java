package com.example.benson.foodpacker2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Benson on 2016/10/13.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;


    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();
    public void addFragment(Fragment fragments, String titles){
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }

    public ViewPagerAdapter(FragmentManager fm/*, Context context*/){
        super(fm);
        //this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        //return tabTitles.get(position);
        return null;
        /*
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
        */
    }
}
