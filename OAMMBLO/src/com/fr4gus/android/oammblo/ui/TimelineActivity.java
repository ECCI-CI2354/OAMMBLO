package com.fr4gus.android.oammblo.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fr4gus.android.oammblo.OammbloApp;
import com.fr4gus.android.oammblo.R;
import com.fr4gus.android.oammblo.bo.Tweet;
import com.fr4gus.android.oammblo.service.TwitterService;
import com.fr4gus.android.oammblo.service.TwitterServiceException;
import com.fr4gus.android.oammblo.service.TwitterServiceFactory;

public class TimelineActivity extends Activity {
	public static enum TweetType {
		TWEET, REPLY
	}

	ListView mTimeline;
	TimelineAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

		mTimeline = (ListView) findViewById(R.id.timeline_list);
		Button loadmore = new Button(this);
		loadmore.setText("Load More");
		mTimeline.addFooterView(loadmore);
		mTimeline.setEmptyView(findViewById(R.id.timeline_empty));
		// Opcion 1. Referencia directa al adapter
		// mAdapter = new TimelineAdapter(this, new ArrayList<Tweet>());
		// mTimeline.setAdapter(mAdapter);
		Object obj = getLastNonConfigurationInstance();
		List<Tweet> tweets = null;
		if (obj != null) {
			tweets = (List<Tweet>)obj;
			Log.d("", "Tweets recuperados" + tweets.size());
		} else {
			tweets =new ArrayList<Tweet>();
			Log.d("", "No hay tweets por recuperar");
		}
		mTimeline.setAdapter(new TimelineAdapter(this,
				tweets));
	}

	public void loadData(View view) {
		AsyncTask<Void, Void, List<Tweet>> task = new AsyncTask<Void, Void, List<Tweet>>() {

			@Override
			protected List<Tweet> doInBackground(Void... params) {
				TwitterService service = OammbloApp.getInstance().getService();
				List<Tweet> tweets = null;
				try {
					tweets = service.getTimeline();
				} catch (TwitterServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return tweets;
			}

			@Override
			protected void onPostExecute(List<Tweet> result) {
				// TimelineAdapter adapter = (TimelineAdapter) mTimeline
				// .getAdapter();
				TimelineAdapter adapter = (TimelineAdapter) ((HeaderViewListAdapter) mTimeline
						.getAdapter()).getWrappedAdapter();
				adapter.addList(result);
			}

		}.execute();
	}

	public void clearData(View view) {
		TimelineAdapter adapter = (TimelineAdapter) ((HeaderViewListAdapter) mTimeline
				.getAdapter()).getWrappedAdapter();
		adapter.clear();
	}

	@Override
	protected void onStart() {
		super.onStart();
//		Debug.startMethodTracing("despues_viewholder_debug");
	}

	@Override
	protected void onStop() {
		super.onStop();
//		Debug.stopMethodTracing();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return ((TimelineAdapter)((HeaderViewListAdapter) mTimeline.getAdapter()).getWrappedAdapter()).getTweets();
	}

	private static class TimelineAdapter extends BaseAdapter {
		List<Tweet> tweets = new ArrayList<Tweet>();
		LayoutInflater inflater;

		public TimelineAdapter(Context context, List<Tweet> tweets) {
			this.tweets = tweets;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addList(List<Tweet> tweets) {
			if (tweets == null) {
				return;
			}
			this.tweets.addAll(tweets);
			notifyDataSetChanged();
		}

		public List<Tweet> getTweets() {
			return tweets;
		}

		@Override
		public int getItemViewType(int position) {
			Tweet tweet = (Tweet) getItem(position);
			if (tweet.isReply()) {
				return TweetType.REPLY.ordinal();
			}
			return TweetType.TWEET.ordinal();
		}

		@Override
		public int getViewTypeCount() {
			return TweetType.values().length;
		}

		public int getCount() {
			return tweets.size();
		}

		public Object getItem(int position) {
			return tweets.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Tweet tweet = (Tweet) getItem(position);

			TweetViewHolder holder = null;
			if (convertView == null) {

				if (tweet.isReply()) {
					convertView = inflater.inflate(R.layout.list_item_reply,
							parent, false);

				} else {
					convertView = inflater.inflate(R.layout.list_item_tweet,
							parent, false);

				}
				holder = new TweetViewHolder();

				convertView.setTag(holder);
				int color = parent.getContext().getResources()
						.getColor(R.color.tweet_author);
				holder.authorName = (TextView) convertView
						.findViewById(R.id.timeline_item_displayname);
				holder.authorName.setTextColor(color);
				holder.content = (TextView) convertView
						.findViewById(R.id.timeline_tweet_content);

			} else {
				holder = (TweetViewHolder) convertView.getTag();
			}

			holder.authorName.setText(tweet.getAuthor().getDisplayName());
			holder.content.setText(tweet.getContent());

			return convertView;
		}

		public void clear() {
			tweets.clear();
			notifyDataSetChanged();
		}
	}

	private static class TweetViewHolder {
		public TextView authorName;
		public TextView content;
		public ImageView photoThumbnail;
		public TextView date;
	}
}
