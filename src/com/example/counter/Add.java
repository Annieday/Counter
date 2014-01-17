package com.example.counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Add extends Activity{
	EditText name=null;
	Button Add_Counter=null;
	String new_name=null;
	
	public static final String Each_Counts_label="COUNTER COUNTS";
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
			SharedPreferences Each_Counts=getSharedPreferences(Each_Counts_label,0);
			new_name=name.getText().toString().replaceAll("\\s+","-");
			if(Each_Counts.contains(new_name)){
				Toast.makeText(getApplicationContext(), "This counter name already appears.",Toast.LENGTH_SHORT).show();
			}
			else{
				Each_Counts.edit().putInt(new_name,0).commit();
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
