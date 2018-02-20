package com.example.harm.blindwallgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import static android.content.ContentValues.TAG;

/**
 * Created by harm on 20-2-2018.
 */

public class ListViewAdapter extends ArrayAdapter<Blindwall> {

    public ListViewAdapter(Context context, ArrayList<Blindwall> blindwalls) {
        super(context, 0,blindwalls);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Blindwall blindwall = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(
                            R.layout.lv_row,parent,false);
        }

        TextView name = convertView.findViewById(R.id.auteur_id);
        ImageView picture = convertView.findViewById(R.id.plaatje_id);

        name.setText(blindwall.getName());

        try {
            Log.e("Image", "image: " + blindwall.getImageURL());
            URL url = new URL(blindwall.getImageURL());
            try {
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                picture.setImageBitmap(bmp);

            }catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        }
        return convertView;
    }
}
