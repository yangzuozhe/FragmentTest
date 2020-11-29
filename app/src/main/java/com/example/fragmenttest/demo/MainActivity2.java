package com.example.fragmenttest.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmenttest.R;

public class MainActivity2 extends AppCompatActivity {
    MyFragment2 t2;
    MyFragment t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //获取 MyFragment 的对象
        t1 = MyFragment.newInstance();
        //将 MyFragment 放进 FrameLayout 父布局
        replaceFragment(t1, R.id.fgOne);
        t2 = MyFragment2.newInstance();
        replaceFragment(t2, R.id.fgTwo);

//        //这里我们注意我们使用了 MyFragment 的回调方法，在这个方法里我们将 MyFragment 的数据传到 MyFragment2 中
//        t1.getData(new MyFragment.CallBack() {
//            @Override
//            public void getResult(String result) {
//                //获取 MyFragment2 的实例，并且将数据存进去
//
//            }
//        });
    }

    /**
     * 通过获取对象的方法，来获取Fragment1的数值，再将数据传进Fragment2
     *
     * @param data
     */
    public void sendToOtherFragment(String data) {
        t2.setData(data);
    }

    /**
     * 动态添加碎片
     */
    public void replaceFragment(Fragment fragment, int parentId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(parentId, fragment, "MyFragment");
        transaction.commit();
    }

}