package com.example.myapplication24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.serializer.LabelFilter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyBasrAdapter extends BaseAdapter {
    Context context;
    List<JavaBean.DataBean>dataBeans;

    public MyBasrAdapter(Context context, List<JavaBean.DataBean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }


    @Override
    public int getCount() {

        return dataBeans.size();
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
        ViowHolder viowHolder = new ViowHolder();
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item,null);
            viowHolder.imageView = view.findViewById(R.id.ivv);
            viowHolder.textView = view.findViewById(R.id.tv);
            view.setTag(viowHolder);
        }else{
            viowHolder = (ViowHolder) view.getTag();

        }
        viowHolder.textView.setText(dataBeans.get(i).getTitle());
        Picasso.with(context).load(dataBeans.get(i).getPic()).into(viowHolder.imageView);
        return view;
    }
    public class  ViowHolder{
        ImageView imageView;
        TextView textView;
    }
}
