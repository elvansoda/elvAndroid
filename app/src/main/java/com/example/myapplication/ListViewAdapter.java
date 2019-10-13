package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<listViewItem> listViewItemList = new ArrayList<listViewItem>();

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.price);
        TextView amountTextView = (TextView) convertView.findViewById(R.id.amount);
        listViewItem listViewItem = (listViewItem)getItem(position);

        nameTextView.setText(listViewItem.getName());
        priceTextView.setText(String.format(Locale.getDefault(), "%s%d%s", "개당 가격: ", listViewItem.getPrice(), "원"));
        amountTextView.setText(String.format(Locale.getDefault(), "%s%d%s", "재고수량: ", listViewItem.getAmount(), "개"));

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(String name, int price, int amount) {
        listViewItem item = new listViewItem();

        item.setName(name);
        item.setAmount(amount);
        item.setPrice(price);

        listViewItemList.add(item);
    }
}
