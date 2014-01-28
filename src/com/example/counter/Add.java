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

public class Add extends Activity{
	public static final String Label="COUNTERS";
	private EditText name=null;
	private Button Add_Counter=null;
	private String new_name=null;
	private SharedPreferences DataBase=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		name=(EditText)findViewById(R.id.name);
		Add_Counter=(Button)findViewById(R.id.Add_Counter);
		Add_Counter.setOnClickListener(new add_c_click());
	}

	class add_c_click implements OnClickListener{

		@Override
		public void onClick(View v){

			// TODO Auto-generated method stub
			DataBase=getSharedPreferences(Label,0);
			Gson gson=new Gson();
			Counter_list All_Counters=gson.fromJson(DataBase.getString("all_counters",gson.toJson(new Counter_list())),Counter_list.class);
			//new_name=name.getText().toString().replaceAll("\\s+","-");
			new_name=name.getText().toString();
			boolean res=All_Counters.Add(new_name);
			if(res==false){
				Toast.makeText(getApplicationContext(), "This counter name already appears.",Toast.LENGTH_SHORT).show();
			}
			else{
				DataBase.edit().putString("all_counters",gson.toJson(All_Counters)).commit();
				Intent intent=new Intent(Add.this,List_View.class);
				startActivity(intent);
				finish();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
