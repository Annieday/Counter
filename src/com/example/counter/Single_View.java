package com.example.counter;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Single_View extends Activity
{
	public static final String Label="COUNTERS";
	public static final String RENAME_HELPER="RENAME_HELPER";
	private SharedPreferences DataBase=null;
	private SharedPreferences Rename_helper=null;
	private String counter_name=null;
	private TextView Counter_name=null;
	private TextView Counts=null;
	private Button increment=null;
	private Button reset=null;
	private Button rename=null;
	private Button remove=null;
	private Button Stat=null;
	private Counter_list All_Counters=null;
	//List_name
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single__view);
		Counter_name=(TextView)findViewById(R.id.counter_name);
		Counts=(TextView)findViewById(R.id.counts);
		increment=(Button)findViewById(R.id.increment);
		increment.setOnClickListener(new increment_click());
		reset=(Button)findViewById(R.id.reset);
		reset.setOnClickListener(new reset_click());
		rename=(Button)findViewById(R.id.rename);
		rename.setOnClickListener(new rename_click());
		remove=(Button)findViewById(R.id.remove);
		remove.setOnClickListener(new remove_click());
		Stat=(Button)findViewById(R.id.Stat);
		Stat.setOnClickListener(new stat_click());
		Intent intent=getIntent();
		counter_name=intent.getStringExtra("counter_name");
		Counter_name.setText("Counter:   "+counter_name);
	}
	
	
	
	@Override
	protected void onStart()
	{

		// TODO Auto-generated method stub
		super.onStart();
		DataBase=getSharedPreferences(Label,0);
		Gson gson=new Gson();
		All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
		Rename_helper=getSharedPreferences(RENAME_HELPER,0);
		if(Rename_helper.contains("rename")){
			Counter_name.setText(Rename_helper.getString("rename", ""));
			counter_name=Rename_helper.getString("rename", "");
			Rename_helper.edit().remove("rename").commit();
		}
		Counts.setText(All_Counters.getCounts(counter_name)+"");
	}
	
	class increment_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Gson gson=new Gson();
			All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
			All_Counters.increment_counter(counter_name);
			DataBase.edit().putString("all_counters",gson.toJson(All_Counters)).commit();
			Counts.setText(All_Counters.getCounts(counter_name)+"");
		}
		
	}
	
	class reset_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			Gson gson=new Gson();
			All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
			All_Counters.reset_counter(counter_name);
			DataBase.edit().putString("all_counters",gson.toJson(All_Counters)).commit();
			Counts.setText(All_Counters.getCounts(counter_name)+"");
		}
		
	}
	
	class stat_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(Single_View.this,View_menu.class);
			push_intent.putExtra("counter_name", counter_name);
			startActivity(push_intent);
		}
		
	}
	
	class rename_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(Single_View.this,Rename.class);
			push_intent.putExtra("counter_name",counter_name);
			startActivity(push_intent);
		}
		
	}
	
	class remove_click implements OnClickListener{

		@Override
		public void onClick(View arg0)
		{
			Gson gson=new Gson();
			All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
			All_Counters.Remove(counter_name);
			DataBase.edit().putString("all_counters",gson.toJson(All_Counters)).commit();
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single__view, menu);
		return true;
	}

}
