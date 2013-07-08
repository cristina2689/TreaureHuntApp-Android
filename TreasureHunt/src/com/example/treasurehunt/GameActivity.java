package com.example.treasurehunt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.treasurehunt.gameendpoint.Gameendpoint;
import com.example.treasurehunt.gameendpoint.model.CollectionResponseGame;
import com.example.treasurehunt.gameendpoint.model.Game;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.View;


public class GameActivity extends Activity {

	public ListView listview; 
	public ArrayList<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		listview = (ListView) findViewById(R.id.listview);
		list = new ArrayList<String>();
		
		
		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter((ListAdapter) adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int myItemInt, long id) {

				String selectedFromList = (String) (listview.getItemAtPosition(myItemInt));
				System.out.println(selectedFromList);
				System.out.println(id);

			}
		});
		new EndpointsTask().execute(getApplicationContext());
	}
	
	
	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {

			// Game endpoint
			Gameendpoint.Builder endpointBuilder2 = new Gameendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Gameendpoint endpoint2 = CloudEndpointUtils.updateBuilder(
					endpointBuilder2).build();
			
			try {
				CollectionResponseGame response = new CollectionResponseGame();
				response = endpoint2.listGame().execute();
				List<Game> games = response.getItems();
				for (Game g : games){
					Log.d("SERVER","exista joc");
					list.add(g.getName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return (long) 0;
		}
	}
	
	
	public void startGameAction(View button) {
		 Intent startMap = new Intent(this, SearchActivity.class);
		 startActivity(startMap);
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	}
}