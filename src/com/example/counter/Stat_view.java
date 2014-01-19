package com.example.counter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class Stat_view extends Activity
{
	public static final String Date_rec="DATE REC";
	TextView stat_title=null;
	SharedPreferences Dates=null;
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
		stat_title.setText("View counts per "+Tag+" : "+counter_name);
		Dates=getSharedPreferences(Date_rec,0);
		Set<String> date_list=Dates.getStringSet(counter_name, new HashSet<String>());
		Iterator<String> date_str_iterator = date_list.iterator();
		TreeSet<Date> sorted_date=new TreeSet<Date>();
		while (date_str_iterator.hasNext()){
			
		}
	}
	
	public static Date str_to_date(String str) throws ParseException{
		DateFormat format= new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		Date date=format.parse(str);
		return date;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat_view, menu);
		return true;
	}

}
