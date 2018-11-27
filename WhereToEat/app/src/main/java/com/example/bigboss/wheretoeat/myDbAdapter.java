package com.example.bigboss.wheretoeat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.common.util.ArrayUtils;


public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long register(String name, String pass) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();


        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME, myDbHelper.MyPASSWORD};

        Cursor cursor = dbb.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            if (username.equals(name)) {
                return 0;
            }
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME, myDbHelper.MyPASSWORD};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(cid + "   " + name + "   " + password + " \n");
        }
        return buffer.toString();
    }

    public String login(String username, String password) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.USERNAME, myDbHelper.MyPASSWORD};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String pass = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            if (username.equals(name) && password.equals(pass)) {
                return "0"; //Login Success
            } else if (username.equals(name) && (password.equals(pass)) == false) {
                return "1"; // Login Fail, Password Incorrect
            }
        }
        return "2"; //Maybe No Username
    }

    public long addRestaurant(String name, String type, String time, String recommend, String price, String lat, String longt) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();


        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};

        Cursor cursor = dbb.query(myDbHelper.TABLE_NAME2, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
            String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
            if (nameindb.equals(name)) {
                return 0;
            }
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RESTNAME, name);
        contentValues.put(myDbHelper.TYPE, type);
        contentValues.put(myDbHelper.TIME, time);
        contentValues.put(myDbHelper.RECOMMEND, recommend);
        contentValues.put(myDbHelper.PRICE, price);
        contentValues.put(myDbHelper.LAT, lat);
        contentValues.put(myDbHelper.LONG, longt);
        long id = dbb.insert(myDbHelper.TABLE_NAME2, null, contentValues);
        return id;
    }


    public String getDataRest() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
            String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
            String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
            String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
            String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
            String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
            String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));
            buffer.append(nameindb);
        }
        return buffer.toString();
    }

    public String showRestaurant() {
        SQLiteDatabase db2 = myhelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db2, myDbHelper.TABLE_NAME2);
        db2.close();

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
            String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
            String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
            String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
            String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
            String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
            String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

            buffer.append(nameindb + "__");
        }
        return buffer.toString();
    }

    public String showEdit(String nameEdit) {
        SQLiteDatabase db2 = myhelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db2, myDbHelper.TABLE_NAME2);
        db2.close();

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "name = '" + nameEdit + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        cursor.moveToNext();
        int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
        String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
        String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
        String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
        String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
        String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
        String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
        String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

        buffer.append(cid + "__" + type + "__" + nameindb + "__" + time + "__" + recommend + "__" + price + "__" + lat + "__" + longt);

        return buffer.toString();
    }


    public void editRestaurant(String idM, String name, String type, String time, String recommend, String price, String lat, String longt) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RESTNAME, name);
        contentValues.put(myDbHelper.TYPE, type);
        contentValues.put(myDbHelper.TIME, time);
        contentValues.put(myDbHelper.RECOMMEND, recommend);
        contentValues.put(myDbHelper.PRICE, price);
        contentValues.put(myDbHelper.LAT, lat);
        contentValues.put(myDbHelper.LONG, longt);
        long id = dbb.update(myDbHelper.TABLE_NAME2, contentValues, "id = " + idM, null);
    }

    public void deleteRestaurant(String uid) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {uid};

        db.delete(myDbHelper.TABLE_NAME2, myDbHelper.UID2 + " = ?", whereArgs);
    }

    public String showRestauranttoChoose(String[] chs) {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        StringBuffer buffer = new StringBuffer();

        if (ArrayUtils.contains(chs, "ชาบู")) {
            Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "type= 'ชาบู'", null, null, null, null);

            while (cursor.moveToNext()) {
                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
                String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
                String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
                String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
                String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
                String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
                String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
                String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

                buffer.append(nameindb + "__");
            }
        }
        if (ArrayUtils.contains(chs, "หมูกระทะ")) {
            Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "type= 'หมูกระทะ'", null, null, null, null);

            while (cursor.moveToNext()) {
                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
                String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
                String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
                String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
                String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
                String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
                String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
                String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

                buffer.append(nameindb + "__");
            }
        }
        if (ArrayUtils.contains(chs, "อาหารตามสั่ง")) {
            Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "type= 'อาหารตามสั่ง'", null, null, null, null);

            while (cursor.moveToNext()) {
                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
                String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
                String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
                String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
                String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
                String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
                String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
                String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

                buffer.append(nameindb + "__");
            }
        }
        if (ArrayUtils.contains(chs, "ก๋วยเตี๋ยว")) {
            Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "type= 'ก๋วยเตี๋ยว'", null, null, null, null);

            while (cursor.moveToNext()) {
                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
                String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
                String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
                String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
                String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
                String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
                String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
                String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

                buffer.append(nameindb + "__");
            }
        }


        return buffer.toString();
    }

    public String showRestaurantNoSelected() {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        StringBuffer buffer = new StringBuffer();


        Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID2));
            String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
            String type = cursor.getString(cursor.getColumnIndex(myDbHelper.TYPE));
            String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
            String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
            String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
            String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
            String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

            buffer.append(nameindb + "__");
        }


        return buffer.toString();
    }

    public String showWhere(String restName) {

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID2, myDbHelper.RESTNAME, myDbHelper.TYPE, myDbHelper.TIME, myDbHelper.RECOMMEND, myDbHelper.PRICE, myDbHelper.LAT, myDbHelper.LONG};
        StringBuffer buffer = new StringBuffer();


        Cursor cursor = db.query(myDbHelper.TABLE_NAME2, columns, "name = '" + restName + "'", null, null, null, null);

        cursor.moveToNext();
        String nameindb = cursor.getString(cursor.getColumnIndex(myDbHelper.RESTNAME));
        String time = cursor.getString(cursor.getColumnIndex(myDbHelper.TIME));
        String recommend = cursor.getString(cursor.getColumnIndex(myDbHelper.RECOMMEND));
        String price = cursor.getString(cursor.getColumnIndex(myDbHelper.PRICE));
        String lat = cursor.getString(cursor.getColumnIndex(myDbHelper.LAT));
        String longt = cursor.getString(cursor.getColumnIndex(myDbHelper.LONG));

        buffer.append(nameindb + "__" + time + "__" + recommend + "__" + price + "__" + lat + "__" + longt);


        return buffer.toString();
    }

    /*public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME , myDbHelper.USERNAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }*/

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name

        private static final String TABLE_NAME = "user";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String USERNAME = "Username";    //Column II
        private static final String MyPASSWORD = "Password";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " VARCHAR(255) ," + MyPASSWORD + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


        private static final String TABLE_NAME2 = "restaurant";   // Table Name
        private static final String UID2 = "id";     // Column I (Primary Key)
        private static final String RESTNAME = "name";    //Column II
        private static final String TYPE = "type";    // Column III
        private static final String TIME = "time";    // Column IV
        private static final String RECOMMEND = "recommend";    // Column V
        private static final String PRICE = "price";    // Column VI
        private static final String LAT = "lat";    // Column VII
        private static final String LONG = "long";    // Column VIII
        private static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + UID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RESTNAME + " VARCHAR(255) ," + TYPE + " VARCHAR(225) ," +
                " " + TIME + " VARCHAR(255) ," + RECOMMEND + " VARCHAR(255), " + PRICE + " VARCHAR(255) ," + LAT + " VARCHAR(255) ," +
                " " + LONG + " VARCHAR(255));";
        private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;


        private Context context;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE2);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                db.execSQL(DROP_TABLE2);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

    }
}