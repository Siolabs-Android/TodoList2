package com.siolabstodolist2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	EditText eText;
	ListView lv ;
	
	ArrayList<String> data;
	ArrayAdapter aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eText = (EditText)findViewById(R.id.editText1);
		lv = (ListView)findViewById(R.id.listView1);
		
		data = new ArrayList<String>();
		aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		
		lv.setAdapter(aa);
		
		//function for click on list view item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				String s = data.get(position);
				
				Intent i = new Intent(getApplicationContext(), Details.class);
				
				i.putExtra("viewKey", s);
				i.putExtra("for",position);
				startActivityForResult(i, RESULT_OK);
				
			}
		});		
		
	}
	
	

	
	
	
	public void addToList(View view){
		
		String s  = eText.getText().toString();
		data.add(0, s);
		aa.notifyDataSetChanged();
		eText.setText("");
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	String s=data.getExtras().getString("view");
	int i=data.getExtras().getInt("pos");
	this.data.add(i,s);
	aa.notifyDataSetChanged();
	super.onActivityResult(requestCode, resultCode, data);		
		
		
	}
}
