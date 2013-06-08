package com.example.babyprogressmap;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewChildrenAdapter extends BaseAdapter {
	private LayoutInflater mInflater = null;
	private ArrayList<Children> childrens;

	private final class ViewHolder {
		ImageView imageView_awatar;
		TextView textView_name;		
	}

	private int selectedChildrenId = 0;
	private int selectedPosition = 0;
	private ViewHolder mHolder = null;

	public ListViewChildrenAdapter(Context context) {
		Context mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		childrens = new ArrayList<Children>();
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int newPos) {
		selectedPosition = newPos;
		selectedChildrenId = childrens.get(newPos).getId();
	}
	
	public int getSelectedChildrenId() {
		return selectedChildrenId;
	}


	public void addItem(Children child) {
		childrens.add(child);
	}

	@Override
	public int getCount() {
		return childrens.size();
	}

	@Override
	public Object getItem(int position) {
		return childrens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return childrens.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_children, null);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.imageView_awatar = (ImageView) convertView
				.findViewById(R.id.imageView_awatar);
		mHolder.imageView_awatar.setImageBitmap(DataManager
				.getImageFromBytes(childrens.get(position).getAwatar()));

		mHolder.textView_name = (TextView) convertView
				.findViewById(R.id.textView_name);
		mHolder.textView_name.setText(childrens.get(position).getName());

		if (selectedPosition == position) {
			convertView.setSelected(true);
			convertView.setPressed(true);
			convertView.setBackgroundColor(Color.GREEN);
			selectedChildrenId = childrens.get(position).getId();
		} else {
			convertView.setSelected(false);
			convertView.setPressed(false);
			convertView.setBackgroundColor(Color.WHITE);
		}
		
		return convertView;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}
}