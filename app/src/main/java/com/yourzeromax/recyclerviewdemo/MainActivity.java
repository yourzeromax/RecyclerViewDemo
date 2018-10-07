package com.yourzeromax.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.yourzeromax.recyclerviewdemo.Bean.GroupInfo;
import com.yourzeromax.recyclerviewdemo.Bean.Student;
import com.yourzeromax.recyclerviewdemo.adapter.MyAdapter;
import com.yourzeromax.recyclerviewdemo.decoration.CustomDecoration;
import com.yourzeromax.recyclerviewdemo.decoration.StickyDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerView;

    Button btnAdd, btnRemove, btnChange, btnMove;

    List<Student> students = new ArrayList<>();

    MyAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    RecyclerView.ItemAnimator mItemAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListener();

        initLists(20);
        initAdapter();
        initLayoutManager();
        initAnimator();

        //初始化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(mItemAnimator);

        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new CustomDecoration());

//        mRecyclerView.addItemDecoration(new StickyDecoration(new StickyDecoration.CallBack() {
//            @Override
//            public GroupInfo getGroupInfo(int position) {
//                /**
//                 * 在这里根据position来获得Item的GroupInfo描述
//                 * 需要在项目中根据需求进行替换
//                 */
//                int groupId = position / 4;
//                int index = position % 4;
//                GroupInfo groupInfo = new GroupInfo(groupId, groupId + "");
//                groupInfo.setPosition(index);
//                groupInfo.setGroupCount(4);
//                return groupInfo;
//            }
//        }));
    }

    private void initListener() {
        btnAdd = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        btnMove = findViewById(R.id.btn_move);
        btnChange = findViewById(R.id.btn_change);
        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnMove.setOnClickListener(this);
        btnChange.setOnClickListener(this);
    }

    /**
     * 装填模拟数据
     */
    private void initLists(int num) {
        for (int i = 0; i < num; i++) {
            students.add(new Student(20144200 + i, "name " + i));
        }
    }

    /**
     * 装填适配器Adapter
     */
    private void initAdapter() {
        mAdapter = new MyAdapter(this, students, R.layout.item_sample);
    }


    /**
     * 装填布局管理器
     */
    private void initLayoutManager() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }


    /**
     * 装填Item的动画效果
     */
    private void initAnimator() {
        mItemAnimator = new DefaultItemAnimator();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_remove:
                break;
            case R.id.btn_move:
                break;
            case R.id.btn_change:
                break;
            default:
        }
    }
}
