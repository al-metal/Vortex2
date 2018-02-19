package com.vortex.vortex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vortex.vortex.models.newsModel;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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

import static android.view.View.INVISIBLE;

public class testNews extends AppCompatActivity {

    ListView lv;
    ProgressBar pgLoadNews;
    TextView tvProgressBerText;
    List<newsModel> newsModelList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_news);

        lv = (ListView) findViewById(R.id.lvNewsArray);
        tvProgressBerText = (TextView) findViewById(R.id.tvProgressBerText);
        pgLoadNews = (ProgressBar) findViewById(R.id.pgLoadNews);

        new NewsTask().execute("https://pk-vortex.ru/mobail-files/news/news.txt");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                newsModel itemModel = newsModelList.get(position);
                String idModel = String.valueOf(itemModel.getId());

                Intent intent = new Intent(testNews.this, Activity_news1.class);
                intent.putExtra("newsModel", new newsModel(itemModel.getId(), itemModel.getHeader(), itemModel.getDate(), itemModel.getPreview(), itemModel.getNews()));
                startActivity(intent);
            }
        });
    }

    public class NewsTask extends AsyncTask<String, String, List<newsModel>> {

        @Override
        protected List<newsModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

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
                    model.setId(finalObjeckt.getInt("id"));

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
            pgLoadNews.setVisibility(View.GONE);
            tvProgressBerText.setVisibility(View.GONE);
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

            TextView tvDate;
            TextView tvPreview;
            TextView tvHeader;

            tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            tvPreview = (TextView) convertView.findViewById(R.id.tvPreview);
            tvHeader = (TextView) convertView.findViewById(R.id.tvHeader);

            tvDate.setText(newsModelList.get(position).getDate());
            tvHeader.setText(newsModelList.get(position).getHeader());
            tvPreview.setText(newsModelList.get(position).getPreview());

            return convertView;
        }
    }
}
