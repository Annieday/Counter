package com.example.counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity
{
	//set variable
	//LinearLayout Linear=null;
	private Button View=null;
	private Button Add=null;
	private Button Exit=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Linear=(LinearLayout)findViewById(R.id.Linear);
		//find all button by id
		View=(Button)findViewById(R.id.View);
		Add=(Button)findViewById(R.id.Add);
		Exit=(Button)findViewById(R.id.Exit);
		//Button setup
		Exit.setOnClickListener(new exit_click());
		View.setOnClickListener(new view_click());
		Add.setOnClickListener(new add_click());
	}
	//exit listener
	class exit_click implements OnClickListener{

		@Override
		public void onClick(android.view.View v)
		{

			// TODO Auto-generated method stub
			finish();
			//System.exit(0);
			//android.os.Process.killProcess(android.os.Process.myPid());
		    Intent push_intent = new Intent(Intent.ACTION_MAIN); 
		    push_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		    push_intent.addCategory(Intent.CATEGORY_HOME);
		    startActivity(push_intent);
		}
		
	}
	//view listener
	class view_click implements OnClickListener{

		@Override
		public void onClick(android.view.View v)
		{

			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,List_View.class);
			startActivity(intent);
		}
		
	}
	//add listener
	class add_click implements OnClickListener{

		@Override
		public void onClick(android.view.View v)
		{

			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity.this,Add.class);
			startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
