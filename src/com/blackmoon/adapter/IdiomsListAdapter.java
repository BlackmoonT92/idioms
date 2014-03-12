package com.blackmoon.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blackmoon.Utils.ConfigData;
import com.blackmoon.danhngon.R;
import com.blackmoon.database.DatabaseHandler;
import com.blackmoon.database.IdiomItem;
import com.blackmoon.lazyloading.ImageLoader;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IdiomsListAdapter extends ArrayAdapter<IdiomItem> {
	// ==========================================
	// VARIABLES
	// ==========================================

	private Activity activity;
	LayoutInflater inflater;
	private List<IdiomItem> data = new ArrayList<IdiomItem>();

	public IdiomsListAdapter(Activity a, int textViewResourceId) {
		super(a, textViewResourceId);
		activity = a;
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void clear() {
		data.clear();
		super.clear();
	}

	@Override
	public void add(IdiomItem item) {
		data.add(item);
		notifyDataSetChanged();
		super.add(item);
	}
	
	@Override
	public void remove(IdiomItem object) {
		data.remove(object);
		notifyDataSetChanged();
		super.remove(object);
	}

	public int getCount() {
		return this.data.size();
	}

	public IdiomItem getItem(int index) {

		return this.data.get(index);

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(R.layout.row_danh_ngon_item, parent, false);

		}
		TextView tvEnglish = (TextView) vi.findViewById(R.id.tvEnglish);
		TextView tvVietnamese = (TextView) vi.findViewById(R.id.tvVietnamese);
		TextView tvAuthor = (TextView) vi.findViewById(R.id.tvAuthor);

		ImageButton ivShare = (ImageButton) vi.findViewById(R.id.ivShare);

		final ImageButton ivLike = (ImageButton) vi.findViewById(R.id.ivLike);

		// set data
		final IdiomItem item = data.get(position);
		if (item.get_favorite() != 0) {
			ivLike.setImageResource(R.drawable.favorite_clicked);
		} else {
			ivLike.setImageResource(R.drawable.favorite_default);
		}

		tvEnglish.setText(item.get_english() + "");
		tvVietnamese.setText(item.get_vietnamese() + "");
		tvAuthor.setText(item.get_author() + "");

		ivShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		ivLike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (item.get_favorite() != 0) {
					item.set_favorite(0);
					ivLike.setImageResource(R.drawable.favorite_default);
				} else {
					ivLike.setImageResource(R.drawable.favorite_clicked);
					item.set_favorite(1);
				}

				DatabaseHandler dbHandler = new DatabaseHandler(
						ConfigData.activityMain);

				dbHandler.updateIdomItem(item);

				Log.d("Like", "You Like " + item.get_id());

			}
		});

		return vi;

	}

}
