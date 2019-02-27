package hkust.comp4521.courseinfo;

import android.app.Activity;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.IOException;
import android.text.util.Linkify;

public class contacts extends Activity {
	
	// Add a new string
    public static final String DEBUG_TAG = "Contacts Log";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        
        //Get pointers to the two table layouts in the contacts.xml file
        TableLayout instructorTable = (TableLayout) findViewById(R.id.TableLayout_Instructor);
        TableLayout taTable = (TableLayout) findViewById(R.id.TableLayout_TA);
        
        // Open a XML resource parser to parse the contactinfo.xml file
        XmlResourceParser contactinfo = getResources().getXml(R.xml.contactinfo);
        
        // Now construct the information for the instructor and TA from the parsed XML file
        try {
        	// process the contacts xml file to set up the information on the activity screen
            processcontacts(instructorTable, taTable, contactinfo);
        } catch (Exception e) {
            Log.e(DEBUG_TAG, "Failed to load Contacts", e);
        }
    }


    /**
     * Churn through an XML score information and populate a {@code TableLayout}
     * 
     * @param instructorTable
     *            The {@code TableLayout} to populate
     * @param taTable
     *            The {@code TableLayout} to populate
     *
     * @param contact
     *            A standard {@code XmlResourceParser} containing the scores
     * @throws XmlPullParserException
     *             Thrown on XML errors
     * @throws IOException
     *             Thrown on IO errors reading the XML
     */
    private void processcontacts(final TableLayout instructorTable,
    		final TableLayout taTable,
    		XmlResourceParser contact) throws XmlPullParserException,
            IOException {
        int eventType = -1;
        boolean bFoundContacts = false;
        // Find records from XML
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                // Get the name of the tag (eg contact, instructor or assistant)
                String strName = contact.getName();
                if (strName.equals("instructor")) {
                    bFoundContacts = true;
                    String name = contact.getAttributeValue(null, "name");
                    String office = contact.getAttributeValue(null, "office");
                    String tel = contact.getAttributeValue(null, "tel");
                    String email = contact.getAttributeValue(null, "email");
                    String web = contact.getAttributeValue(null, "web");
                    insertContactRow(instructorTable, "     " + name, -1);
                    insertContactRow(instructorTable, "     Office: " + office, -1);
                    insertContactRow(instructorTable, "     Tel: " + tel, Linkify.PHONE_NUMBERS);
                    insertContactRow(instructorTable, "     Email: " + email, Linkify.EMAIL_ADDRESSES);
                    insertContactRow(instructorTable, "     Web: " + web, Linkify.WEB_URLS);
                    insertContactRow(instructorTable, "     ", -1);

                }
                if (strName.equals("assistant")) {
                    bFoundContacts = true;
                    String name = contact.getAttributeValue(null, "name");
                    String office = contact.getAttributeValue(null, "office");
                    String tel = contact.getAttributeValue(null, "tel");
                    String email = contact.getAttributeValue(null, "email");
                    insertContactRow(taTable, "     " + name, -1);
                    insertContactRow(taTable, "     Office: " + office, -1);
                    insertContactRow(taTable, "     Tel: " + tel, Linkify.PHONE_NUMBERS);
                    insertContactRow(taTable, "     Email: " + email, Linkify.EMAIL_ADDRESSES);
                    insertContactRow(taTable, "     ", -1);
                }
            }
            eventType = contact.next();
        }
        // Handle no records available
        if (bFoundContacts == false) {
            final TableRow newRow = new TableRow(this);
            TextView noResults = new TextView(this);
            newRow.addView(noResults);
            instructorTable.addView(newRow);
        }
    }

    /**
     * {@code insertContactRow()} helper method -- Inserts a new contact information row {@code
     * TableRow} in the {@code TableLayout}
     * 
     * @param contactTable
     *            The {@code TableLayout} to add the contact information to
     * @param strValue
     *            The value of text string to be inserted into the row
     * @param mask
     *            specifies what regex I need to look for in the string in order to Linkify it. mask <= 0 implies no need to Linkify.
     */
    private void insertContactRow(final TableLayout contactTable, String strValue, int mask) {
    	// create a new table row and populate it
        final TableRow newRow = new TableRow(this);
        TextView textView = new TextView(this);
        textView.setText(strValue);
        if (mask > 0)
           Linkify.addLinks(textView, mask);
        newRow.addView(textView);
        contactTable.addView(newRow);
    }
}
