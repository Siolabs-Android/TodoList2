package com.siolabstodolist2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Details extends Activity {

	EditText eText1;
	Spinner spinner;
	String data;
	int pos;
	
	ItemsDAO itemsDAO;
	
	String[] category = {"ALL","Study","Shopping"};
			
	MyItem item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		itemsDAO = new ItemsDAO(this);
		
		Intent i = getIntent();
		
		item   = (MyItem) i.getSerializableExtra("viewKey");
		pos = i.getExtras().getInt("pos");
		
		eText1 = (EditText)findViewById(R.id.editText1);
		eText1.setText(item.getText());
		
	
		
		spinner = (Spinner)findViewById(R.id.spinner_details);
		if(item.getCategory().equalsIgnoreCase("ALL"))
			spinner.setSelection(0);
		else if(item.getCategory().equalsIgnoreCase("Study"))
			spinner.setSelection(1);
		else
			spinner.setSelection(2);
		
		
	}
	
	
	public void update(View view){
		
		this.finish();
	}


	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		
		item.setText(eText1.getText().toString());
		item.setCategory(category[spinner.getSelectedItemPosition()]);
		itemsDAO.updateItem(item);
		
		Intent intent = new Intent();
		intent.putExtra("updatedValue",item);
		intent.putExtra("pos",pos);
		setResult(10, intent);		
		super.finish();
	}


	
	
	

}
