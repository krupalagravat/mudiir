package com.mudiir;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Login_screen extends AppCompatActivity {
    private final static int NUM_PAGES = 3;
    ViewPager mViewPager;
    private List<ImageView> dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));
        addDots();
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        selectDot(0);
    }

    public void addDots() {
        dots = new ArrayList<>();
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.dots);

        for (int i = 0; i < NUM_PAGES; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.not_selected_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);
            dotsLayout.addView(dot, params);

            dots.add(dot);
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < NUM_PAGES; i++) {
            int drawableId = (i == idx) ? (R.drawable.selected_dot) : (R.drawable.not_selected_dot);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Login_Layout_Fragment();
            } else if (position == 1) {
                return new Login_Layout_Fragment();
            } else
                return new Login_Layout_Fragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
