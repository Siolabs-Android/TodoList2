package com.siolabstodolist2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	EditText eText;
	ListView lv ;
	
	ArrayList<MyItem> data;
	ArrayAdapter<MyItem> aa;
	Spinner spinner;
	
	String[] cat =  {"ALL","Study","Shopping"};
	
	ItemsDAO itemsDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		itemsDAO = new ItemsDAO(this);
		
		eText = (EditText)findViewById(R.id.editText1);
		lv = (ListView)findViewById(R.id.listView1);
		spinner = (Spinner)findViewById(R.id.spinner1);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long id) {
				
				if(position == 0)
					data = itemsDAO.getAllItems();
				else
					data = itemsDAO.getAllItemsInCategory(cat[position]);
				aa.clear();
				aa.addAll(data);
				aa.notifyDataSetChanged();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				data = itemsDAO.getAllItems();
				aa.clear();
				aa.addAll(data);
				aa.notifyDataSetChanged();
			}
		});
		
		
		data = itemsDAO.getAllItems();
		if(data == null)
			data = new ArrayList<MyItem>();
		aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
		
		lv.setAdapter(aa);
		
		//function for click on list view item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				MyItem item = data.get(position);
				
				Intent i = new Intent(getApplicationContext(), Details.class);
				
				i.putExtra("viewKey", item);
				i.putExtra("pos",position);
				startActivityForResult(i, 10);
				
			}
		});		
		
	}
	
	

	
	
	
	public void addToList(View view){
		
		String s  = eText.getText().toString();
		MyItem i = new MyItem();
		i.setText(s);
		i.setCategory("ALL");
		
		itemsDAO.insertItem(i);
		
		data.add(0, i);
		aa.notifyDataSetChanged();
		eText.setText("");
		
		
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	
	
	aa.clear();
	this.data = itemsDAO.getAllItems();
	aa.addAll(this.data);
	aa.notifyDataSetChanged();
	
	super.onActivityResult(requestCode, resultCode, data);		
		
		
	}
}
