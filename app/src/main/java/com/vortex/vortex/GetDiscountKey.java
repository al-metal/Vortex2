package com.vortex.vortex;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by KonyshevAM on 15.11.2017.
 */

public class GetDiscountKey extends AsyncTask<Void, Void, String> {

    private TextView text;
    private Context context;

    public GetDiscountKey(Context context, TextView text) {
        this.context = context;
        this.text = text;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {

            String link = "http://training.vortex-avto.ru/android/news/getnews.php";
            String data = URLEncoder.encode("id", "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;


            //Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();

        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    protected void onPostExecute(String result) {
        this.text.setText(Html.fromHtml(result));
    }

}
