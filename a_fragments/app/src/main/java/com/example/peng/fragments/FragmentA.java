package com.example.peng.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peng on 11/2/15.
 */
public class FragmentA extends Fragment {
    private static final String TAG = "FragmentA";

    @Override
    public void onAttach(Context context) {
        // This is called once the fragment is associated with its activity.
        super.onAttach(context);
        Log.i(TAG, " onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // This is called after the onAttach(), doing the initial creation of the fragment.
        super.onCreate(savedInstanceState);
        Log.i(TAG, " onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //This is called after the onCreate(), this is where you will initialize all the views in this fragment.
        Log.i(TAG, " onCreateView()");
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //This is called after onCreateView(), this is where you can access the resource from the activity of this fragment.
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, " onActivityCreated()");
    }

    @Override
    public void onStart() {
        //This is called after onActivityCreated(), it makes the fragment visible to the user (based on its containing activity being started).
        super.onStart();
        Log.i(TAG, " onStart()");
    }

    @Override
    public void onResume() {
        //This is called after onStart()
        super.onResume();
        Log.i(TAG, " onResume()");
    }

    @Override
    public void onPause() {
        //This is called when the fragment is being moved out from the screen
        super.onPause();
        Log.i(TAG, " onPause()");
    }

    @Override
    public void onStop() {
        //This is called when the fragment is no longer visible to the user either because its activity is being stopped or a fragment operation is modifying it in the activity.
        super.onStop();
        Log.i(TAG, " onStop.");
    }

    @Override
    public void onDestroyView() {
        //This is where you will clean up resources of this fragment, background thread, handlers, etc.
        super.onDestroyView();
        Log.i(TAG, " onDestroyView.");
        onDestroy();

    }

    @Override
    public void onDestroy() {
        //This is called to do final cleanup of the fragment's state.
        super.onDestroy();
        Log.i(TAG, " onDestroy.");
    }

    @Override
    public void onDetach() {
        //This is called immediately prior to the fragment no longer being associated with its activity.
        super.onDetach();
        Log.i(TAG, " onDetach()");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        //This is called after onActivityCreated(), this is where you will retrieve the data that's stored from the onSaveInstanceState
        super.onViewStateRestored(savedInstanceState);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : "null";
        Log.i(TAG, " onViewStateRestored: " + greeting);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //This is called after onPause(), this is where you will store the data that you want to keep when the uer come back to this fragment
        super.onSaveInstanceState(outState);
        Log.i(TAG, " onSaveInstanceState.");
        outState.putString("greeting", "Hello");
    }
}
