/**
 * Name: Jasmyn Stansfield
 * Course: CS30S
 * Teacher: Mr. Hardman
 * Lab #3, Program #1
 * Date last modified: April 13, 2018
 */

package comjasmynstansfield.httpsgithub.contactbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Contact[] contactsArray;
    private static int numContactsAdded;

    private EditText mNameInput;
    private EditText mPhoneInput;
    private EditText mEmailInput;

    private TextView mErrorMessage;
    private TextView mSortedList;

    @Override
    /**
     * onCreate is the method that is run when we create an instance of thi activity
     *
     * @param savedInstanceState is a bundle object that allows the activity to
     *                           restore itself to a previously saved instance
     * @return Nothing will be returned
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameInput = (EditText) findViewById(R.id.et_contact_name);
        mPhoneInput = (EditText) findViewById(R.id.et_phone_num);
        mEmailInput = (EditText) findViewById(R.id.et_email_address);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mSortedList = (TextView) findViewById(R.id.tv_sorted_lists);

       contactsArray = new Contact[50];

       numContactsAdded = 0;

    }

    /**
     * addContact is the method that will add contacts to the array that we will sort
     *
     * @param vw is the view related to this method
     * @return Nothing will be returned
     */
    public void addContact ( View vw )
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        Contact toAdd;

        if( mNameInput.getText().length() == 0)
        {
            mErrorMessage.setText("You must at least eneter a name to add a contact.");
        }
        else if ( numContactsAdded >= contactsArray.length )
        {
            mErrorMessage.setText("You have reached the maximum amount of contacts allowed in the contact book. Please click Sort Contacts");
        }
        else
        {
            toAdd = new Contact ( mNameInput.getText().toString(), mPhoneInput.getText().toString(), mEmailInput.getText().toString() );
            contactsArray[numContactsAdded] = toAdd;

            numContactsAdded ++;

            mNameInput.setText("");
            mPhoneInput.setText("");
            mEmailInput.setText("");

            mErrorMessage.setText("Contact Successfully added. Please enter another contact.");
            inputManager.hideSoftInputFromWindow(mNameInput.getApplicationWindowToken(), 0 );
        }
    }

    /**
     * sortContact is the method that will display the sorted the list of contacts using String.format
     *
     * @param vw is the view related to this method
     * @return Nothing will be returned
     */
    public void sortContact ( View vw )
    {
        String sortedList = "";

        insertionSort();
        selectionSort();
        quickSort(contactsArray, 0, numContactsAdded - 1);

        for( int i = 0; i < numContactsAdded; i ++)
        {
            if( contactsArray[i] != null )
            {
                sortedList += String.format("Name: %20s\nPhone: %20s\nEmail: %20s\n\n", contactsArray[i].getName(), contactsArray[i].getPhone(), contactsArray[i].getEmail() );
            }
        }

        mErrorMessage.setText("");
        mSortedList.setText( sortedList );
    }

    /**
     * insertionSort uses the Insertion Sort algorithm to sort a list of items in
     * ascending order
     *
     * @param "" There are no parameters
     * @return Nothing will be returned
     */
    private void insertionSort()
    {
        Contact key;

        int index;

        for( int j = 1; j < numContactsAdded; j ++)
        {
            key = contactsArray[j];
            index = j-1;

            while( index >= 0 && (contactsArray[index].getName()).compareTo( key.getName() ) > 0)
            {
                contactsArray[index + 1] = contactsArray[index];
                index = index - 1;
            }

            contactsArray[index + 1] = key;

        }
    }

    /**
     *selectionSort uses the Selection Sort algorithm to sort the list of items in ascending order
     *
     * @param "" There are no parameters
     * @return Nothing will be returned
     */
    private void selectionSort()
    {
        int minIndex;
        Contact toSwap;


        for( int j = 0; j < numContactsAdded - 1; j ++ )
        {
            minIndex = j;

            for( int k = j + 1; k < numContactsAdded; k ++)
            {
                if( contactsArray[k].getName().compareTo(contactsArray[minIndex].getName()) < 0)
                {
                    minIndex = k;
                }
            }

            toSwap = contactsArray[minIndex];
            contactsArray[minIndex] = contactsArray[j];
            contactsArray[j] = toSwap;
        }
    }

    /**
     * quickSort uses the Quick Sort algorithm to sort a list of items in ascending order
     *
     * @param array is the array we are sorting
     * @param low is the beginning index of the section of the array we would like to sort
     * @param high is the ending of index of the section of the array we would like to sort
     * @return Nothing will be returned
     */

    private void quickSort( Contact[] array, int low, int high)
    {
        int middle;
        Contact pivot;

        int i;
        int j;

        Contact toSwap;

        if( low < high)
        {
            middle = low + (high - low) / 2;
            pivot = array[middle];
            i = low;
            j = high;

            while( i <= j )
            {
                while( array[i].getName().compareTo(pivot.getName()) < 0 )
                {
                    i++;
                }

                while (array[j].getName().compareTo(pivot.getName()) > 0 )
                {
                    j--;
                }

                if ( i <= j )
                {
                    toSwap = array[i];
                    array[i] = array[j];
                    array[j] = toSwap;

                    i++;
                    j--;
                }
            }

            if( low < j)
            {
                quickSort( array, low, j );
            }

            if ( high > i )
            {
                quickSort( array, i, high );
            }
        }
    }
}


