package com.example.counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;


public class Counter_list{
	Map<String,Counter> Counters= new HashMap<String,Counter>();
	ValueComparator bvc =  new ValueComparator(Counters);
	
	public Counter_list(){
		
	}
	
	public boolean Add(String New_name){
		if(Counters.containsKey(New_name)){
			return false;
		}
		else{
			Counters.put(New_name,new Counter(New_name));
			return true;
		}
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
		}
	}
	
	public boolean Rename(String counter_name, String new_name){
		if(Counters.containsKey(new_name)){
			return false;
		}
		else{
			Counter c= Counters.get(counter_name);
			c.rename(new_name);
			return true;
		}
	}
	
	public String[] getListViewAdapter(){
		TreeMap<String,Counter> SortedCounters=new TreeMap<String,Counter>(bvc);
	    for(Map.Entry<String,Counter> pair: Counters.entrySet()){
	    	SortedCounters.put(pair.getKey(),pair.getValue());
		}
	    List<String> name_list=new ArrayList<String>();
	    for(Map.Entry<String,Counter> pair: SortedCounters.entrySet()){
	    	name_list.add(pair.getKey()+"   "+pair.getValue().get_count());
	    }
	    return name_list.toArray(new String[0]);
	}
	
	class ValueComparator implements Comparator<String>{
		Map<String,Counter> base;
	    public ValueComparator(Map<String,Counter> base) {
	        this.base = base;
	    }
		@Override
		public int compare(String a, String b){
			
			if(base.get(a).get_count()>=base.get(b).get_count()){
				return 1;
			}
			else{
				return -1;
			}
		}
		
	}
}
