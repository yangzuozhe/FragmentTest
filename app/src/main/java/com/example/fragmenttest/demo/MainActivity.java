package com.example.fragmenttest.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmenttest.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyFragment mMyFragment;
    TextView mTvMoveText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_fragment);
        initView();
        //首先我们获取Fragment 的对象
        mMyFragment = MyFragment.newInstance();
        //将我们的Fragment试图添加到 父布局中显示出来
        replaceFragment(mMyFragment, R.id.moveFrameLayout);
    }

    /**
     * 获取从 Fragment 传来的数据name
     * @param name
     */
    public void getFragmentFun(String name){
        mTvMoveText.setText(name);
    }

    public void initView() {
        mTvMoveText = findViewById(R.id.tvMoveText);
        mTvMoveText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvMoveText) {
            //在点击事件这里调用方法
            //通过 Fragment 的引用
//            sendDataToFragment();
            mMyFragment.getData(new MyFragment.CallBack() {
                @Override
                public void getResult(String result) {
                    Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                }
            });
//            //通过 findFragmentByTag
//            sendDataWidthTag();
        }
    }

    /**
     * 如果在有 Fragment 的对象的情况下
     */
    public void sendDataToFragment() {
        mMyFragment.getDataFromActivity("通过Fragment的调用方法");
    }

    /**
     * 如果没有获取到 Fragment 的对象也没有关系，可以通过 findFragmentByTag 来获取 Fragment 的对象，再调用Fragment 的方法。
     */
    public void sendDataWidthTag() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag("MyFragment");
        if (fragmentByTag instanceof MyFragment) {
            ((MyFragment) fragmentByTag).getDataFromActivity("通过");
        }
    }

    public void replaceFragment(Fragment fragment, int parentId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(parentId, fragment, "MyFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(Fragment fragment, int parentId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(parentId, fragment, "MyFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 静态Fragment
     */
    public void staticFragment() {
        //获取FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //通过 findFragmentById 获取 Fragment
        Fragment fragment = manager.findFragmentById(R.id.fgMy);
        if (fragment != null) {
            //如果想在Activity中获取Fragment中的View
            View view = fragment.getView();
            //然后获得这个view 就可以获取view中的 控件
            TextView textView = view.findViewById(R.id.tvMyFragment);
            Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }


}