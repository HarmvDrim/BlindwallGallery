package com.example.harm.blindwallgallery;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by harm on 20-2-2018.
 */

public class BlindwallAsyncTask extends AsyncTask<String, Void, String>{

    private BlindwallListener listener;

    public BlindwallAsyncTask(BlindwallListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader bufferedReader = null;
        String response = null;
        String url2 = "https://api.blindwalls.gallery/apiv2/murals";
        Log.e("exec", "bla");
        try {
            URL url = new URL(url2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            response = bufferedReader.readLine().toString();
            String line;
            while( (line = bufferedReader.readLine()) != null) {
                response += line;
            }
        } catch (Exception e) {
            Log.e("exec", "ConnectionException");
            return null;
        } finally {
            if( bufferedReader != null ) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    Log.e("exec", "bufferedReaderException");
                    return null;
                }
            }
        }
        return response;
    }

    protected void onPostExecute(String response){
        Log.e("exec", "exec1");
        if( response == null ) {
            Log.e("exec", "response0");
            return;
        }
        try {
            JSONArray BlindwallJSONArry = new JSONArray(response);
            Log.e("exec", "exec2");
            for( int idx = 0; idx < BlindwallJSONArry.length(); idx++) {
                JSONObject blindwall = BlindwallJSONArry.getJSONObject(idx);

                JSONObject title = blindwall.getJSONObject("title");
                String stringTitle = title.getString("nl");

                JSONObject material = blindwall.getJSONObject("material");
                String stringMaterial = material.getString("nl");

                String stringAddress = blindwall.getString("address");

                String stringPhotographer = blindwall.getString("photographer");

                JSONObject description = blindwall.getJSONObject("description");
                String stringDescription = description.getString("nl");

                JSONArray picture = blindwall.getJSONArray("images");
                JSONObject pictureObject = picture.getJSONObject(0);
                String stringPicture = pictureObject.getString("url");

                stringPicture = "https://api.blindwalls.gallery/" + stringPicture;

                int addressNumber = blindwall.getInt("numberOnMap");

                Blindwall bla = new Blindwall(
                        stringTitle, stringMaterial,stringAddress,
                        stringDescription,stringPhotographer, stringPicture,addressNumber);
                Log.e("exec", "exec3");
                this.listener.onBlindwallAvailable(bla);

                Log.d("","");
            }

            Log.d("","");

        } catch(Exception e) {
            Log.e("","");
        }
    }
}
