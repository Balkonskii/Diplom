package com.example.babyprogressmap;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewNoteAdapter extends BaseAdapter {
	private LayoutInflater mInflater = null;
	private ArrayList<Note> notes = new ArrayList<Note>();

	private final class ViewHolder {
		ImageView imageView;
		TextView textView_tittle;
		TextView textView_description;
	}

	private ViewHolder mHolder = null;

	public ListViewNoteAdapter(Context context) {
		Context mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// notes = new ArrayList<Note>();
	}

	public ArrayList<Note> getCollection() {
		return notes;
	}

	public void addItem(Note note) {
		notes.add(note);
	}

	@Override
	public int getCount() {
		return notes.size();
	}

	@Override
	public Object getItem(int position) {
		return notes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return notes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		boolean hasImage = notes.get(position).hasImage;
		// boolean hasImage = true;

//		if (convertView == null) {
			mHolder = new ViewHolder();
			if (hasImage)
				convertView = mInflater.inflate(R.layout.list_item_note, null);
			else {
				convertView = mInflater.inflate(
						R.layout.list_item_note_without_image, null);
			}
			convertView.setTag(mHolder);
//		} else {
//			mHolder = (ViewHolder) convertView.getTag();
//		}

		if (hasImage) {
			mHolder.imageView = (ImageView) convertView
					.findViewById(R.id.imageView);
//			try
//			{
			mHolder.imageView.setImageBitmap(DataManager
					.getImageFromBytes(notes.get(position).getPhoto()));
//			}
//			catch(NullPointerException e)
//			{
//				
//			}
		}
					
		mHolder.textView_tittle = (TextView) convertView
				.findViewById(R.id.textView_title);
		mHolder.textView_tittle.setText(notes.get(position).getTitle());

		mHolder.textView_description = (TextView) convertView
				.findViewById(R.id.textView_description);
		mHolder.textView_description.setText(notes.get(position)
				.getDescription());

		return convertView;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}
}