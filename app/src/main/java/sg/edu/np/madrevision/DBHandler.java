package sg.edu.np.madrevision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import static sg.edu.np.madrevision.ListActivity.userList;

//Create your db handler class extending SQLiteOpenHelper.
// This class will create a new database upon initialization.
// There is a User table, whose structure follows the User class diagram.

public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "userDB.db"; //filename
    public static String TABLE_USER = "USER"; //table name
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESC = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESC + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"   //value of id is an auto-increment primary key.
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);

        for (int i = 0; i < 20; ++i) {
            Log.v("Create Table", "");
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, "Name" + randomNum());
            values.put(COLUMN_DESC, "Description" + randomNum());
            values.put(COLUMN_FOLLOWED, new Random().nextInt()%2 == 0);

            Log.v("User ", String.valueOf(values));
            db.insert(TABLE_USER, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Create a getUsers() function in your db handler.
    // This function will returns all the user information
    // from the database as a List.
    public ArrayList<User> getUsers() {
        ArrayList<User> userInfoList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int id = cursor.getInt(2);
                boolean followed = cursor.getInt(3) == 0;

                userInfoList.add(new User(name, description, id, followed));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();

        return userInfoList;
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_DESC,user.getDescription());
        values.put(COLUMN_ID,user.getId());
        values.put(COLUMN_FOLLOWED,user.getFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User findUser(String name){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_NAME +
                "=\"" + name + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        User queryData = new User();
        if(cursor.moveToFirst()){
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            int booleanNum = cursor.getInt(3);
            if(booleanNum == 0){
                queryData.setFollowed(true);
            }
            else{
                queryData.setFollowed(false);
            }

            cursor.close();
        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;
    }

    /*
    public void addListItem(){
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < 20; ++i) {
            Log.v("Create Table", "");
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, "Name" + randomNum());
            values.put(COLUMN_DESC, "Description" + randomNum());
            values.put(COLUMN_FOLLOWED, new Random().nextInt()%2 == 0);

            Log.v("User ", String.valueOf(values));
            db.insert(TABLE_USER, null, values);
            db.close();
        }
    }

     */

    public void updateUser(User u){
        SQLiteDatabase db = getReadableDatabase();
        int num;
        if(u.followed){
            num = 0;
        }
        else {
            num = 1;
        }
        String query = "UPDATE " + TABLE_USER + " SET " + COLUMN_FOLLOWED + " = " + num +
                " WHERE " + COLUMN_ID + " = " + u.id;
        db.execSQL(query);
        db.close();
    }

    public int randomNum(){
        return new Random().nextInt();
    }
}
