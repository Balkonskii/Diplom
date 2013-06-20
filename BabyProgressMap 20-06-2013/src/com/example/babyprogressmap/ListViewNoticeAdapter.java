package com.example.babyprogressmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewNoticeAdapter extends BaseAdapter {
	private LayoutInflater mInflater = null;
	private ArrayList<Notice> notifications;

	private final class ViewHolder {		
		TextView textView_datetime;
		TextView textView_title;	
		TextView textView_description;	
	}

	private int selectedChildrenId = 0;
	private int selectedPosition = 0;
	private ViewHolder mHolder = null;

	public ListViewNoticeAdapter(Context context) {
		Context mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		notifications = new ArrayList<Notice>();
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int newPos) {
		selectedPosition = newPos;
		selectedChildrenId = notifications.get(newPos).getId();
	}
	
	public int getSelectedChildrenId() {
		return selectedChildrenId;
	}


	public void addItem(Notice notice) {
		notifications.add(notice);
	}

	@Override
	public int getCount() {
		return notifications.size();
	}

	@Override
	public Object getItem(int position) {
		return notifications.get(position);
	}

	@Override
	public long getItemId(int position) {
		return notifications.get(position).getId();
	}
	
	public ArrayList<Notice> getCollection()
	{
		return notifications;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_notice, null);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.textView_title = (TextView) convertView
				.findViewById(R.id.textView_title);
		mHolder.textView_title.setText(notifications.get(position).getTitle());

		mHolder.textView_description = (TextView) convertView
				.findViewById(R.id.textView_description);
		mHolder.textView_description.setText(notifications.get(position).getDescription());
		
		SimpleDateFormat format = new SimpleDateFormat(DataAdapter.DATE_TIME_FORMAT_REVERSED);
		String dt = format.format(notifications.get(position).getNotifyDateTime());
		
		mHolder.textView_datetime = (TextView) convertView
				.findViewById(R.id.textView_datetime);
		mHolder.textView_datetime.setText(dt);
				

		if (selectedPosition == position) {
//			convertView.setSelected(true);
//			convertView.setPressed(true);
//			convertView.setBackgroundColor(Color.GREEN);
			selectedChildrenId = notifications.get(position).getId();
		} else {
//			convertView.setSelected(false);
//			convertView.setPressed(false);
//			convertView.setBackgroundColor(Color.WHITE);
		}
		
		return convertView;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}
}