package com.example.homer.rex;

//                Matthew Brennan SDA Assignment-4  Tab4_CollectionFragment Feb 2019

/*
 * {@link Tab4_CollectionFragment} This shows an array of CollectionLocations objects, each object is created from the constructor
 * of the CollectionLocations class
 * For each collection point, there is a City name, an Address and a Phone Number.
 * A listener is put on the ViewList and when an item is selected its name is displayed in a Toast
 * and also it is recorded in memory with  SharedPreferences
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
// Tab4_CollectionFragment class
public class Tab4_CollectionFragment extends Fragment {
    //Logcat TAG
    private static final String TAG = "Tab4_CollectionFragment";

    @Nullable
    @Override
    // onCreate tab4_collection layout is inflated
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.tab4_collection, container, false);

        // Create an ArrayList of CollectionLocation objects
       final ArrayList<CollectionLocations> collectionLocations =new ArrayList<CollectionLocations>();

        collectionLocations.add(new CollectionLocations("Dublin", "5 St Stephens Green, Dublin 2, Dublin City", "PH No: (10) 345889"));
        collectionLocations.add(new CollectionLocations("Cork", "22 Lee House, Patricks St, Cork City", "PH No: (02) 345564"));
        collectionLocations.add(new CollectionLocations("Limerick", "21 O Connell St, limerick City", "PH No: (061) 282877"));
        collectionLocations.add(new CollectionLocations("Galway", "22 Salt Hill, Galway City", "PH No: (091) 227272"));
        collectionLocations.add(new CollectionLocations("Belfast", "16 The City Centre, Belfast City", "PH No: 028 123 4567"));
        collectionLocations.add(new CollectionLocations("Athlone", "16 Shannon St, Athlone Town", "PH No: 090 993 4567"));

        // Create an {@link CollectionLocationsAdapter}, whose data source is a list of
        // {@link CollectionLocations}s. The adapter knows how to create list item views for each item
        // in the list.
       CollectionLocationsAdapter coLoAdapter = new CollectionLocationsAdapter(getActivity(), collectionLocations);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView lv = (ListView) rootView.findViewById(R.id.list_locations);
        lv.setAdapter(coLoAdapter);

        //The following Set a  listener on the lv ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // When an item in the listView is clicked the onItemClick returns 'parent, view, int and id (number in the listView) of the item clicked
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // The following uses the 'id' of the listView item clicked to identify the selected object in the Arraylist 'collectionLocations'
                // and uses the getter method of that object to return the  name of the selected collection location.
                String nameOfItemClicked = collectionLocations.get((int) id).getColLocationName();
                String toastText = nameOfItemClicked;
                // The Toast below then displayes toastText (name of the selected collection location)
                Toast.makeText(getContext(), toastText + " " +getString(R.string.selectLocation), Toast.LENGTH_SHORT).show();

                // The following uses SharedPreferences to stores the name of each selected collection location
                SharedPreferences mSettings = getActivity().getSharedPreferences("Coll_location", Context.MODE_PRIVATE);
                //SharedPreferences Editor
                SharedPreferences.Editor editor = mSettings.edit();
                // The toastText (name of the selected collection location) is stored then, and also with key called toastText
                editor.putString("keyCL",toastText);
                editor.commit();
            }

        });
        return rootView;
    }
}
