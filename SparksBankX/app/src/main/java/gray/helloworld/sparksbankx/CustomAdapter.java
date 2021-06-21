package gray.helloworld.sparksbankx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList srno,name,bankid,balance;
    private OnItemListener mOnItemListener;


    CustomAdapter(Context context,ArrayList srno,ArrayList name,ArrayList bankid,ArrayList balance,OnItemListener onItemListener){
        this.context=context;
        this.srno=srno;
        this.name=name;
        this.bankid=bankid;
        this.balance=balance;
        this.mOnItemListener=onItemListener;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
         return new MyViewHolder(view,mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.srnotv.setText(String.valueOf(srno.get(position)));
        holder.nametv.setText(String.valueOf(name.get(position)));
        holder.bankidtv.setText(String.valueOf(bankid.get(position)));
        holder.balancetv.setText(String.valueOf(balance.get(position)));


    }

    @Override
    public int getItemCount() {
        return srno.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView srnotv,nametv,bankidtv,balancetv;
        
        OnItemListener onItemListener;


        public MyViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            srnotv=itemView.findViewById(R.id.srnoTV);
            nametv=itemView.findViewById(R.id.customerNameTV);
            bankidtv=itemView.findViewById(R.id.customerBankIdTV);
            balancetv=itemView.findViewById(R.id.customerBalanceTV);

            this.onItemListener=onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());

        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}
