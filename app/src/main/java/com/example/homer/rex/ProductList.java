package com.example.homer.rex;

//                Matthew Brennan SDA Assignment-4  ProductList Feb 2019

/**
 * {@link ProductList} represents a list of T-Shirts each with different Logos.
 * Each object (T-Shirt) has 3 properties: name, price, and image resource ID.
 */

//ProductList
public class ProductList {
    // T-Shirt Logo)
    private String mShirtLogo;
    // T-Shirt Price
    private String mCost;
    // Drawable resource ID
    private int mImageResourceId;
    /*
     * Create a new ProductList object.
     *
     * @param vLogo is the Logo on the T-Shirt)
     * @param vCost is the corresponding price of the T-shirt)
     * @param image is drawable reference ID that corresponds to the T-Shirt
     * */
    public ProductList(String vShirtLogo, String vCost, int imageResourceId)
    {
        mShirtLogo = vShirtLogo;
        mCost = vCost;
        mImageResourceId = imageResourceId;
    }
    /**
     * Get the name of the T-Shirt
     */
    public String getmShirtLogo() { return mShirtLogo;  }

    /**
     * Get the price of the T-Shirt
     */
    public String getCost() {
        return mCost;
    }

    /**
     * Get the image resource ID  of the T-Shirt
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }
}
