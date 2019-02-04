package com.example.homer.rex;

//                Matthew Brennan SDA Assignment-4  CollectionLocationsAdapter Feb 2019
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
/*
 * {@link CollectionLocationsAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Location tList} objects.
 * */
public class CollectionLocationsAdapter  extends ArrayAdapter<CollectionLocations> {

    private static final String LOG_TAG = CollectionLocationsAdapter.class.getSimpleName();

    /**
     * This is a custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param collectionLocations A List of AndroidFlavor objects to display in a list
     */
    public CollectionLocationsAdapter(Activity context, ArrayList<CollectionLocations> collectionLocations){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        super(context,0, collectionLocations);
    }
    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_collection, parent, false);
        }

        // Get the {@link CollectionLocations} object located at this position in the list
        CollectionLocations currentCollectionLocations = getItem(position);

        // Find the TextView in the list_item_collection.xml layout with the ID city_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.city_name);
        // Get the city name from the current CollectionLocations object and
        // set this text on the city_name TextView
        assert currentCollectionLocations != null;
        nameTextView.setText(currentCollectionLocations.getColLocationName());

        // Find the TextView in the list_item_collection.xml  layout with the ID city_addr
        TextView addrTextView = (TextView) listItemView.findViewById(R.id.city_addr);
        // Get the version number from the current CollectionLocations object and
        // set this text on the city_addr TextView
        addrTextView.setText(currentCollectionLocations.getColLocationAddr());

        // Find the TextView in the list_item_collection.xml  layout with the ID city_phone
        TextView phoneTextView = (TextView) listItemView.findViewById(R.id.city_phone);
        // Get the version number from the current CollectionLocations object and
        // set this text on the city_phone TextView
        phoneTextView.setText(currentCollectionLocations.getColLocationPhone());

        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
