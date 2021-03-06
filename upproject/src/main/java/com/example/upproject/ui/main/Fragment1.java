package com.example.upproject.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.upproject.ui.main.ui_ietm_list.FirstListItem;
import com.example.upproject.ui.main.ui_ietm_list.ListIitem5;
import com.example.upproject.ui.main.ui_ietm_list.ListItem2;
import com.example.upproject.ui.main.ui_ietm_list.Listitem4;
import com.example.upproject.R;
import com.example.upproject.ui.main.ui_ietm_list.SecondListItem;
import com.example.upproject.ui.main.ui_ietm_list.ThirdListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cjs on 2016/3/2.
 */
//存放第一个listview，显示电器清单信息
public class Fragment1 extends Fragment {
    private View view;
    private ListView mlistview;
    private SimpleAdapter adapter;
    private List<HashMap<String, Object>> data_hashMap;
    private List<HashMap<String, Object>> mHashMap;
    private HashMap<String, Object> map;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mlistview.setAdapter(adapter);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab01, container, false);
        return view;
    }

    //   实现页面一清单的点击事件
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mlistview = (ListView) view.findViewById(R.id.list);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), FirstListItem.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), ListItem2.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), ThirdListItem.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getActivity(), Listitem4.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(getActivity(), ListIitem5.class);
                        startActivity(intent5);
                        break;
                    default:
                        Toast.makeText(getActivity(), "default", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
               new MyThread().start();
    }




//    新开一个线程避免阻塞UI
    class MyThread extends Thread {
        @Override
        public void run() {
            if (data_hashMap==null) {
                data_hashMap = getData();
            }
            if (adapter==null) {
                adapter = new SimpleAdapter(getActivity(), data_hashMap,
                        R.layout.item, new String[]{"image", "title"}, new int[]
                        {R.id.item, R.id.tv_title});
            }
            handler.sendEmptyMessage(0x123);
        }
    //adapter的数据源
    public List<HashMap<String, Object>> getData() {
        mHashMap = new ArrayList<HashMap<String, Object>>();
        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.kongtiao);
        map.put("title", "空调");
        mHashMap.add(map);


        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.light);
        map.put("title", "电灯");
        mHashMap.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.wash);
        map.put("title", "洗衣机");
        mHashMap.add(map);


        map = new HashMap<String, Object>();
        map.put("image",  R.mipmap.elecar);
        map.put("title", "充电汽车");
        mHashMap.add(map);


        map = new HashMap<String, Object>();
        map.put("image", R.mipmap.dishwash);
        map.put("title", "洗碗机");
        mHashMap.add(map);


        return mHashMap;
    }
    }
}

