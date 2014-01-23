package com.example.counter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Counter{
	
	private int count=0;
	private String counter_name=null;
	private Set<Long> Time= new TreeSet<Long>();
	
	public Counter(String name){
		counter_name=name;
	}
	
	public void increment(){
		count++;
		Time.add((new Date()).getTime());
	}
	
	public void reset(){
		count=0;
		Time.clear();
	}
	
	public void rename(String New_name){
		counter_name=New_name;
	}
	
	public String get_counter_name(){
		return this.counter_name;
	}
	
	public int get_count(){
		return this.count;
	}
	
	public String[] getArrayAdapterForDate(String tag){
		Set<Date> Dates=new TreeSet<Date>();
		for(Long time : Time){
			Dates.add(new Date(time));
		}
		List<String> view_list=new ArrayList<String>();
		if(tag.equals("WEEK")==false){
			int count=0;
			int block_amount=0;
			Date prev=null;
			Date current=null;
			Calendar prev_c = Calendar.getInstance();
			Calendar current_c = Calendar.getInstance();
			for(Date date : Dates){
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
			int prev_week_day;
			int current_week_day;
			int count=0;
			int block_amount=0;
			Date prev=null;
			Date current=null;
			Calendar prev_c = Calendar.getInstance();
			Calendar current_c = Calendar.getInstance();
			for(Date date : Dates){
				if(count==0){
					current=date;
					block_amount++;
				}
				else{
					prev=(Date)current.clone();
					current=date;
					prev_c.setTime(prev);
					current_c.setTime(current);
					prev_week_day=prev_c.get(Calendar.DAY_OF_WEEK);
					current_week_day=current_c.get(Calendar.DAY_OF_WEEK);
					if(((current.getTime()-prev.getTime())<604800000)&&((current_week_day-prev_week_day)>=0)){
						block_amount++;
					}
					else{
						String[] slices=prev.toString().split(" ");
						view_list.add("Week of "+slices[5]+"-"+slices[1]+"-"+slices[2]+"-----"+block_amount);
						block_amount=1;
					}
				}
				count++;
			}
			String[] finial_slices=current.toString().split(" ");
			view_list.add("Week of "+finial_slices[5]+"-"+finial_slices[1]+"-"+finial_slices[2]+"-----"+block_amount);
		}
		return view_list.toArray(new String[0]);
	}
}
