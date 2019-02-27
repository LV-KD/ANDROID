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

public class time_table extends Activity {
	
    public static final String DEBUG_TAG = "Time Table Log";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        
        //Get pointers to the two table layouts in the contacts.xml file
        TableLayout lectureTable = (TableLayout) findViewById(R.id.TableLayout_Lecture);
        TableLayout labTable = (TableLayout) findViewById(R.id.TableLayout_Lab);

        // Open a XML resource parser to parse the contactinfo.xml file
        XmlResourceParser timetableinfo = getResources().getXml(R.xml.time_table);
        try {
            processtimetable(lectureTable, labTable, timetableinfo);
        } catch (Exception e) {
            Log.e(DEBUG_TAG, "Failed to load Time Table", e);
        }
    }


    /**
     * Churn through an XML score information and populate a {@code TableLayout}
     * 
     * @param lectureTable
     *            The {@code TableLayout} to populate
     * @param labTable
     *            The {@code TableLayout} to populate
     * @param timetable
     *            A standard {@code XmlResourceParser} containing the scores
     * @throws XmlPullParserException
     *             Thrown on XML errors
     * @throws IOException
     *             Thrown on IO errors reading the XML
     */
    private void processtimetable(final TableLayout lectureTable,
    		final TableLayout labTable,
    		XmlResourceParser timetable) throws XmlPullParserException,
            IOException {
        int eventType = -1;
        boolean bFoundTimeTable = false;
        // Find records from XML
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            if (eventType == XmlResourceParser.START_TAG) {
                // Get the name of the tag (eg timetable, lecture or lab)
                String strName = timetable.getName();
                if (strName.equals("lecture")) {
                    bFoundTimeTable = true;
                    String days = timetable.getAttributeValue(null, "days");
                    String room = timetable.getAttributeValue(null, "room");
                    String time = timetable.getAttributeValue(null, "time");
                    insertTimeTableRow(lectureTable, "     " + days);
                    insertTimeTableRow(lectureTable, "     Time: " + time);
                    insertTimeTableRow(lectureTable, "     Room: " + room);
                    insertTimeTableRow(lectureTable, "     ");

                }
                if (strName.equals("lab")) {
                    bFoundTimeTable = true;
                    String days = timetable.getAttributeValue(null, "days");
                    String room = timetable.getAttributeValue(null, "room");
                    String time = timetable.getAttributeValue(null, "time");
                    insertTimeTableRow(labTable, "     " + days);
                    insertTimeTableRow(labTable, "     Time: " + time);
                    insertTimeTableRow(labTable, "     Room: " + room);
                    insertTimeTableRow(labTable, "     ");
                }
            }
            eventType = timetable.next();
        }
        // Handle no records available
        if (bFoundTimeTable == false) {
            final TableRow newRow = new TableRow(this);
            TextView noResults = new TextView(this);
            newRow.addView(noResults);
            lectureTable.addView(newRow);
        }
    }

    /**
     * {@code insertTimeTableRow()} helper method -- Inserts a new time table row {@code
     * TableRow} in the {@code TableLayout}
     * 
     * @param timeTable
     *            The {@code TableLayout} to add the time table information to
     * @param strValue
     *            The value of the  text string to be inserted into the row
     */
    private void insertTimeTableRow(final TableLayout timeTable, String strValue) {
        final TableRow newRow = new TableRow(this);
        TextView textView = new TextView(this);
        textView.setText(strValue);
        newRow.addView(textView);
        timeTable.addView(newRow);
    }
}
