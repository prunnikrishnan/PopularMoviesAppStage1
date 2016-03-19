package com.utility;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by ADMIN on 09-Mar-16.
 */
public class ConnectionDetector {

    private Context _context;

    public ConnectionDetector(Context context){
        this._context = context;
    }

    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
           /* Network activeNetwork = connectivity.getActiveNetwork();
            NetworkInfo info = connectivity.getNetworkInfo(activeNetwork);
            if (info != null)

                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
*/
        }
        return false;
    }
}