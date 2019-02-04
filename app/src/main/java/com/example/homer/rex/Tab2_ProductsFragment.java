package com.example.homer.rex;

//                Matthew Brennan SDA Assignment-4  Tab2_ProductsFragment Feb 2019

/*
 * {@link Tab2_ProductsFragment} This shows an array of ProductList objects, each object is created from the constructor
 * of the ProductList class
 * For each product, the T-Shire name, price, and an image is displayed.
 * A listener is put on the ViewList and when an item is selected its name is displayed in a Toast
 * and also it is recorded in memory with  SharedPreferences
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

// Tab2_ProductsFragment Class
public class Tab2_ProductsFragment extends Fragment {
    // LogCat Tag
    private static final String TAG = "Tab2_ProductsFragment";

    @Override
    // onCreate tab2_products layout is inflated
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      final View rootview = inflater.inflate(R.layout.tab2_products, container, false);

        // Create an ArrayList of ProductList objects
        final ArrayList<ProductList> productList = new ArrayList<ProductList>();
        productList.add(new ProductList("COOL CODER", "Euro 18.50", R.drawable.coolcoder));
        productList.add(new ProductList("BASIC", "Euro 18.40", R.drawable.basic));
        productList.add(new ProductList("C", "Euro 17.00", R.drawable.c));
        productList.add(new ProductList("C +", "Euro 16.00", R.drawable.cplus));
        productList.add(new ProductList("HTML", "Euro 17.00", R.drawable.html));
        productList.add(new ProductList("JAVA", "Euro 18.50", R.drawable.java));
        productList.add(new ProductList("MATHLAB", "Euro 19.00", R.drawable.math_lab));
        productList.add(new ProductList("PYTHON", "Euro 18.25", R.drawable.python));
        productList.add(new ProductList("SQL", "Euro 19.00", R.drawable.sql));
        productList.add(new ProductList("XML", "Euro 18.00", R.drawable.xml));
        Log.d(TAG, "ArraytList for Tab2_ProductsFragment created ");

        // Create an {@link ProductListAdapter}, whose data source is a list of
        // {@link ProductList}s. The adapter knows how to create list item views for each item
        // in the list.
       ProductListAdapter productListAdapter = new ProductListAdapter(getActivity(), productList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView lv = (ListView) rootview.findViewById(R.id.list_products);
        lv.setAdapter(productListAdapter);

        //The following Set a  listener on the lv ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // When an item in the listView is clicked the onItemClick returns 'parent, view, int and id (number in the listView) of the item clicked
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // The following uses the 'id' of the listView item clicked to identify the selected object in the Arraylist 'productList'
                // and uses the getter method of that object to return the  name of the selected T-shirt.
                String nameOfItemClicked = productList.get((int) id).getmShirtLogo();
                String toastText = nameOfItemClicked;
                // The Toast below then displayes toastText (name of the selected T-Shirt)
                Toast.makeText(getContext(), "T-Shirt with  ' " +toastText+ "'  LOGO Added", Toast.LENGTH_SHORT).show();

                // The following uses SharedPreferences to stores the name of each selected T-Shirt
                SharedPreferences mSettings = getActivity().getSharedPreferences("prod_Lst", Context.MODE_PRIVATE);
                //SharedPreferences Editor
                SharedPreferences.Editor editor = mSettings.edit();
                // The toastText (name of the selected T-Shirt) is stored then, and also with key called toastText
                editor.putString(toastText,toastText);
                editor.commit();
            }
        });
        return rootview;
    }
}
