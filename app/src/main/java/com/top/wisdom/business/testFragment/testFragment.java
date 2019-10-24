package com.top.wisdom.business.testFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.top.wisdom.R;
import com.top.wisdom.business.main.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class testFragment extends Fragment {

    private static final String TAG ="testFragment";


    private  MainActivity activity;

    public static testFragment newInstance() {

        Bundle args = new Bundle();

        testFragment fragment = new testFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"-------------onAttach-------------");
        activity= (MainActivity) context;
        activity.closeDrawerLayout();
        activity.setBack(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"-------------onCreate-------------");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"-------------onCreateView-------------");

        View rootView=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test,null);
        activity= (MainActivity) getActivity();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"-------------onActivityCreated-------------");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"-------------onStart-------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"-------------onResume-------------");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"-------------onPause-------------");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"-------------onStop-------------");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"-------------onDestroyView-------------");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"-------------onDestroy-------------");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"-------------onDetach-------------");
        activity.unLockDrawerLayout();

    }
}
