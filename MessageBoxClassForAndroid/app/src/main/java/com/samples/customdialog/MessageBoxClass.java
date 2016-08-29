package com.samples.customdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MessageBoxClass extends DialogFragment {

	String textfield, dialogtitle;
	int bType, pType;
	Context localContext;

	
	//button types
	public static final int CANCELTRYCONTINUE = 0;
	public static final int CANCELCONTINUE = 1;
	public static final int CANCEL = 2;
	
	//picture types
	public static final int WARNING = 0;
	public static final int HELP = 1;
	public static final int FAIL = 2;
	public static final int INFO = 3;
	
	public void OnCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	public void setDialog (int buttonType, String setTitle, String setText, int picType){
		if (buttonType < 3 || buttonType >= 0) {
			if(picType < 4 || picType >= 0) {
				if(setTitle.length() > 0) {
					if(setText.length() > 0) {
						textfield = setText;
						dialogtitle = setTitle;
						bType = buttonType;
						pType = picType;
						Log.d("Test", "MB_pType " + pType );
						Log.d("Test", "MB_bType " + bType );
						Log.d("Test", "MB_textfield " + textfield );
						Log.d("Test", "MB_dialogtitle " + dialogtitle );
					}
					else {Toast.makeText(localContext, "Wrong _setText_ Field!", Toast.LENGTH_SHORT).show();}
				}
				else {Toast.makeText(localContext, "Wrong _setTitle_ Field!", Toast.LENGTH_SHORT).show();}
			}
			else {Toast.makeText(localContext, "Wrong _picType_ Field!", Toast.LENGTH_SHORT).show();}
		}
		else {Toast.makeText(localContext, "Wrong _buttonType_ Field!", Toast.LENGTH_SHORT).show();}
	}
	
    
	@Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
		 
		Log.d("Test", "MB_Create run" );
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View layout = inflater.inflate(R.layout.custom_layout, null);
    	Log.d("Test", "MB_layout 0k" );
    	
    	TextView text = (TextView)layout.findViewById(R.id.text);
        text.setText(textfield); //"Are you sure you want to exit?"
        Log.d("Test", "MB_text 0k" );
        
        ImageView image = (ImageView) layout.findViewById(R.id.image);
        switch(pType){
        case WARNING: image.setImageResource(R.drawable.warn_pic); break;
        case HELP: image.setImageResource(R.drawable.help_pic); break;
        case FAIL: image.setImageResource(R.drawable.fail_pic); break;
        case INFO: image.setImageResource(R.drawable.info_pic); break;
        default: image.setImageResource(R.drawable.android3d); break;
        }
        Log.d("Test", "MB_picture 0k" );
        
        AlertDialog.Builder builder = new AlertDialog.Builder(layout.getContext());
        builder.setView(layout);
        builder.setMessage(dialogtitle); //"This is a custom dialog!"
        Log.d("Test", "MB_title 0k" );
        
        final OnClickListener ContinueButton = new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        final OnClickListener TryAgainButton = new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        final OnClickListener CancelButton = new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };
        
        switch (bType){
        case CANCELTRYCONTINUE: {
            builder.setPositiveButton("Continue", ContinueButton);
            builder.setNeutralButton("Try Again", TryAgainButton);
            builder.setNegativeButton("Cancel", CancelButton);
        } break;
        case CANCELCONTINUE: {
            builder.setPositiveButton("Continue", ContinueButton);
            builder.setNegativeButton("Cancel", CancelButton);
        } break;
        case CANCEL: {
        	builder.setNegativeButton("Cancel", CancelButton);
        } break;
        default: builder.setNegativeButton("Exit", CancelButton);
        }
        Log.d("Test", "MB_button 0k" );

        Dialog MB = builder.setCancelable(false)
        				   .create();
        return MB;
    }
}
