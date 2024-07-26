package com.example.android_food.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.android_food.R;
import com.example.android_food.ui.entity.tblCategoryEntity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

private ArrayList<tblCategoryEntity> listData;
private LayoutInflater layoutInflater;
private Context context;


public class HomeGridViewAdapter extends BaseAdapter {

    public HomeGridViewAdapter(Context aContext, ArrayList<tblCategoryEntity> listData) {
        this.context = aContext;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        if (listData != null) {
            return  listData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView grid_item_image;
        int id;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.home_gridview_item, null);
            holder = new HomeGridViewAdapter.ViewHolder();
            holder.grid_item_image = (ImageView) convertView.findViewById(R.id.grid_item_image);

            holder.grid_item_image.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",holder.id);
                     Navigation.findNavController(v).navigate(R.id.action_nav_home_to_detailsFragment,bundle);
                }
            });

            convertView.setTag(holder);
        } else {
            //holder = (SearchListAdapter.ViewHolder) convertView.getTag();
        }

        tblCategoryEntity category = this.listData.get(position);
        holder.grid_item_image.setImageBitmap(getBitmapFromURL(category.getUrl()));
        holder.id = category.getId();

        return convertView;

    }
}
