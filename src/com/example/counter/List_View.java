package com.example.counter;

import com.google.gson.Gson;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.content.SharedPreferences;

public class List_View extends Activity
{
	public static final String Label="COUNTERS";
	private ListView counter_name=null;
	private Button Back=null;
	private SharedPreferences DataBase=null;
	@Override
	protected void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		counter_name=(ListView)findViewById(R.id.counters);
		Back=(Button)findViewById(R.id.Main_menu);
	}
	
	
	
	@Override
	protected void onStart()
	{

		// TODO Auto-generated method stub
		super.onStart();
		DataBase=getSharedPreferences(Label,0);
		Gson gson=new Gson();
		Counter_list All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
		ArrayAdapter<String> name_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,All_Counters.getListViewAdapter());
		counter_name.setAdapter(name_adapter);
		counter_name.setOnItemClickListener(new click_counter());
		Back.setOnClickListener(new back_click());
	}



	class click_counter implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3)
		{

			// TODO Auto-generated method stub
			String counter_name =(arg0.getItemAtPosition(arg2).toString());
			Intent intent=new Intent(List_View.this,Single_View.class);
			intent.putExtra("counter_name",counter_name);
			startActivity(intent);
		}
		
	}
	
	class back_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}

}
