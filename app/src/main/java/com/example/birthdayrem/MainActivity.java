package com.example.birthdayrem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birthdayrem.lili.factory.BirthdayDaoFactory;
import com.example.birthdayrem.lili.pojo.Birthday;
import com.example.birthdayrem.lili.util.DateUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private List<Birthday> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.e("lili","oncreate");
        list = getDate();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到新页面
                Intent intent = new Intent(MainActivity.this,InfoActivity.class);
                intent.putExtra("_id",list.get(i).get_id());
                startActivityForResult(intent,0);
            }
        });
        Log.e("lili","size="+list.size());
        for (Birthday b:list){
            Log.e("lili",b.toString());
        }
    }



    public void init(){
        listView = (ListView)findViewById(R.id.listview);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,0);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            try {
                list = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(this).selectAllBirthday();
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Birthday> getDate(){
        List<Birthday> list = null;
        try {
            list = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(this).selectAllBirthday();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item,null);
            TextView textView1 = (TextView)myView.findViewById(R.id.listview_item_textview1);
            TextView textView2 = (TextView)myView.findViewById(R.id.listview_item_textview2);
            textView1.setText(list.get(i).getName());
            textView2.setText(DateUtil.formatDate(list.get(i).getYear(),list.get(i).getMonth(),list.get(i).getDay()));
            return myView;
        }
    }
}
