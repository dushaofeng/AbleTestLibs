package com.able.libs;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.recycler_adapter_layout)
public class RecyclerAdapterTestActivity extends AppCompatActivity {
    @ViewById(R.id.tv)
    TextView mTv;
    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerview;
    static List<Integer> sNumberIconList = new ArrayList<>();

    static {
        sNumberIconList.add(1);
        sNumberIconList.add(2);
        sNumberIconList.add(3);
        sNumberIconList.add(10);
        sNumberIconList.add(4);
        sNumberIconList.add(5);
        sNumberIconList.add(6);
        sNumberIconList.add(11);
        sNumberIconList.add(7);
        sNumberIconList.add(8);
        sNumberIconList.add(9);
        sNumberIconList.add(12);
        sNumberIconList.add(13);
        sNumberIconList.add(0);
        sNumberIconList.add(14);
        sNumberIconList.add(15);
    }

    @AfterViews
    void onAfterViews() {
        RecyclerAdapter adapter = new RecyclerAdapter(R.layout.recycler_layout, sNumberIconList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTv.setText("pos:" + position);
            }
        });
        mRecyclerview.setLayoutManager(gridLayoutManager);
        mRecyclerview.setAdapter(adapter);
    }

    class RecyclerAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

        RecyclerAdapter(int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            helper.setText(R.id.recycler_tv, "ttt:"+item);
        }

    }
}
