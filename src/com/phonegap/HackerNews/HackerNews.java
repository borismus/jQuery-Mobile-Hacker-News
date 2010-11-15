
package com.phonegap.HackerNews;

import android.os.Bundle;
import com.phonegap.*;

    public class HackerNews extends DroidGap
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            super.loadUrl("file:///android_asset/www/index.html");
        }
    }
    
