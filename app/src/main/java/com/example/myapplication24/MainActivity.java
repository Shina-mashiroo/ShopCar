package com.example.myapplication24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends AppCompatActivity {
//    ListView listView;
    PullToRefreshListView pullToRefreshListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = findViewById(R.id.lv);
        pullToRefreshListView = findViewById(R.id.pf);
      //  new MyAsncTasK(MainActivity.this,listView).execute("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1");
        new MyAsncTasK(MainActivity.this,pullToRefreshListView).execute("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1");
    }
}
