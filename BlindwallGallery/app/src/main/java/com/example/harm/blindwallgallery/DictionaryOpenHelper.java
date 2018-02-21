package com.example.harm.blindwallgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by harm on 21-2-2018.
 */

public class DictionaryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_TABLE_NAME = "BlindwallGallary";

    private String material;
    private String address;
    private String description;
    private String photographer;
    private String imageURL;
    private int addressNumber;

    //Tabel en kolom namen
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_MATERIAL = "material";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PHOTOGRAPHER = "photographer";
    private static final String COLUMN_IMAGEURL = "imageURL";
    private static final String COLUMN_ADDRESSNUMBER = "addressNumber";


    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_MATERIAL + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_PHOTOGRAPHER + " TEXT, " +
                    COLUMN_IMAGEURL + " TEXT, " +
                    COLUMN_ADDRESSNUMBER + "INTEGER);";


    public DictionaryOpenHelper(Context context) {
        super(context, DATABASE_TABLE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Create NEW db");
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, "onUpgrade - DROPPING EXISTING DATABASE");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);

    }

    public void addBlindwall(Blindwall blindwall){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, blindwall.getName());
        values.put(COLUMN_MATERIAL, blindwall.getMaterial());
        values.put(COLUMN_ADDRESS, blindwall.getAddress());
        values.put(COLUMN_DESCRIPTION, blindwall.getDescription());
        values.put(COLUMN_PHOTOGRAPHER, blindwall.getPhotographer());
        values.put(COLUMN_IMAGEURL, blindwall.getImageURL());
        values.put(COLUMN_ADDRESSNUMBER, blindwall.getAddressNumber());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DATABASE_TABLE_NAME, null, values);
        db.close();
    }
}
