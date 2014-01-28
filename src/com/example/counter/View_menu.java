package com.example.counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class View_menu extends Activity
{
	private String counter_name=null;
	private Button view_per_hour=null;
	private Button view_per_day=null;
	private Button view_per_week=null;
	private Button view_per_month=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_menu);
		Intent intent=getIntent();
		counter_name=intent.getStringExtra("counter_name");
		view_per_hour=(Button)findViewById(R.id.view_per_hour);
		view_per_hour.setOnClickListener(new vph_click());
		view_per_day=(Button)findViewById(R.id.view_per_day);
		view_per_day.setOnClickListener(new vpd_click());
		view_per_week=(Button)findViewById(R.id.view_per_week);
		view_per_week.setOnClickListener(new vpw_click());
		view_per_month=(Button)findViewById(R.id.view_per_month);
		view_per_month.setOnClickListener(new vpm_click());
	}
	
	class vph_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(View_menu.this,Stat_view.class);
			push_intent.putExtra("counter_name",counter_name);
			push_intent.putExtra("TAG","HOUR");
			startActivity(push_intent);
			finish();
		}
		
	}
	
	class vpd_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(View_menu.this,Stat_view.class);
			push_intent.putExtra("counter_name",counter_name);
			push_intent.putExtra("TAG","DAY");
			startActivity(push_intent);
			finish();
		}
		
	}
	
	class vpw_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(View_menu.this,Stat_view.class);
			push_intent.putExtra("counter_name",counter_name);
			push_intent.putExtra("TAG","WEEK");
			startActivity(push_intent);
			finish();
		}
		
	}
	
	class vpm_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(View_menu.this,Stat_view.class);
			push_intent.putExtra("counter_name",counter_name);
			push_intent.putExtra("TAG","MONTH");
			startActivity(push_intent);
			finish();
		}
		
	}
	
	class gb_click implements OnClickListener{

		@Override
		public void onClick(View v)
		{

			// TODO Auto-generated method stub
			Intent push_intent=new Intent(View_menu.this,Single_View.class);
			push_intent.putExtra("counter_name",counter_name);
			startActivity(push_intent);
			finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_menu, menu);
		return true;
	}

}
