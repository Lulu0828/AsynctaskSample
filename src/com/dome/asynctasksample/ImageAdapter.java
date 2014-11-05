package com.dome.asynctasksample;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	
	private List<HashMap<String, Object>> listItems;
	private LayoutInflater listContainer;
	
	private ImageView imageView;
	
	public ImageAdapter(Context context, List<HashMap<String, Object>> listItems) {
		super();
		this.listContainer = LayoutInflater.from(context);
		this.listItems = listItems;
	}

	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �Զ����ɵķ������
		if (convertView == null) {
			convertView = listContainer.inflate(R.layout.list_item, null);
			imageView = (ImageView)convertView.findViewById(R.id.imageView);
			convertView.setTag(imageView);
		} else {
			imageView = (ImageView)convertView.getTag();
		}
		imageView.setImageDrawable((Drawable) listItems.get(position).get("ItemImage"));
		return convertView;
	}

}
