package com.vortex.vortex;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vortex.vortex.models.newsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_array_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_new);
        setTitle("Новости VORTEX");

        ListView lv = (ListView) findViewById(R.id.lvArrayNews);
        TextView tv = (TextView) findViewById(R.id.tv);

        final String[] catNames = new String[]{
                "Новость 1", "Новость 2", "Новость 3", "Новость 4", "Новость 5"
        };

        new GetNewsArray(this, lv, tv).execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Activity_array_new.this, Activity_news1.class);
                startActivity(intent);

                Log.d("LOG_TAG", "itemClick: position = " + position + ", id = "
                        + id);
            }
        });
    }

    public class NewsAdapter extends ArrayAdapter{

        public List<newsModel> newsModelList;
        private int resource;
        private LayoutInflater inflater;

        public NewsAdapter(@NonNull Context context, int resource, @NonNull List<newsModel> objects) {
            super(context, resource, objects);
            newsModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent){

            if(convertView == null){
                convertView = inflater.inflate(resource, null);
            }

            TextView tvDate;
            TextView tvPreview;
            TextView tvNews;

            tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            tvPreview = (TextView) convertView.findViewById(R.id.tvPreview);
            tvNews = (TextView) convertView.findViewById(R.id.tvNews);

            tvDate.setText(newsModelList.get(position).getDate());
            tvPreview.setText(newsModelList.get(position).getPreview());
            tvNews.setText(newsModelList.get(position).getNews());
            return super.getView(position, convertView, parent);

        }
    }

    class GetNewsArray extends AsyncTask<String, Void, List<newsModel>> {
        private Context context;
        ListView lv;
        TextView tv;

        public GetNewsArray(Activity_array_new context, ListView lv, TextView tv) {
            this.context = context;
            this.lv = lv;
            this.tv = tv;
        }

        @Override
        protected List<newsModel> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                String link = "https://pk-vortex.ru/mobail-files/news/news.txt";
                URL url = new URL(link);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String resultArray = buffer.toString();

                JSONObject parentObject = new JSONObject(resultArray);
                JSONArray parenArray = parentObject.getJSONArray("newsArray");

                List<newsModel> nModelList = new ArrayList<>();
                for (int i = 0; parenArray.length() > i; i++) {
                    JSONObject finalyObject = parenArray.getJSONObject(i);
                    newsModel nModels = new newsModel();

                    nModels.setDate(finalyObject.getString("date"));
                    nModels.setPreview(finalyObject.getString("preview"));
                    nModels.setNews(finalyObject.getString("news"));
                    //nModels.setNews(finalyObject.getString("header"));

                    nModelList.add(nModels);
                }
                return nModelList;

            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(List<newsModel> result) {
            super.onPostExecute(result);
            Activity_array_new.NewsAdapter adapter = new Activity_array_new.NewsAdapter(context.getApplicationContext(), R.layout.rownewsarray, result );
            lv.setAdapter(adapter);
            //TODO need to set data to the list
        }
    }



}
