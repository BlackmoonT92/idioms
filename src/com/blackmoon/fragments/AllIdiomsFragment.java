package com.blackmoon.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.blackmoon.adapter.IdiomsListAdapter;
import com.blackmoon.danhngon.R;
import com.blackmoon.database.IdiomItem;
import com.blackmoon.database.DatabaseHandler;
import com.blackmoon.database.XMLReader;
import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AllIdiomsFragment extends Fragment {

	LoadMoreListView listViewAllIdioms;
	IdiomsListAdapter adapterIdioms;

	DatabaseHandler dbHandler;
	List<IdiomItem> listIdioms;

	int pageIndicator = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		// find view
		listViewAllIdioms = (LoadMoreListView) view
				.findViewById(R.id.listViewAllIdioms);

		// setData
		adapterIdioms = new IdiomsListAdapter(getActivity(),
				R.layout.row_danh_ngon_item);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		// setDataDefault
		// set Animation
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
				adapterIdioms);
		swingBottomInAnimationAdapter.setInitialDelayMillis(200);
		swingBottomInAnimationAdapter.setAbsListView(listViewAllIdioms);

		listViewAllIdioms.setAdapter(swingBottomInAnimationAdapter);
		listViewAllIdioms.setOnLoadMoreListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				new LoadMoreDataTask().execute();

			}
		});

		// test database
		dbHandler = new DatabaseHandler(getActivity());

		XMLReader xmlReader = new XMLReader(getActivity());
		//xmlReader.readXMl();
		new LoadMoreDataTask().execute();

	}

	public int getIdiomsByIndex() {
		listIdioms = new ArrayList<IdiomItem>();
		listIdioms = dbHandler.getAllDanhNgon(pageIndicator);
		pageIndicator += listIdioms.size();
		return listIdioms.size();
	}

	// =============loadmore listview

	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Step 1. Get PAGE_SIZE car of the page after mEndPageIndex to

			// hide load more progress at the end of list
			if (getIdiomsByIndex() < 20) {
				listViewAllIdioms.hideFooter();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			for (int i = 0; i < listIdioms.size(); i++) {
				adapterIdioms.add(listIdioms.get(i));
			}
			listIdioms.clear();

			adapterIdioms.notifyDataSetChanged();
			listViewAllIdioms.onLoadMoreComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			listViewAllIdioms.onLoadMoreComplete();
		}
	}

}
