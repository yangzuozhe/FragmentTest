package com.example.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingSelectFragment extends Fragment {
    RecyclerView mRvSelectShopping;
    View mView;
    List<MyBean> mBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_shopping_select, container, false);
        initViews(mView);
        return mView;
    }

    public void initViews(View view) {
        //添加数据bean类
        mBeanList.add(new MyBean("A", "我是A"));
        mBeanList.add(new MyBean("B", "我是B"));
        mBeanList.add(new MyBean("C", "我是C"));
        mBeanList.add(new MyBean("D", "我是D"));
        mBeanList.add(new MyBean("E", "我是E"));
        mBeanList.add(new MyBean("F", "我是F"));
        mBeanList.add(new MyBean("G", "我是G"));
        mRvSelectShopping = view.findViewById(R.id.rvSelectShopping);
        MyAdapter myAdapter = new MyAdapter(mBeanList);
        //调用Adapter 的回调方法 点击这里面的项，进行传值
        myAdapter.sendData(new MyCallBack() {
            @Override
            public void sendDataToActivity(MyBean myBean) {
                //碎片向活动赋值的过程
                FragmentActivity activity = getActivity();
                if (activity instanceof ShoppingActivity) {
                    //调用活动的方法 sendFragment2()；
                    ((ShoppingActivity) activity).sendFragment2(myBean);
                }
            }
        });
        //给RecyclerView设置属性
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRvSelectShopping.setLayoutManager(manager);
        mRvSelectShopping.setAdapter(myAdapter);

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        List<MyBean> mBeanList;
        //这个接口是 内部的Adapter类 和 外部进行联系的接口
        MyCallBack mMyCallBack;

        public MyAdapter(List<MyBean> beanList) {
            mBeanList = beanList;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvRecyclerTextView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvRecyclerTextView = itemView.findViewById(R.id.tvRecyclerTextView);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            final MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final MyBean bean = mBeanList.get(position);
            holder.tvRecyclerTextView.setText(bean.getSelectName());
            //点击某一个item就会 调用 mMyCallBack 的 方法 sendDataToActivity()。
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyCallBack.sendDataToActivity(bean);
                }
            });
        }

        /**
         * 这是Adapter 的 回调方法
         *
         * @param myCallBack 回调接口
         */
        public void sendData(MyCallBack myCallBack) {
            mMyCallBack = myCallBack;
        }

        @Override
        public int getItemCount() {
            return mBeanList.size();
        }

    }

    /**
     * 这是我们定义的和Adapter外界联系的接口
     */
    public interface MyCallBack {
        void sendDataToActivity(MyBean myBean);
    }


}
