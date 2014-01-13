package com.lachongmedia.sol;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Unoken on 20/12/13.
 */
public class emo extends BaseAdapter {
    private Context context;

    public final int[] images = new int[] {R.drawable.emo_giay, R.drawable.emo_hoa,
        R.drawable.emo_like, R.drawable.emo_cuoi1, R.drawable.emo_cuoi2,
        R.drawable.emo_unlike, R.drawable.emo_lips, R.drawable.emo_votay,};

    public emo(Context c) {
        context = c;
    }

    public int getCount(){
        return images.length;
    }

    public Object getItem(int position){
        return images[position];
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(100,100));
        return imageView;
    }

}
