package com.example.homer.rex;

/*             Matthew Brennan SDA Assignment-4  Tab3_OrdersFragment Feb 2019

This Fragment offers the user the facilities to input their Name and Address to which to be sent a T-shirt,
either selected from the products list or customised by themselves by taking a photo with their phone camera,
he activity then e-mails the details to the T shirt company. A spinner is available to allow the customer
to select the number of days in which they wish eto receive the orders.

 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import static android.app.Activity.RESULT_OK;
import static com.example.homer.rex.R.id.button;

//Adapted from code written by Colette Kirwan. DCU Open Education

/** The Tab3_OrdersFragment class provides the java functionality code for the tab3_order.xml code
 * and extends the class AppCompatActivity.
  */
public class Tab3_OrdersFragment extends Fragment
{
   private static final String TAG = "Tab3_OrdersFragment";
    // The following variables are initialised
    Uri mPhotoURI;
    Spinner mSpinner;
    EditText mCustomerName;
    EditText mAddress;
    EditText meditOptional;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    private Button  btnSend;

    /**The onCreateView method setups the Fragment and sets the values of some of the variables
     //initialised at the start. It also setup for receiving input data.
     //Here also an adapter inflates the spinner_days xml layout file with the arraylist
     // ui_time_entries stored in the strings file. The adapter is then used to populate the
     // elements of the spinner on the activity_order screen.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
          final View rootView = inflater.inflate(R.layout.tab3_orders, container,false);

          //Sets setOnClickListener on the ImageView on the Layout
         ImageView thePic = (ImageView) rootView.findViewById(R.id.imageView2);
         thePic.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                // Code here executes on main thread after user presses button
                dispatchTakePictureIntent();
              }
         });
        //Sets setOnClickListener on the Button (btnSend) on the Layout
         btnSend = (Button) rootView.findViewById(button);
         btnSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v){
               //If Clicked the sendEmail method is called.
               sendEmail();
             }
         });

         // Variable to Delivery Address EditText box on the tab3_orders layout and setup for user input
        meditOptional = (EditText) rootView.findViewById(R.id.editOptional);
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //initialise spinner using the integer array
        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mCustomerName = (EditText) rootView.findViewById(R.id.editCustomer);
        mAddress =(EditText) rootView.findViewById(R.id.editOptional);
        // Create an ArrayAdapter using the string array (ui_time_entries) and a spinner layout xml (spinner_days)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);

        return rootView;
    }

    /* @param v
    This method triggers the launch of the phone camera and stores the image in the variable mPhotoURI
    It provides a name using the text 'my_tshirt_image' and appending a timestamp to make it unique.
     */
    public void dispatchTakePictureIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "my_tshirt_image_" + timeStamp + ".bmp";
        Log.i(TAG, "imagefile"); // Logcat Info
        //File file = new File(Environment.getExternalStorageDirectory(), imageFileName);
        File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageFileName);

    //Setting URI of image to mPhotoURI
        mPhotoURI = Uri.fromFile(file);
        Log.i(TAG, mPhotoURI.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        //incase of caching if it comes from the activity stack, just a precaution
        intent.removeExtra(MediaStore.EXTRA_OUTPUT);
    }
   /**
     * When the image is saved and the user returns an OK that they are happy with the image
     * this method generates both a Toast which indicate the image was taken and saved successfully.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // To the give user a message that everything went ok using a Toast
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            CharSequence text = getString(R.string.image_taken);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();

        //The following displays then photo taken by the camera for the T-Shirt image in the ImageView on the ORDERS Tab.
        final ImageView imageViewx = (ImageView) getView().findViewById(R.id.imageView2);
             try{
                Bitmap mBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),mPhotoURI);
                imageViewx.setImageBitmap(Bitmap.createScaledBitmap(mBitmap,1000,700,false));
                }
                catch (IOException e){
                e.printStackTrace();
                }
           Log.i(TAG, "Image TAKEN and Displayed in ImageView");
        }
    }

    /**
     * Returns the Email Body Message.
     * <p> Email body message is created used prescription related data inputed from user </p>
     * The message is composed of user input and text which is stored in strings.
     * @return Email Body Message
     */
    private String createOrderSummary()
    {
        /*
         *This method creates a  String 'orderMessage' which holds the Text that will form the body of the E-Mail text
         * it includes Customer name, and a list of the items selected by the user on the Products page,
          * and weather the order will be delivered or collected and the expected number of days for
          * receiving the order from the spinner
         */

        // Initialising this string to hold the product(s) selected by the user on the Products fragment
       String selectedProductsString="";
        // The following String holds the information to be displayed in the E-mail in relation to products selected by the user,
        // it is initialised here for the situation where there has been no items selected (NO ITEMS SELECTED!!)
       String itemsSelected = getString(R.string.no_items_selected);
       int itemselectedLength = 0; // This holds then length of the string of products selected by the user.

        // The following checks the SharedPreferences memory location "prod_list" to find if an item, or items, have been selected from the products Tab.
        SharedPreferences xSettings = getActivity().getSharedPreferences("prod_Lst", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = xSettings.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            // If there is an entry or entries, then they are saved to the selectedProductsString String.
            selectedProductsString = (selectedProductsString + entry.getValue().toString() + ", ");
            /*
            The following determines if products have been selected by the user on the Products page and displays
            a corresponding message to the  E-mail.
            Below sds holds the length of the of String selectedProductsString
            */
            itemselectedLength= selectedProductsString.length();
            /*
             *If 'itemselectedLength' variable is greater than 2 then there are basically items selected by the user and these are displayed on the E-mail along with
             *a last line as the 'itemsSelected' String '--THE CODERS T-SHIRTS---'
             *If 'itemselectedLength' variable is not greater than 2 then the 'if; statement below does not execute and the selectedProductsString String will be empty and
             *the 'itemsSelected' String' will display 'NO ITEMS SELECTED!!'
            */
             if(itemselectedLength > 2){
                 itemsSelected = getString(R.string.items_selected);
             }
        }

        // The following String holds the information to be displayed in the E-mail in relation to a collection point Address been input by the user,
        // it is initialised here  for the situation where there has been no Address -('NO ITEMS SELECTED!!)
        // The following checks the SharedPreferences memory location "Coll_location" to find if a collection location has been selected in the Collection Tab.
        SharedPreferences mSettings = getActivity().getSharedPreferences("Coll_location", Context.MODE_PRIVATE);
        // The collectionLoc String hold the collection location selected by the user in then Collection Tab.
        String collectionLoc = mSettings.getString("keyCL", getString(R.string.noAddress));

        // The following opens the SharedPreferences Editor and overwrites the memory at key 'keyCL' with "NO ADDRESS"
        // else the selscted location stays in memory and may give erronious information in future orders.
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("keyCL",getString(R.string.noAddress));
        editor.commit();

       // String customerAddress will hold the Address that will be input in the E-mail as an address to sent the order to.
        String customerAddress = mAddress.getText().toString();

        // If no Collection location is selected and also the user did not supply a delivery address, then a Toast will be displayed asking for a delivery Address.
        if(collectionLoc.equals(getString(R.string.noAddress)) && customerAddress.isEmpty())
            Toast.makeText(getContext(), R.string.missingAddress, Toast.LENGTH_LONG).show();
        /*
        *The String orderMessage below holds the Text that will form the body of the E-Mail text
        * it includes Customer name, and then on a new line text saying 'MyOrder:' etc
        * Then on another line it lists the items selected by the user on the Products page using variable (selectedProductsString)
        *The following line indicates how the order will be received either by delivery or collection (from address supplied or from (itemsSelected)
        * and then the expected number of days for receiving the order from the spinner
        */
        String orderMessage = getString(R.string.customer_name) + " " + mCustomerName.getText().toString();
        orderMessage += "\n" + "\n" + getString(R.string.order_message_1) + " \n \n " + selectedProductsString + "\n \n " + itemsSelected + "\n" ;
        String optionalInstructions = meditOptional.getText().toString();
        // The following clears all memory contents which held the selected items.
        editor = xSettings.edit();  // clear all
        editor.clear();
        editor.commit();

        /*
        *The following 'if' statement determines what form then last line of the orderMessage takes, depending on if an address is supplied or not.
        * If no Address is provided but a collection location is selected then the following will execute pertaining to a collection location.
        */
        if(customerAddress.isEmpty() )
            orderMessage += "\n" + getString(R.string.order_message_collect) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days in " + collectionLoc +"\n";
        else
        // If no Collection Location is selected, 'or' if both a Collection Location is selscted and an address in provided the following will execute
        // pertaining to an address been provided. An address been provided takes priority over a deliver location been selscted.
           orderMessage += "\n" + getString(R.string.order_message_deliver) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days to " + customerAddress+ "\n";
           orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();

        return orderMessage;
    }

    /**
     * This method does a check to ensure there is input in the Name field and if there is not
     * a Toast is displayed to say the field is empty.
     * If there is a name present then an intent is run which launches an e-mail app on the
     * phone and also information is passed to the app including name, subject, photo and then number
     * of days selected from the spinner within which you wish to recieve you order.
     *
     */
    public void sendEmail()
    {
        //check that Name Field is not empty, if it is then Toast user to input a Name.
        String customerName = mCustomerName.getText().toString();
           if (customerName.matches(""))
            Toast.makeText(getContext(), getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show();
           // If the Name is present then proceed to construct the E-mail
           // The following sends information to the e-mail application including Name, Mail Text for the body of the E-mail
           //  a photo if taken from the camera.
           else {
            Intent intent = new Intent(Intent.ACTION_SEND);
           //intent.setData(Uri.parse("mailto:"));
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email) });
            intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
              if (intent.resolveActivity(getActivity().getPackageManager()) != null)
                  startActivity(intent);
            }
    }
 }


