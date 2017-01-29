package com.thieumao.youtubeapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.thieumao.youtubeapp.R;
import com.thieumao.youtubeapp.adapter.HistoryAdapter;
import com.thieumao.youtubeapp.adapter.PlaylistAdapter;
import com.thieumao.youtubeapp.utils.Database;
import com.thieumao.youtubeapp.utils.JSONParser;
import com.thieumao.youtubeapp.utils.Vari;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> arrList;
    HistoryAdapter adapter;
    ListView lvHistory;
    int idUser = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");
        Bundle bundle = getIntent().getExtras();
        idUser = bundle.getInt("id");
        arrList = new ArrayList<HashMap<String, String>>();
        adapter = new HistoryAdapter(HistoryActivity.this, arrList);
        lvHistory = (ListView) findViewById(R.id.lvHistory);
        lvHistory.setAdapter(adapter);
        new HistoryActivity.AsynLoad().execute();
    }

    private class AsynLoad extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Cursor kq = Database.getInstance(HistoryActivity.this).getData("SELECT * FROM history where idUser = '" + idUser + "';");
            while (kq.moveToNext() == true) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(PlaylistActivity.title, kq.getString(1));
                map.put(PlaylistActivity.thumbnails, kq.getString(2));
                map.put(PlaylistActivity.videoId, kq.getString(3));
                arrList.add(map);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            adapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }

}
