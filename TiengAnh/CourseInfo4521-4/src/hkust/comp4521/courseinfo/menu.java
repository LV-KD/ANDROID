package hkust.comp4521.courseinfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class menu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        // Get the reference to the ListView item in the main.xml layout
        ListView menuList = (ListView) findViewById(R.id.ListView_menu);
        
        // Create an array of strings and populate it with the items from the string array declared in strings.xml
        String[] items = getResources().getStringArray(R.array.menu_items);
        
        // Create an array adapter to create the menu with the list items being laid out as per list_item.xml
        // and the item names from the items[] array created above
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.list_item, items);
        menuList.setAdapter(adapt);        

        // make the menu items actionable by declaring an onclick listener for each of them
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {
                
            	TextView textview = (TextView) itemClicked;
                String strText = textview.getText().toString();
                
                switch(position){
                    case 0:
        				startActivity(new Intent(menu.this,contacts.class));
            	    break;
            	    
                    case 1:
        				startActivity(new Intent(menu.this,time_table.class));
            	    break;

                    case 2:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Description.html")));
                    break;
                    
                    case 3:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Syllabus.html")));
                    break;
                    
                    case 4:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Lectures.html")));
                    break;

                    case 5:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Labs.html")));
                    break;
                    case 6:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Exams.html")));
                    break;
                    case 7:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Project.html")));
                    break;
                    case 8:
                    	startActivity(new Intent(Intent.ACTION_VIEW,
                    			Uri.parse("http://course.cse.ust.hk/comp4521/Links.html")));
                    break;
            	                	                	                	                	                	                	    
            	    default:
                        // When clicked, show a toast with the TextView text
                        Toast.makeText(getApplicationContext(), strText,
                            Toast.LENGTH_SHORT).show();
            	    break;
            	    
                }
            }
        });
    }
}