package com.blackmoon.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.blackmoon.danhngon.MainActivity;
import com.blackmoon.danhngon.R;
import com.blackmoon.database.DatabaseHandler;
import com.blackmoon.database.IdiomItem;

public class IdiomWidgetProvider extends AppWidgetProvider {

	private static final String ACTION_CLICK = "ACTION_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// Get all ids
		ComponentName thisWidget = new ComponentName(context,
				IdiomWidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			// create some random data
			try {
				RemoteViews remoteViews = new RemoteViews(
						context.getPackageName(), R.layout.widget_layout);

				// update calender
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
				Date d = new Date();
				String dayOfTheWeek = sdf.format(d);

				Calendar calender = Calendar.getInstance();
				remoteViews.setTextViewText(R.id.tvUpdateDate, dayOfTheWeek
						+ "");
				remoteViews.setTextViewText(R.id.tvUpdateDay, d.getDate() + "");
				remoteViews.setTextViewText(R.id.tvUpdateMonth, d.getMonth()
						+ 1 + "");
				// get database
				DatabaseHandler dbHandler = new DatabaseHandler(context);
				IdiomItem item = dbHandler.getRandomIdiom();

				remoteViews.setTextViewText(R.id.tvUpdateEnglish,
						item.get_english());

				remoteViews.setTextViewText(R.id.tvUpdateVietnamese,
						item.get_vietnamese());

				remoteViews.setTextViewText(R.id.tvUpdateAuthor,
						item.get_author());

				// Register an onClickListener to change content
				Intent intent = new Intent(context, IdiomWidgetProvider.class);

				intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
						appWidgetIds);

				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				remoteViews.setOnClickPendingIntent(R.id.layoutIdiom,
						pendingIntent);

				// Register an onClickListener to open app

				Intent configIntent = new Intent(context, MainActivity.class);
				configIntent.setAction(Intent.ACTION_MAIN);
				PendingIntent configPendingIntent = PendingIntent.getActivity(
						context, 0, configIntent, 0);

				remoteViews.setOnClickPendingIntent(R.id.layoutCalender,
						configPendingIntent);

				// end function
				appWidgetManager.updateAppWidget(widgetId, remoteViews);

			} catch (ActivityNotFoundException e) {
				Toast.makeText(context.getApplicationContext(),
						"There was a problem loading the application: ",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
