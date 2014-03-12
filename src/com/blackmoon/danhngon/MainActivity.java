package com.blackmoon.danhngon;

import java.util.ArrayList;

import org.json.JSONArray;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.blackmoon.Utils.ConfigData;
import com.blackmoon.adapter.NavigationDrawerAdapter;
import com.blackmoon.adapter.NavigationDrawerListItem;
import com.blackmoon.adapter.OnItemClickHandler;
import com.blackmoon.fragments.AllIdiomsFragment;
import com.blackmoon.fragments.FavoriteIdiomsFragment;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener, LoaderCallbacks<Cursor>, OnMenuItemClickListener {

	private ArrayList<NavigationDrawerListItem> mNavigationDrawerListItems;
	private NavigationDrawerAdapter mNavigationDrawerAdapter;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	// For listen to Open & Close NavigationDrawer menu
	private ActionBarDrawerToggle mDrawerToggle;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ConfigData.activityMain = getApplicationContext();
		ConfigData.actionBar = getSupportActionBar();

		/**
		 * 1. Initial for NavigationDrawer
		 */
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mNavigationDrawerListItems = new ArrayList<NavigationDrawerListItem>();

		// Create & Add Items CATEGORY "Application"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.CATEGORY_NAME, 0, this
						.getString(R.string.label_application), "", null));

		// Create & Add Items "store"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.all,
				this.getString(R.string.label_all), "",
				new OnItemClickHandler() {
					@Override
					public void doAction() {
						Fragment mFragment = new AllIdiomsFragment();
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.content_frame, mFragment)
								.commit();

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_all));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// Create & Add Items "oders"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.topic,
				this.getString(R.string.label_topic), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_topic));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// Create & Add Items "favorites"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.like,
				this.getString(R.string.label_favorites), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {
						Fragment mFragment = new FavoriteIdiomsFragment();
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.content_frame, mFragment)
								.commit();

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_favorites));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// Create & Add Items "love_shops"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.trophy,
				this.getString(R.string.label_award), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_award));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// =========================JUDDY====================================

		// Create & Add Items CATEGORY "Juddy"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.CATEGORY_NAME, 0, this
						.getString(R.string.label_juddy), "", null));

		// Create & Add Items "Account"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.apps,
				this.getString(R.string.label_apps), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_apps));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// Create & Add Items "setting"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY,
				R.drawable.setting, this.getString(R.string.label_setting), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_setting));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// Create & Add Items "about"
		mNavigationDrawerListItems.add(new NavigationDrawerListItem(
				NavigationDrawerListItem.SCREEN_IN_CATEGORY, R.drawable.about,
				this.getString(R.string.label_about), "",
				new OnItemClickHandler() {

					@Override
					public void doAction() {

						// Highlight the selected item, update the title, and
						// close the drawer
						mDrawerList.setItemChecked(
								mNavigationDrawerListItems.size(), true);
						getSupportActionBar().setTitle(
								getString(R.string.label_about));
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}));

		// =============default screen is store===========================
		// Show Search Car as default screen
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, new AllIdiomsFragment()).commit();

		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(mNavigationDrawerListItems.size(), true);
		getSupportActionBar().setTitle(R.string.label_all);
		// =============Config Navigation Drawer==========================

		// Create NavigationDrawer List adapter
		mNavigationDrawerAdapter = new NavigationDrawerAdapter(this,
				mNavigationDrawerListItems);

		// Create adapter for the Items
		mDrawerList.setAdapter(mNavigationDrawerAdapter);

		// Set the list's click listener
		mDrawerList.setOnItemClickListener(this);

		/**
		 * 2. Setting for the main activity can be listen and handle when
		 * NavigationDarwer Toggle(Open & Close)
		 */
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Enable ActionBar application icon to behave as action to toggle
		// navigation drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// action search in actionbar
		handleIntent(getIntent());

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the application icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		try {
			mNavigationDrawerListItems.get(position).getOnItemClickHandler()
					.doAction();
		} catch (NullPointerException e) {

		}
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// =================MENU CONTEXT=========================

	private void handleIntent(Intent intent) {
		if (intent != null) {
			if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
				doSearch(intent.getStringExtra(SearchManager.QUERY));
			} else if (intent.getAction().equals(Intent.ACTION_VIEW)) {
				getPlace(intent.getStringExtra(SearchManager.EXTRA_DATA_KEY));
			}
		}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// super.onNewIntent(intent);
		setIntent(intent);
		handleIntent(intent);
	}

	private void doSearch(String query) {
		Toast.makeText(getApplicationContext(), query, 0).show();
		ConfigData.storeName = query;
		// reset static data
		ConfigData.allProducts = new JSONArray();

		Bundle data = new Bundle();
		data.putString("query", query);
		getSupportLoaderManager().restartLoader(0, data, this);
	}

	private void getPlace(String query) {
		Bundle data = new Bundle();
		data.putString("query", query);
		getSupportLoaderManager().restartLoader(1, data, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		MenuItem searchItem = menu.findItem(R.id.action_search);

		SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(searchItem);

		// searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		// searchView.setIconifiedByDefault(false); // Do not iconify the
		// widget; expand it by default

		/*
		 * // action order MenuItem orderItem =
		 * menu.findItem(R.id.action_order);
		 * 
		 * orderItem.setOnMenuItemClickListener(this);
		 */

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		/*
		 * switch (item.getItemId()) { case R.id.action_order: Fragment f = new
		 * ThanhNguFragment(); getSupportFragmentManager().beginTransaction()
		 * .replace(R.id.content_frame, f)
		 * .addToBackStack("STORE_FRAGMENT").commit(); break;
		 * 
		 * default: break; }
		 */

		return false;
	}

}