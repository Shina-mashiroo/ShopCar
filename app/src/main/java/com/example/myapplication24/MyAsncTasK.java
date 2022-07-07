package com.example.myapplication24;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.nio.file.Path;
import java.util.List;

public class MyAsncTasK extends AsyncTask<String,Void,String> {
    Context context;
   // ListView listView;
    PullToRefreshListView pullToRefreshListView;
    int page= 1;
    public MyAsncTasK(Context context, PullToRefreshListView pullToRefreshListView) {
        this.context = context;
       // this.listView = listView;
        this.pullToRefreshListView = pullToRefreshListView;
    }

    @Override
    protected String doInBackground(String... strings) {
        String path = strings[0];
        String json = HttpUtils.getStringResult(path);
        Log.i("--json",json);

        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s!=null){

            Gson gson = new Gson();
            JavaBean javaBean = gson.fromJson(s,JavaBean.class);
            final List<JavaBean.DataBean> dataBeans = javaBean.getData();
            for (int i=0;i<dataBeans.size();i++){
                JavaBean.DataBean dataBean = dataBeans.get(i);
                String title = dataBean.getTitle();
                Log.i("--title",title);
            }
            final MyBasrAdapter myBasrAdapter = new MyBasrAdapter(context,dataBeans);
           // listView.setAdapter(myBasrAdapter);
            pullToRefreshListView.setAdapter(myBasrAdapter);
            pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
            pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    page++;
                    new MyAsncTasK(context,pullToRefreshListView).execute("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page="+page);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                }
            });
            ListView listView   = pullToRefreshListView.getRefreshableView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context,dataBeans.get(i-1).getTitle(),Toast.LENGTH_LONG).show();

                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                   dataBeans.remove(i-1);
                   myBasrAdapter.notifyDataSetChanged();
                    return true;
                }
            });

        }
    }
}
