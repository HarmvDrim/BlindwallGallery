package com.example.harm.blindwallgallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class DetailActivity extends Activity {

    private Blindwall bl;
    private TextView author;
    private TextView material;
    private TextView address;
    private TextView photographer;
    private TextView description;
    private TextView numberOnMap;
    private ImageView imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        bl = (Blindwall) i.getSerializableExtra("BLINDITEM");

        author = findViewById(R.id.name_id);
        author.setText(bl.getName());

        material = findViewById(R.id.material_id);
        material.setText(bl.getMaterial());

        address = findViewById(R.id.address_id);
        address.setText(bl.getAddress());

        photographer = findViewById(R.id.photograph_id);
        photographer.setText(bl.getPhotographer());

        description = findViewById(R.id.description_id);
        description.setText(bl.getDescription());

        numberOnMap = findViewById(R.id.addressNumber_id);
        numberOnMap.setText(String.valueOf(bl.getAddressNumber()));

        imageUrl = findViewById(R.id.picture_id);
        new ImageLoader(imageUrl).execute(bl.getImageURL());
        Log.i(TAG, "Waarden gezet op detailscherm");

    }
}
