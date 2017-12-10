package com.example.android.earthreport.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.earthreport.Map;
import com.example.android.earthreport.R;
import com.example.android.earthreport.model.DataProvider;
import com.example.android.earthreport.network.GetEarthquakeData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class YesterdayFragment extends Fragment {

    public final static String YESTERDAY = "Yesterday";
    private ListView earthquakeListViewYesterday;


    public YesterdayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yesterday, container, false);

        //Find the reference of Listview
        earthquakeListViewYesterday = view.findViewById(R.id.yesterdayList);

        //Data replicate in listview due to this I add for just when view appear listview
        // instance recereate and assigned (check):
        DataProvider.valuesList = new ArrayList<>();


        //Yesterday Url
        String url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

        // here we can give the argument in execute the argument could be the `url`
        //to get data from web
        new GetEarthquakeData(getContext(), earthquakeListViewYesterday).execute(url);

        //Add list view listener to open detail activity of each list view value
        earthquakeListViewYesterday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Log.i("URL: ",values.get(position).getUrl());
                //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(values.get(position).getUrl()));
                Intent intent = new Intent(getContext(), Map.class);

                Bundle bundle = new Bundle();

                bundle.putDouble("LONGITUDE", DataProvider.valuesList.get(position).getLongitude());
                bundle.putDouble("LATITUDE", DataProvider.valuesList.get(position).getLatitude());
                bundle.putString("CITY", DataProvider.valuesList.get(position).getCityname());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        return view;

    }

}