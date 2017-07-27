package com.example.enchanter21.first;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView listv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        (ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        mActionBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
//        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //What to do on back clicked
//            }
//        });


        listv1=(ListView)findViewById(R.id.list1);


        String n1=getIntent().getStringExtra("msg");
        String n2=getIntent().getStringExtra("msg1");
        setTitle(n2);
        String RYLO=Global_Url.URI_CATEGORY1+n1;
        new Asyncm().execute(RYLO);

        }

    public class MovieAdap extends ArrayAdapter {
        private List<all_recps> movieModelList;
        private int resource;
        Context context;
        private LayoutInflater inflater;
        MovieAdap(Context context, int resource, List<all_recps> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.context =context;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getViewTypeCount() {
            return 1;
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if(convertView == null){
                convertView = inflater.inflate(resource,null);
                holder = new ViewHolder();
                holder.textnamed=(TextView)  convertView.findViewById(R.id.textView22);
                holder.textdescd=(TextView)  convertView.findViewById(R.id.textView33);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            all_recps ccitac=movieModelList.get(position);
            holder.textnamed.setText(ccitac.getTpcname());
            holder.textdescd.setText(ccitac.getShtdesc());
            return convertView;
        }
        class ViewHolder{
            public TextView textnamed,textdescd;
        }
    }
    public class Asyncm extends AsyncTask<String,String, List<all_recps>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<all_recps> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("result");
                List<all_recps> milokilo = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    all_recps catego = gson.fromJson(finalObject.toString(), all_recps.class);
                    milokilo.add(catego);
                }
                return milokilo;
            } catch (JSONException | IOException e) {
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
            return null;
        }
        @Override
        protected void onPostExecute(final List<all_recps> movieMode) {
            super.onPostExecute(movieMode);
            if (movieMode.size()>0) {
                MovieAdap adapter = new MovieAdap(getApplicationContext(), R.layout.second, movieMode);
                listv1.setAdapter(adapter);
                listv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        all_recps item = movieMode.get(position);
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        intent.putExtra("name", item.getTpcname());
                        intent.putExtra("id", item.getShtdesc());
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();
            } else {

                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
