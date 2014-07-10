package com.siolabstodolist2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Details extends Activity {

	EditText eText1;
	String data;
	int pos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent i = getIntent();
		
		 data  = i.getExtras().getString("viewKey");
		 pos = i.getExtras().getInt("for");
		
		eText1 = (EditText)findViewById(R.id.editText1);
		eText1.setText(data);
		
	}
	
	
	public void update(View view){
		
		this.finish();
	}


	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent();
		intent.putExtra("view",eText1.getText().toString());
		intent.putExtra("pos",pos);
		setResult(10, intent);		
		super.finish();
	}


	
	
	

}
