package com.example.counter;

import java.util.Locale;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Stat_view extends Activity
{
	public static final String Label="COUNTERS";
	TextView stat_title=null;
	SharedPreferences DataBase=null;
	ListView stat_list=null;
	String counter_name=null;
    String Tag=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat_view);
		Intent intent=getIntent();
		counter_name=intent.getStringExtra("counter_name");
		Tag=intent.getStringExtra("TAG");
		stat_title=(TextView)findViewById(R.id.stat_title);
		stat_title.setText("View counts per "+Tag.toLowerCase(Locale.getDefault())+" : "+counter_name);
		stat_list=(ListView)findViewById(R.id.stat_list);
		DataBase=getSharedPreferences(Label,0);
		Gson gson=new Gson();
		Counter_list All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
		String[] array=All_Counters.getDateViewAdapter(counter_name, Tag);
		ArrayAdapter<String> date_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
		stat_list.setAdapter(date_adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat_view, menu);
		return true;
	}

}
