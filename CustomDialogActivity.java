package com.samples.customdialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;


public class CustomDialogActivity extends FragmentActivity implements OnClickListener  {
	
    public static final MessageBoxClass messagebox = new MessageBoxClass();
    public final FragmentManager fManager = getSupportFragmentManager();
    EditText MessageTitle, MessageText;
    int buttonType, pictureType;
    String[] buttons = {
    		"Cancel, Try Again, Continue",
    		"Cancel, Continue",
    		"Cancel"
    		};
    String[] pictures = {
    		"Warning",
    		"Help",
    		"Fail",
    		"Info"
    		};
    int[] pics = {
    		R.drawable.warn_pic,
    		R.drawable.help_pic,
    		R.drawable.fail_pic,
    		R.drawable.info_pic
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button callButton = (Button)findViewById(R.id.button);
        callButton.setOnClickListener(this);
        
        MessageTitle = (EditText) findViewById(R.id.editText1);
        MessageText = (EditText) findViewById(R.id.editText2);
        
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        spin1.setOnItemSelectedListener(new OnItemSelectedListener(){
        	public void onItemSelected(
                    AdapterView<?> parent, View v, int position, long id) {
        		buttonType = position;
        		
        	}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				buttonType = 0;
				
			}
        });
        spin2.setOnItemSelectedListener(new OnItemSelectedListener(){
        	public void onItemSelected(
                    AdapterView<?> parent, View v, int position, long id) {
        		pictureType = position;
        		
        	}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				pictureType = 0;
				
			}
        });
        
        ArrayAdapter<String> arrayAdapter1; 
        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
        		buttons);
        
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(arrayAdapter1);
        
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", "Picture : " + pictures[i]);
            hm.put("pic", Integer.toString(pics[i]));
            aList.add(hm);
          }
          String[] from = { "pic", "txt"};
          int[] to = { R.id.pic, R.id.txt,};

          SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,
              R.layout.listview_layout, from, to);
          spin2.setAdapter(adapter);
        
        
    }


	@Override
	public void onClick(View v) {
		messagebox.setDialog(
    			buttonType, 
    			MessageTitle.getText().toString(), 
    			MessageText.getText().toString(), 
    			pictureType);
		messagebox.show(fManager, "MB");
	}

    
}