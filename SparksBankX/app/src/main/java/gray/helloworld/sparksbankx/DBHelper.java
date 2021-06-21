package gray.helloworld.sparksbankx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME="BankX.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME = "customer_list";
    private static final String SRNO="_id";
    private static final String NAME="customer_name";
    private static final String BANKID="bank_id";
    private static final String BALANCE="balance";

    private static final String TRANSACTION_TABLE="transactions";
    private static final String PAYEE="payee";
    private static final String RECEIVER="receiver";
    private static final String AMOUNT="amount";
    private static final String DATE="date";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ TABLE_NAME +
                        " ("+ SRNO + " INTEGER PRIMARY KEY, "+
                        NAME + " TEXT, " +
                        BANKID + " TEXT, " +
                        BALANCE + " INTEGER);";
        db.execSQL(query);

        query="CREATE TABLE "+ TRANSACTION_TABLE +
                " ("+ SRNO + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PAYEE + " TEXT, " +
                RECEIVER + " TEXT, " +
                DATE + " TEXT, " +
                AMOUNT + " TEXT);";
        db.execSQL(query);
        //db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(1,'asd','asd','asd');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE);
        onCreate(db);

    }

    public Boolean insertUserData(int srno,String name,String bankid,int balance){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(SRNO,srno);
        cv.put(NAME,name);
        cv.put(BANKID,bankid);
        cv.put(BALANCE,balance);
        long result=DB.insert(TABLE_NAME,null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean insertTransactions(String payee,String receiver,String date,String amount){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(PAYEE,payee);
        cv.put(RECEIVER,receiver);
        cv.put(DATE,date);
        cv.put(AMOUNT,amount);
        long result=DB.insert(TRANSACTION_TABLE,null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from "+TABLE_NAME,null);
        return cursor;

    }

    public void dropTables(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("DROP TABLE " + TABLE_NAME);
        DB.execSQL("DROP TABLE " + TRANSACTION_TABLE);
        String query="CREATE TABLE "+ TABLE_NAME +
                " ("+ SRNO + " INTEGER PRIMARY KEY, "+
                NAME + " TEXT, " +
                BANKID + " TEXT, " +
                BALANCE + " INTEGER);";
        DB.execSQL(query);

        query="CREATE TABLE "+ TRANSACTION_TABLE +
                " ("+ SRNO + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PAYEE + " TEXT, " +
                RECEIVER + " TEXT, " +
                DATE + " TEXT, " +
                AMOUNT + " TEXT);";
        DB.execSQL(query);

    }
}
