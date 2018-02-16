package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vortex.vortex.models.newsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class testNews extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_news);

        lv = (ListView) findViewById(R.id.lvNewsArray);

        new NewsTask().execute("https://pk-vortex.ru/mobail-files/news/news.txt");
    }

    public class NewsTask extends AsyncTask<String, String, List<newsModel>> {

        @Override
        protected List<newsModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            List<newsModel> newsModelList = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                String finalJSON = buffer.toString();

                JSONObject parentObjeckt = new JSONObject(finalJSON);
                JSONArray parentArray = parentObjeckt.getJSONArray("newsArray");

                newsModelList = new ArrayList<>();
                for (int i = 0; parentArray.length() > i; i++) {
                    JSONObject finalObjeckt = parentArray.getJSONObject(i);
                    newsModel model = new newsModel();

                    model.setDate(finalObjeckt.getString("date"));
                    model.setPreview(finalObjeckt.getString("preview"));
                    model.setNews(finalObjeckt.getString("news"));
                    model.setHeader(finalObjeckt.getString("head"));

                    newsModelList.add(model);
                }

                if (newsModelList == null) {

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return newsModelList;
        }

        @Override
        protected void onPostExecute(List<newsModel> result) {
            super.onPostExecute(result);
            NewsAdapter adapter = new NewsAdapter(getApplicationContext(), R.layout.rownewsarray, result);
            lv.setAdapter(adapter);
        }
    }

    public class NewsAdapter extends ArrayAdapter {

        private List<newsModel> newsModelList;
        private int resource;
        private LayoutInflater inflater;

        public NewsAdapter(@NonNull Context context, int resource, @NonNull List<newsModel> objects) {
            super(context, resource, objects);
            newsModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView tvDate = null;
            TextView tvPreview;
            TextView tvNews;
            TextView tvHeader;

            tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            tvPreview = (TextView) convertView.findViewById(R.id.tvPreview);
            tvNews = (TextView) convertView.findViewById(R.id.tvNews);
            tvHeader = (TextView) convertView.findViewById(R.id.tvHeader);

            tvDate.setText(newsModelList.get(position).getDate());
            tvHeader.setText(newsModelList.get(position).getHeader());
            tvPreview.setText(newsModelList.get(position).getPreview());
            tvNews.setText(newsModelList.get(position).getNews());

            return convertView;
        }
    }
}
