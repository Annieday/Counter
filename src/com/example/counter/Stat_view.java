package com.example.counter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

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
		stat_title.setText("View counts per "+Tag.toLowerCase(Locale.getDefault())+" : "+counter_name);
		stat_list=(ListView)findViewById(R.id.stat_list);
		Dates=getSharedPreferences(Date_rec,0);
		Set<String> date_list=Dates.getStringSet(counter_name, new HashSet<String>());
		//Iterator<String> date_str_iterator = date_list.iterator();
		
		if(date_list.size()!=0){
			TreeSet<Date> sorted_date=new TreeSet<Date>();
			//while (date_str_iterator.hasNext()){
				//sorted_date.add(new Date(Long.valueOf(date_str_iterator.next())));
			//}
			for (String date_str : date_list) {
				sorted_date.add(new Date(Long.valueOf(date_str)));
			}
			//debug:
			System.out.println(sorted_date);
			String[] date_array=create_adapter(sorted_date,Tag);
			ArrayAdapter<String> date_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,date_array);
			stat_list.setAdapter(date_adapter);
		}
		
	}
	
	public static Date str_to_date(String str) throws ParseException{
		DateFormat format= new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		Date date=format.parse(str);
		return date;
	}
	
	public static String[] create_adapter(TreeSet<Date> sorted_date,String tag){
		List<String> view_list=new ArrayList<String>();
		if(tag.equals("WEEK")==false){
			int count=0;
			int block_amount=0;
			Date prev=null;
			Date current=null;
			Calendar prev_c = Calendar.getInstance();
			Calendar current_c = Calendar.getInstance();
			for(Date date : sorted_date){
				if(count==0){
					current=date;
					block_amount++;
				}
				else{
					prev=(Date)current.clone();
					prev_c.setTime(prev);
					current=date;
					current_c.setTime(current);
					if(tag.equals("MONTH")){
						if((prev_c.get(Calendar.YEAR)==current_c.get(Calendar.YEAR))&&(prev_c.get(Calendar.MONTH)==current_c.get(Calendar.MONTH))){
							block_amount++;
						}
						else{
							String[] slices=prev.toString().split(" ");
							view_list.add(slices[5]+"-"+slices[1]+"-----"+block_amount);
							block_amount=1;
						}
					}
					
					else if(tag.equals("DAY")){
						if( (prev_c.get(Calendar.YEAR)==current_c.get(Calendar.YEAR)) && (prev_c.get(Calendar.MONTH)==current_c.get(Calendar.MONTH)) && (prev_c.get(Calendar.DAY_OF_MONTH)==current_c.get(Calendar.DAY_OF_MONTH)) ){
							block_amount++;
						}
						else{
							String[] slices=prev.toString().split(" ");
							view_list.add(slices[5]+"-"+slices[1]+"-"+slices[2]+"-----"+block_amount);
							block_amount=1;
						}
					}
					
					else if(tag.equals("HOUR")){
						if( (prev_c.get(Calendar.YEAR)==current_c.get(Calendar.YEAR)) && (prev_c.get(Calendar.MONTH)==current_c.get(Calendar.MONTH)) && (prev_c.get(Calendar.DAY_OF_MONTH)==current_c.get(Calendar.DAY_OF_MONTH)) && (prev_c.get(Calendar.HOUR_OF_DAY)==current_c.get(Calendar.HOUR_OF_DAY)) ){
							block_amount++;
						}
						else{
							String[] slices=prev.toString().split(" ");
							String hour_present=slices[3].split(":")[0]+":00";
							view_list.add(slices[5]+"-"+slices[1]+"-"+slices[2]+"-"+hour_present+"-----"+block_amount);
							block_amount=1;
						}
					}
				}
				count++;
			}
			String[] finial_slices=current.toString().split(" ");
			if(tag.equals("MONTH")){
				view_list.add(finial_slices[5]+"-"+finial_slices[1]+"-----"+block_amount);
			}
			else if(tag.equals("DAY")){
				view_list.add(finial_slices[5]+"-"+finial_slices[1]+"-"+finial_slices[2]+"-----"+block_amount);
			}
			else if(tag.equals("HOUR")){
				String hour_present=finial_slices[3].split(":")[0]+":00";
				view_list.add(finial_slices[5]+"-"+finial_slices[1]+"-"+finial_slices[2]+"-"+hour_present+"-----"+block_amount);
			}
		}
		else{
			
		}
		return view_list.toArray(new String[0]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat_view, menu);
		return true;
	}

}
