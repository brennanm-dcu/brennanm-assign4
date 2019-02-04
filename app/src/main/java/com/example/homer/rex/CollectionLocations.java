package com.example.homer.rex;

//                Matthew Brennan SDA Assignment-4  CollectionLocations Feb 2019

/**
 * {@link CollectionLocations} represents a list of collection Locations each with a City Name
 * , an Address and a Phone number.
 */
public class CollectionLocations {
    // Name of the collection location (e.g. Dublin, Cork, Galway ect)
    private String colLocationName;
    // Collection location Address)
    private String colLocationAddr;
    // Collection Location Phone No.
    private String colLocationPhone;
    /*
     *  Create a new CollectionLocations object.
     * @param colLocationName is the name of the Collection location (e.g. Galway)
     * @param colLocationAddr is the corresponding Collection Location address.
     * @param colLocationPhone is the corresponding Collection Location Phone No.
     **/
       public CollectionLocations(String coLoName, String colLoAddr, String colLoPhone)
    {
        colLocationName = coLoName;
        colLocationAddr = colLoAddr;
        colLocationPhone = colLoPhone;
    }
     // Getter method - get the name of the Collection Location
    public String getColLocationName() {
        return colLocationName;
    }

     // Getter method -Get the Collection Location Address
    public String getColLocationAddr() {
        return colLocationAddr;
    }

     // Get the Collection Location Phone Number
    public String getColLocationPhone() {
        return colLocationPhone;
    }
}
