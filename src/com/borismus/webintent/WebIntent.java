package com.borismus.webintent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

/**
 * WebIntent is a PhoneGap plugin that bridges Android intents and web applications:
 *  
 * 1. web apps can spawn intents that call native Android applications. 
 * 2. (after setting up correct intent filters for PhoneGap applications), Android
 * 	  intents can be handled by PhoneGap web applications.
 * 
 * @author boris@borismus.com
 *
 */
public class WebIntent extends Plugin {

	/**
	 * Executes the request and returns PluginResult.
	 * 
	 * @param action 		The action to execute.
	 * @param args 			JSONArray of arguments for the plugin.
	 * @param callbackId	The callback id used when calling back into JavaScript.
	 * @return 				A PluginResult object with a status and message.
	 */
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		try {
			if (action.equals("startActivity")) {
				if(args.length() != 1) {
					return new PluginResult(PluginResult.Status.INVALID_ACTION);
				}
				// Parse the arguments
				
				JSONObject obj = args.getJSONObject(0);
				startActivity(obj.getString("action"));
				return new PluginResult(PluginResult.Status.OK);
				
			} else if (action.equals("hasExtra")) {
				if (args.length() != 1) {
					return new PluginResult(PluginResult.Status.INVALID_ACTION);
				}
				Intent i = this.ctx.getIntent();
				String extraName = args.getString(0);
				return new PluginResult(PluginResult.Status.OK, i.hasExtra(extraName));
				
			} else if (action.equals("getExtra")) {
				if (args.length() != 1) {
					return new PluginResult(PluginResult.Status.INVALID_ACTION);
				}
				Intent i = this.ctx.getIntent();
				String extraName = args.getString(0);
				if (i.hasExtra(extraName)) {
					return new PluginResult(PluginResult.Status.OK, i.getStringExtra(extraName));
				} else {
					return new PluginResult(PluginResult.Status.ERROR);
				}
			}
			return new PluginResult(PluginResult.Status.INVALID_ACTION);
		} catch (JSONException e) {
			e.printStackTrace();
			return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
		}
	}
	
	void startActivity(String action) {
		// TODO: do this right
		
		Intent i = new Intent(Intent.ACTION_SEND);  
		//i.setType("text/plain"); //use this line for testing in the emulator  
		i.setType("message/rfc822") ; // use from live device
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"});  
		i.putExtra(Intent.EXTRA_SUBJECT,"subject goes here");  
		i.putExtra(Intent.EXTRA_TEXT,"body goes here");  
		this.ctx.startActivity(i);
	}
}
