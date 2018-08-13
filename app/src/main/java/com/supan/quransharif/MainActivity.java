package com.supan.quransharif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] all_surah_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle.setText("কুরআন শরীফ");

        all_surah_list = getResources().getStringArray(R.array.all_surah_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview_row, all_surah_list);
        ListView mlistView = (ListView) findViewById(R.id.list);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                // Toast.makeText(getActivity(), ((TextView) view).getText() +" Pos: "+ position, Toast.LENGTH_SHORT).show();
                int surah_number = position + 1;
                String surah_name = ((TextView) view).getText().toString();

                Intent intent = new Intent(MainActivity.this, ShowSurahDetailsActivity.class);
                intent.putExtra("surah_number", ""+surah_number);
                intent.putExtra("surah_name", surah_name);
                startActivity(intent);
            }
        });

    }
}
