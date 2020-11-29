package com.example.fragmenttest.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.fragmenttest.R;

public class MyFragment extends Fragment implements View.OnClickListener {
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my, container, false);
        mTextView = view.findViewById(R.id.tvMyFragment);
        mTextView.setOnClickListener(this);
        return view;
    }

    /**
     * 一般我们不推荐实例化Fragment来获取 MyFragment 的对象，推荐用 newInstance() 或者 getInstance()。
     *
     * @return 返回Fragment 的对象
     */
    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void getData(CallBack callBack) {
        callBack.getResult("这是从Fragment通过回调接口调用方法来Activity的");
    }

    /**
     * 定义一个接口
     */
    public interface CallBack {
        public void getResult(String result);
    }

    public void getDataFromActivity(String data) {
        mTextView.setText(data);
    }

    /**
     * 将我们的数据发向 Activity 方法
     * @param data 数据
     */
    public void getDataToOtherFragment(String data){
        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity2){
            ((MainActivity2) activity).sendToOtherFragment(data);
        }
    }

    public void sendDataToActivity(){
        FragmentActivity fragmentActivity = getActivity();
        if (fragmentActivity instanceof MainActivity){
            ((MainActivity) fragmentActivity).getFragmentFun("这是从Fragment调用方法来Activity的");
        }
    }


    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvMyFragment){
            getDataToOtherFragment("Fragment1 传值给 Fragment2");
        }
    }



}

//    /**
//     * onCreate 的生命周期在 onCreateView 之前，因此我们在这里获取数据。
//     */
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            mName = bundle.getString(NAME_KEY);
//        }
//    }
//    /**
//     * 通过这个方法传值，并且获取Fragment的对象
//     */
//    public static MyFragment newInstance(String name) {
//        Bundle args = new Bundle();
//        args.putString(NAME_KEY, name);
//        MyFragment fragment = new MyFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }


