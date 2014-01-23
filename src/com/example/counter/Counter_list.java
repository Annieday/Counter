package com.example.counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.TreeSet;


public class Counter_list{
	Map<String,Counter> Counters= new HashMap<String,Counter>();
	CountComparator cmp =  new CountComparator();
	int size=0;
	
	public Counter_list(){
		
	}
	
	public boolean Add(String New_name){
		if(Counters.containsKey(New_name)){
			return false;
		}
		else{
			Counters.put(New_name,new Counter(New_name));
			size++;
			return true;
		}
	}
	public int getSize(){
		return size;
	}
	
	public void increment_counter(String counter_name){
		Counter c = Counters.get(counter_name);
		c.increment();
		Counters.put(counter_name,c);
	}
	
	public void reset_counter(String counter_name){
		Counter c = Counters.get(counter_name);
		c.reset();
		Counters.put(counter_name,c);
	}
	
	public void Remove(String counter_name){
		if(Counters.containsKey(counter_name)){
			Counters.remove(counter_name);
			size--;
		}
	}
	
	public int getCounts(String counter_name){
		Counter c = Counters.get(counter_name);
		return c.get_count();
	}
	
	public boolean Rename(String counter_name, String new_name){
		if(Counters.containsKey(new_name)){
			return false;
		}
		else{
			Counter c= Counters.get(counter_name);
			c.rename(new_name);
			Counters.put(new_name, c);
			Counters.remove(counter_name);
			return true;
		}
	}
	
	public String[] getDateViewAdapter(String counter_name,String tag){
		Counter c= Counters.get(counter_name);
		return c.getArrayAdapterForDate(tag);
	}
	
	public String[] getListViewAdapter(){
		TreeSet<Counter> SortedCounters=new TreeSet<Counter>(cmp);
	    for(Map.Entry<String,Counter> pair: Counters.entrySet()){
	    	SortedCounters.add(pair.getValue());
		}
	    List<String> name_list=new ArrayList<String>();
	    for(Counter c : SortedCounters){
	    	name_list.add(c.get_counter_name());
	    }
	    return name_list.toArray(new String[0]);
	}

	class CountComparator implements Comparator<Counter>{
		@Override
		public int compare(Counter a,Counter b){
			if(a.get_count()>=b.get_count()){
				return 1;
			}
			else{
				return -1;
			}
		}
		
	}
}
