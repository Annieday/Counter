package com.example.counter;

import java.util.HashSet;
import java.util.Set;

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
	//public static final String Counter="COUNTER NAME";
	public static final String Each_Counts_label="COUNTER COUNTS";
	public static final String Date_rec="DATE REC";
	//SharedPreferences Counters=null;
	SharedPreferences Dates=null;
	SharedPreferences Each_Counts=null;
	String counter_name=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rename);
		//Counters=getSharedPreferences(Counter,0);
		Each_Counts=getSharedPreferences(Each_Counts_label,0);
		
		Dates=getSharedPreferences(Date_rec,0);
		
		Intent intent=getIntent();
		counter_name=intent.getStringExtra("counter_name");
		new_name_=(EditText)findViewById(R.id.new_name);
		ok=(Button)findViewById(R.id.ok);
		ok.setOnClickListener(new ok_click());
	}



	class ok_click implements OnClickListener{

		@Override
		public void onClick(View v){

			// TODO Auto-generated method stub
			String New_name=new_name_.getText().toString().replaceAll("\\s+","-");
			int counts=Each_Counts.getInt(counter_name,0);
			if(Each_Counts.contains(New_name)){
				Toast.makeText(getApplicationContext(), "This counter name already appears or it is the same as the old name of this counter.",Toast.LENGTH_SHORT).show();
			}
			else{
				Each_Counts.edit().putInt(New_name,counts).commit();
				Each_Counts.edit().remove(counter_name).commit();
				Set<String> date_list=Dates.getStringSet(counter_name, new HashSet<String>());
				Dates.edit().putStringSet(New_name,date_list).commit();
				Dates.edit().remove(counter_name).commit();
				Intent push_intent=new Intent(Rename.this,Single_View.class);
				push_intent.putExtra("counter_name",New_name);
				startActivity(push_intent);
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
