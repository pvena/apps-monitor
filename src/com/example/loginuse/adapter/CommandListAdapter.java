package com.example.loginuse.adapter;

import java.util.ArrayList;

import com.example.loginuse.rule.LogRuleData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class CommandListAdapter extends BaseAdapter {

	    private Context context;
	    private ArrayList<LogRuleData> conditions;
	    private int layout;

	    public CommandListAdapter(Context context, ArrayList<LogRuleData> persons,int layout) {
	        this.context = context;
	        this.conditions = persons;
	        this.layout = layout;
	    }

	    @Override
	    public int getCount() {
	        return this.conditions.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return this.conditions.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {

	        TwoLineListItem twoLineListItem;
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            twoLineListItem = (TwoLineListItem) inflater.inflate(
	                    this.layout, null);
	        } else {
	            twoLineListItem = (TwoLineListItem) convertView;
	        }

	        TextView text1 = twoLineListItem.getText1();
	        TextView text2 = twoLineListItem.getText2();

	        text1.setText(this.conditions.get(position).getName());
	        text2.setText(this.conditions.get(position).getConditionsData());

	        return twoLineListItem;
	    }	
}
