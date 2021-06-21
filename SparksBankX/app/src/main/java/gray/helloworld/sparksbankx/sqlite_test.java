package gray.helloworld.sparksbankx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
import java.util.SplittableRandom;

public class sqlite_test extends AppCompatActivity {

    EditText name,bankid,balance;
    Button insertButton,viewButton,dummyButton,dropButton;
    private int srno;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_test);

        name=findViewById(R.id.nameTF);
        bankid=findViewById(R.id.bankidTF);
        balance=findViewById(R.id.balanceTF);

        insertButton=findViewById(R.id.insertButton);
        viewButton=findViewById(R.id.viewButton);
        dummyButton=findViewById(R.id.dummyButton);
        dropButton=findViewById(R.id.dropButton);

        DB=new DBHelper(this);


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor count=DB.getData();
                srno=count.getCount()+1;
                String Sname=name.getText().toString();
                String Sbankid=bankid.getText().toString();
                String Sbalance= balance.getText().toString();
                int finalValue=Integer.parseInt(Sbalance);


                Boolean checkinsert = DB.insertUserData(srno,Sname,Sbankid,finalValue);
                if(checkinsert==true){
                    Toast.makeText(sqlite_test.this, "Succesfully inserted", Toast.LENGTH_SHORT).show();
                    srno++;
                }else{
                    Toast.makeText(sqlite_test.this, "Unsuccesfull", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result= DB.getData();
                if(result.getCount()==0){
                    Toast.makeText(sqlite_test.this, "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("Srno :"+result.getString(0)+"\n");
                    buffer.append("Name :"+result.getString(1)+"\n");
                    buffer.append("Bankid :"+result.getString(2)+"\n");
                    buffer.append("Balance :"+result.getString(3)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(sqlite_test.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });

        dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDummy();
            }
        });

        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.dropTables();
            }
        });

    }

    public void insertDummy(){
        Cursor count=DB.getData();
        srno=count.getCount()+1;
        Boolean checkinsert;
        DB.insertUserData(srno,"Jenslee Dsouza",gen(),9125);srno++;
        DB.insertUserData(srno,"Clive Dsouza",gen(),10300);srno++;
        DB.insertUserData(srno,"Paras Jadhav",gen(),10000);srno++;
        DB.insertUserData(srno,"Nikhil Lobo",gen(),20000);srno++;
        DB.insertUserData(srno,"Selina Ger",gen(),25000);srno++;
        DB.insertUserData(srno,"Mukunda Gujjar",gen(),87000);srno++;
        DB.insertUserData(srno,"Aidan Moraes",gen(),34000);srno++;
        DB.insertUserData(srno,"Ronnan Lobo",gen(),90000);srno++;
        DB.insertUserData(srno,"Karan Chalke",gen(),63000);srno++;
        checkinsert = DB.insertUserData(srno,"Joshua Dsouza",gen(),100000);srno++;

        if(checkinsert==true){
            Toast.makeText(sqlite_test.this, "Dummy succesfully inserted", Toast.LENGTH_SHORT).show();
            srno++;
        }else{
            Toast.makeText(sqlite_test.this, "Dummy Unsuccesfull", Toast.LENGTH_SHORT).show();
        }

    }
    public String gen()
    {
        Random r = new Random( System.currentTimeMillis() );
        return String.valueOf((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

}