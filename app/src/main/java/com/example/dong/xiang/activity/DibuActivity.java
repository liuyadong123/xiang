package com.example.dong.xiang.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dong.xiang.R;
import com.example.dong.xiang.fragment.DisFragment;
import com.example.dong.xiang.fragment.HomeFragment;
import com.example.dong.xiang.fragment.NavFragment;
import com.example.dong.xiang.fragment.WenFragment;
import com.example.dong.xiang.fragment.WoFragment;

public class DibuActivity extends AppCompatActivity {

    private ViewPager pager;
    private HomeFragment homeFragment;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_shang:
                    pager.setCurrentItem(3);
                    return true;
                case R.id.navigation_wo:
                    pager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibu);
        pager=findViewById(R.id.pager);
        navigation=findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:return new HomeFragment();
                    case 1:return new DisFragment();
                    case 2:return new NavFragment();
                    case 3:return new WenFragment();
                    case 4:return new WoFragment();
                }
                return null;
            }
            @Override
            public int getCount() {
                return 5;
            }
        });
        homeFragment =new HomeFragment();
        TextView mTextMessage = (TextView) findViewById(R.id.message);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:navigation.setSelectedItemId(R.id.navigation_home);break;
                    case 1:navigation.setSelectedItemId(R.id.navigation_dashboard);break;
                    case 2:navigation.setSelectedItemId(R.id.navigation_notifications);break;
                    case 3:navigation.setSelectedItemId(R.id.navigation_shang);break;
                    case 4:navigation.setSelectedItemId(R.id.navigation_wo);break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean ret = false;
        if (!ret) {
            ret = homeFragment.onKeyDown(event);//这里的homeFragment是我们前的Fragment
        }
        if (!ret){
            ret = activityParseOnkey(keyCode);
        }
        return ret;
    }

    private boolean activityParseOnkey(int keyCode) {
        final boolean[] ret = {false};
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                break;
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this)
                        .setTitle("提示框")
                        .setMessage("确认关闭?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                ret[0] =true;
                            }
                        }).create().show();
                break;
        }
        return ret[0];
    }
*/

}
