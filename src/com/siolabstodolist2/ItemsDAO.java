package com.siolabstodolist2;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemsDAO {
	
   private MySQLiteHelper helper;
   private SQLiteDatabase db;
   private String[] allColumns = {"_id", "desc", "category"};
   
   
   public ItemsDAO(Context context){
	   helper = new MySQLiteHelper(context);
	   db = helper.getWritableDatabase();
   }
   
   public void close(){
	   helper.close();
   }
   
   public void insertItem(MyItem i){
	   
	   ContentValues values = new ContentValues();
	   values.put("desc", i.getText());
	   values.put("category", i.getCategory());
	   
	   db.insert("items", null, values);
	   
	   
   }
   
   public void deleteItem(MyItem i){
	   
	   db.delete("items", "_id = "+i.getId(), null);
	   
   }
   
   public void updateItem(MyItem i){
	   
	   ContentValues values = new ContentValues();
	   values.put("desc", i.getText());
	   values.put("category", i.getCategory());
	   db.update("items", values, "_id = "+ i.getId(), null);
	   
   }
   
   public MyItem getItem(int id){
	   MyItem resultItem = null;
	   
	   Cursor c = db.query("items",allColumns,"_id = "+id, null,null,null,null);
	   if(c.getCount() == 1)
		   resultItem = cursorToItem(c);
	   
	   
	   return resultItem;
   }
   
   private MyItem cursorToItem(Cursor c) {
	// TODO Auto-generated method stub
	   
	   MyItem item = new MyItem();
	   item.setId(c.getInt(0));
	   item.setText(c.getString(1));
	   item.setCategory(c.getString(2));
	   
	   
	   
	return item;
}

   public ArrayList<MyItem> getAllItems(){
	   ArrayList<MyItem> allItems  = new ArrayList<MyItem>();
	   
	   Cursor c = db.query("items",allColumns,null,null,null,null,null);
	   c.moveToFirst();
	   
	   
	   while(!c.isAfterLast()){
		   allItems.add(cursorToItem(c));
		   c.moveToNext();
	   }
	   
	   
	   
	   
	   return allItems;
   }
   
   public ArrayList<MyItem> getAllItemsInCategory(String category){
	   
ArrayList<MyItem> allItems  = new ArrayList<MyItem>();
	   
	   Cursor c = db.query("items",allColumns,"category='"+category+"'",null,null,null,null);
	   c.moveToFirst();
	   
	   
	   while(!c.isAfterLast()){
		   allItems.add(cursorToItem(c));
		   c.moveToNext();
	   }
	   
	   
	   
	   
	   return allItems;
   }

}
