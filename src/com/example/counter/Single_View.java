package com.example.counter;

//import java.util.LinkedHashSet;
//import java.util.Set;

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
	//public static final String Counter="COUNTER NAME";
	public static final String Each_Counts_label="COUNTER COUNTS";
	//SharedPreferences Counters=null;
	SharedPreferences Each_Counts=null;
	String counter_name=null;
	int counts;
	TextView Counter_name=null;
	TextView Counts=null;
	Button increment=null;
	Button reset=null;
	Button rename=null;
	Button remove=null;
	Button back_to_list=null;
	//List_name
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single__view);
		//Counters=getSharedPreferences(Counter,0);
		Each_Counts=getSharedPreferences(Each_Counts_label,0);
		Intent intent=getIntent();
		Counter_name=(TextView)findViewById(R.id.counter_name);
		Counts=(TextView)findViewById(R.id.counts);
		counter_name=intent.getStringExtra("counter_name");
		Counter_name.setText("Counter:   "+counter_name);
		counts=Each_Counts.getInt(counter_name,0);
		Counts.setText(counts+"");
		increment=(Button)findViewById(R.id.increment);
		increment.setOnClickListener(new increment_click());
		reset=(Button)findViewById(R.id.reset);
		reset.setOnClickListener(new reset_click());
		rename=(Button)findViewById(R.id.rename);
		rename.setOnClickListener(new rename_click());
		remove=(Button)findViewById(R.id.remove);
		remove.setOnClickListener(new remove_click());
		back_to_list=(Button)findViewById(R.id.back_to_list);
		back_to_list.setOnClickListener(new back_c());
	}
	
	class back_c implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(Single_View.this,List_View.class);
			startActivity(push_intent);
			finish();
		}
		
	}
	
	class increment_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			counts++;
			Each_Counts.edit().putInt(counter_name,counts).commit();
			Counts.setText(counts+"");
		}
		
	}
	
	class reset_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			counts=0;
			Each_Counts.edit().putInt(counter_name,counts).commit();
			Counts.setText(counts+"");
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

			// TODO Auto-generated method stub
			//Set<String> name_list=Counters.getStringSet("name list", new LinkedHashSet<String>());
			//name_list.remove(counter_name);
			//Counters.edit().putStringSet("name list",name_list).commit();
			Each_Counts.edit().remove(counter_name).commit();
			Intent push_intent=new Intent(Single_View.this,List_View.class);
			startActivity(push_intent);
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
