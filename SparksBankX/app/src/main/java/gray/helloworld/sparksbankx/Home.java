package gray.helloworld.sparksbankx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {
    private Intent startCustomer,startDeposit;
    private ImageButton customerList,deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startCustomer=new Intent(Home.this,CustomerList.class);
        startDeposit=new Intent(Home.this,Deposit.class);

        customerList=findViewById(R.id.customerButton);
        deposit=findViewById(R.id.depositButton);



        customerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.this.startActivity(startCustomer);
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.this.startActivity(startDeposit);
            }
        });

    }
}