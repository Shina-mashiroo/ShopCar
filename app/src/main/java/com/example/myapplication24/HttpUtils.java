package com.example.myapplication24;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class HttpUtils {
    public static String getStringResult(String path) {
        try {
            URL url=new URL(path);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200)
            {
                InputStream inputStream=httpURLConnection.getInputStream();
                byte[] buffer=new byte[1024];
                int len=0;
                StringBuilder stringBuilder=new StringBuilder();
                while ((len=inputStream.read(buffer))!=-1)
                {
                    stringBuilder.append(new String(buffer,0,len));
                }
                return  stringBuilder.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 向网络加载图片, 并且返回Bitmap
     * @param imagePath
     * @return
     */
    public static Bitmap getBitmap(String imagePath) {
        try {
            URL url = new URL(imagePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() == 200)
            {
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
                return bitmap;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    /**
     * 向网络获取byte[] 类型的数据
     * @param path 路径
     * @return
     */
    public static byte[] getByteResult(String path)
    {
        HttpURLConnection conn  = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200)
            {
                InputStream is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer))!=-1)
                {
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return  baos.toByteArray();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
            if (baos!=null)
            {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }
}