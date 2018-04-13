package comjasmynstansfield.httpsgithub.contactbook;

/**
 * Created by j.stansfield on 4/5/2018.
 */

/**
 *
 */
public class Contact {

    private String inputName = "";
    private String inputPhoneNum = "";
    private String inputEmail = "";

    /**
     * Contact is the default constructor that will get the contact information
     *
     * @param name is the name of the contact the user inputs
     * @param phoneNum is the phone number of the contact the user inputs
     * @param email is the email of the contact the user inputs
     */
    public Contact (String name, String phoneNum, String email)
    {
        inputName = name;
        inputPhoneNum = phoneNum;
        inputEmail = email;
    }

    /**
     * getName will get the name of the contact added
     *
     * @param "" There are no parameters
     * @return a string that is the contact's name
     */
    public String getName()
    {
        return inputName;
    }

    /**
     * getPhone will get the phone number of the contact added
     *
     * @param "" There are no parameters
     * @return a string that is the contact's phone number
     */
    public String getPhone()
    {
        return inputPhoneNum;
    }

    /**
     * getEmail will get the email of the contact added
     *
     * @param "" There are no parameters
     * @return a string that is the contact's email
     */
    public String getEmail()
    {
        return inputEmail;
    }
}

