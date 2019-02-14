package com.example.wishlist;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listview_adapter extends RecyclerView.Adapter<listview_adapter.ViewHolder> {

    private String[] data;
    private String[] price;
    private String[] RedColor;

    private Onclickinterface mlistener;

    public interface Onclickinterface {
        void Onclickiteminterface(int position);
    }

    public void setOnclicklistener(Onclickinterface onclickinterface) {
        mlistener = onclickinterface;
    }


    public listview_adapter(String[] data, String[] price, String[] RedColor) {
        this.data = data;
        this.price = price;
        this.RedColor = RedColor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = data[i];
        String priceint = price[i];
        String red = RedColor[i];

        if (red != null) {
            if (red.equals("red")) {
                viewHolder.tv1.setTextColor(Color.parseColor("#ff0000"));
                viewHolder.pricetv.setTextColor(Color.parseColor("#ff0000"));
            }
        }
        viewHolder.tv1.setText(title);
        viewHolder.pricetv.setText(priceint);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv1;
        public TextView tv1, pricetv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv);
            pricetv = itemView.findViewById(R.id.textviewprice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.Onclickiteminterface(position);
                        }
                    }
                }
            });

        }
    }
}
