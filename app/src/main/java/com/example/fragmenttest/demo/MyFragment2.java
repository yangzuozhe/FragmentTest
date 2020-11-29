package com.example.fragmenttest.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmenttest.R;

public class MyFragment2 extends Fragment {
    public String name;
    public static final String RESULT = "00000";
    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString(RESULT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment2_my, null);
        textView = view.findViewById(R.id.tttt);
        return view;
    }

    public static MyFragment2 newInstance() {

        Bundle args = new Bundle();

        MyFragment2 fragment = new MyFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    public static MyFragment2 newInstance(String result) {
        Bundle args = new Bundle();
        args.putString(RESULT, result);
        MyFragment2 fragment = new MyFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Activity 中转站的值传到 本Fragment2 中
     *
     * @param data
     */
    public void setData(String data) {
        textView.setText(data);
    }


}
