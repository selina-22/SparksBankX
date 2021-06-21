package gray.helloworld.sparksbankx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity implements CustomAdapter.OnItemListener{
    private Button addCustomer;

    RecyclerView recyclerView;
    
    DBHelper DB;
    ArrayList<String> srno,name,bankid,balance;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        recyclerView=findViewById(R.id.recyclerView);

        addCustomer=findViewById(R.id.addButton);

        DB=new DBHelper(this);
        srno= new ArrayList<>();
        name= new ArrayList<>();
        bankid= new ArrayList<>();
        balance= new ArrayList<>();
        storeDatainArrays();

        customAdapter= new CustomAdapter(CustomerList.this,srno,name,bankid,balance,this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CustomerList.this));




        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CustomerList.this,sqlite_test.class);
                CustomerList.this.startActivity(intent); 
            }
        });
    }

    void storeDatainArrays(){
        Cursor result=DB.getData();
        if(result.getCount()==0){
            Toast.makeText(this, "No data exists in the DB", Toast.LENGTH_SHORT).show();
        }
        while(result.moveToNext()){
            srno.add(result.getString(0));
            name.add(result.getString(1));
            bankid.add(result.getString(2));
            balance.add(result.getString(3));

        }

    }

    @Override
    public void onItemClick(int position) {
        String no=name.get(position);
        Toast.makeText(this, no+" was pressed", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(CustomerList.this,CustomerInfo.class);
        CustomerList.this.startActivity(intent);
    }
}