package com.example.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MealRaterDataSource {
    DBMealRaterHelper myhelper;
    public MealRaterDataSource(Context context)
    {
        myhelper = new DBMealRaterHelper(context);
    }

    public long insertData(String name, String dish, String rating)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBMealRaterHelper.RESTAURANT, name);
        contentValues.put(DBMealRaterHelper.DISH, dish);
        contentValues.put(DBMealRaterHelper.RATING,rating);
        long id = dbb.insert(DBMealRaterHelper.TABLE_NAME, null , contentValues);
        return id;
    }



    static class DBMealRaterHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myRestaurantDatabase";
        private static final String TABLE_NAME = "myRestaurantTable";
        private static final int DATABASE_VERSION = 1;
        private static final String DISH_ID="_dishID";
        private static final String RESTAURANT = "RestaurantName";
        private static final String DISH= "DishName";
        private static final String RATING ="Ratings";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+DISH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RESTAURANT+" VARCHAR(255) ,"+ DISH+" VARCHAR(225),"+RATING+"  VARCHAR(50));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DBMealRaterHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context,"Upgrade of Database",Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
            }
        }


    }

}