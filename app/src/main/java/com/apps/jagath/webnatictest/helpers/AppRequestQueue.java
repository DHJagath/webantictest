package com.apps.jagath.webnatictest.helpers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by Jagath on 9/28/17.
 */

public class AppRequestQueue {
    private static AppRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private AppRequestQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized AppRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * This method is to execute all volley requests
     *
     * @param ctx      : the context of the application from the activity invoking the call
     * @param method
     * @param url
     * @param params
     * @param listener
     */
    public static void processRequest(final Context ctx, int method, String url, final Map<String, String> params,
                                      final VolleyResponseListener listener) {

        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onVolleyResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMsg = null;
                        if (error instanceof NoConnectionError) {
                            errorMsg = "No internet Access, Check your internet connection.";
                        } else if (error instanceof TimeoutError) {
                            errorMsg = "Your request has timed out.Please try again";
                        } else if (error instanceof ServerError) {
                            errorMsg = "Something Went Wrong, Please Try Again";
                        } else {
                            errorMsg = error.getMessage();
                        }

                        if (errorMsg != null) {
                            Toast.makeText(ctx, errorMsg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(12000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        AppRequestQueue.getInstance(ctx).addToRequestQueue(stringRequest);
    }

}
