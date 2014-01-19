package com.example.counter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


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
	public static final String Each_Counts_label="COUNTER COUNTS";
	ListView counter_name=null;
	Button Back=null;
	@Override
	protected void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		counter_name=(ListView)findViewById(R.id.counters);
		Back=(Button)findViewById(R.id.Main_menu);
		SharedPreferences Each_Counts=getSharedPreferences(Each_Counts_label,0);
		List<String> name_list=new ArrayList<String>();
		Map<String,?> counter_map = Each_Counts.getAll();
		ValueComparator bvc =  new ValueComparator(counter_map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
        for (Map.Entry<String, ?> pair: counter_map.entrySet()) {
			sorted_map.put(pair.getKey(),(Integer) pair.getValue());
	    }
        for(Map.Entry<String,?> pair: sorted_map.entrySet()){
        	name_list.add(pair.getKey());
        }
		
		String[] name_array = name_list.toArray(new String[0]);
		ArrayAdapter<String> name_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name_array);
		counter_name.setAdapter(name_adapter);
		counter_name.setOnItemClickListener(new click_counter());
		Back.setOnClickListener(new back_click());
	}
	// this part is the idea from http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
	class ValueComparator implements Comparator<String> {

	    Map<String, ?> base;
	    public ValueComparator(Map<String,?> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if ((Integer)base.get(a) >= (Integer)base.get(b)) {
	            return 1;
	        } else {
	            return -1;
	        } // returning 0 would merge keys
	    }
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
			finish();
		}
		
	}
	
	class back_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(List_View.this,MainActivity.class);
			startActivity(push_intent);
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
