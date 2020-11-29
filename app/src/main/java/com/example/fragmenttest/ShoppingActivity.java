package com.example.fragmenttest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShoppingActivity extends AppCompatActivity {
    ShoppingSelectFragment mSelectFragment;
    ShoppingFragment mShoppingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        //这里由于我们不赋值，因此我们这里不使用 newInstance()也可以
        mSelectFragment = new ShoppingSelectFragment();
        mShoppingFragment = new ShoppingFragment();
        //动态添加碎片
        replaceFragment(R.id.flSelect, mSelectFragment);
        replaceFragment(R.id.flShoppingFragment, mShoppingFragment);
    }

    /**
     * 动态添加碎片
     *
     * @param parentId 碎片的父布局容器
     * @param fragment 需要动态添加的碎片
     */
    public void replaceFragment(int parentId, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(parentId, fragment);
        transaction.commit();
    }

    /**
     * 发送数据到 Fragment2 中
     */
    public void sendFragment2(MyBean myBean) {
        mShoppingFragment.onBindView(myBean);
    }
}