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

public class Rename extends Activity
{
	EditText new_name_=null;
	Button ok=null;
	public static final String Label="COUNTERS";
	public static final String RENAME_HELPER="RENAME_HELPER";
	SharedPreferences DataBase=null;
	SharedPreferences Rename_helper=null;
	String counter_name=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rename);
		new_name_=(EditText)findViewById(R.id.new_name);
		ok=(Button)findViewById(R.id.ok);
		ok.setOnClickListener(new ok_click());
	}
	
	



	@Override
	protected void onStart()
	{

		// TODO Auto-generated method stub
		super.onStart();
		DataBase=getSharedPreferences(Label,0);
		Intent intent=getIntent();
		counter_name=intent.getStringExtra("counter_name");
	}





	class ok_click implements OnClickListener{

		@Override
		public void onClick(View v){

			// TODO Auto-generated method stub
			Rename_helper=getSharedPreferences(RENAME_HELPER,0);
			String New_name=new_name_.getText().toString().replaceAll("\\s+","-");
			Gson gson=new Gson();
			Counter_list All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
			boolean res=All_Counters.Rename(counter_name,New_name);
			if(res==false){
				Toast.makeText(getApplicationContext(),"This counter name already appears or it is the same as the old name of this counter.",Toast.LENGTH_SHORT).show();
			}
			else{
				DataBase.edit().putString("all_counters",gson.toJson(All_Counters)).commit();
				Rename_helper.edit().putString("rename",New_name).commit();
				finish();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rename, menu);
		return true;
	}

}
