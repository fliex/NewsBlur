package com.newsblur.database;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsblur.R;
import com.newsblur.domain.Feed;
import com.newsblur.domain.Story;

public class FeedItemsAdapter extends SimpleCursorAdapter {

	private Cursor cursor;
	private Context context;
	private final Feed feed;
	private int darkGray;
	private int lightGray;

	public FeedItemsAdapter(Context context, Feed feed, int layout, Cursor c, String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		this.context = context;
		this.feed = feed;
		this.cursor = c;
		
		darkGray = context.getResources().getColor(R.color.darkgray);
		lightGray = context.getResources().getColor(R.color.lightgray);
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Cursor swapCursor(Cursor c) {
		this.cursor = c;
		return super.swapCursor(c);
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		View v = super.getView(position, view, viewGroup);
		View borderOne = v.findViewById(R.id.row_item_favicon_borderbar_1);
		View borderTwo = v.findViewById(R.id.row_item_favicon_borderbar_2);

		cursor.moveToPosition(position);

		if (!TextUtils.equals(feed.faviconColour, "#null") && !TextUtils.equals(feed.faviconFade, "#null")) {
			borderOne.setBackgroundColor(Color.parseColor(feed.faviconBorder));
			borderTwo.setBackgroundColor(Color.parseColor(feed.faviconColour));
		} else {
			borderOne.setBackgroundColor(Color.GRAY);
			borderTwo.setBackgroundColor(Color.LTGRAY);
		}

		if (TextUtils.equals(Story.fromCursor(cursor).read, "1")) {
			((TextView) v.findViewById(R.id.row_item_author)).setTextColor(lightGray);
			((TextView) v.findViewById(R.id.row_item_date)).setTextColor(lightGray);
		} else {
			((TextView) v.findViewById(R.id.row_item_author)).setTextColor(darkGray);
			((TextView) v.findViewById(R.id.row_item_date)).setTextColor(darkGray);
		}

		return v;
	}

}