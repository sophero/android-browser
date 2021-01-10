package com.dizach.androidbrowser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryListFragment extends DialogFragment {

    private ListView listView;
    private ArrayList<String> history = new ArrayList<String>();

    public HistoryListFragment(ArrayList<String> history) {
        this.history = history;
        Collections.reverse(this.history); // save history in reverse order
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.history_list);
//        listView.setLongClickable(true); // enable long click listeners

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, history);
        listView.setAdapter(arrayAdapter);

        // set click listener to load bookmarked page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("history", "onItemClick: " + history.get(position));
                // close history dialog and load clicked url
//                webView.loadUrl(history.get(position));
                ((MainActivity)getActivity()).goToURL(history.get(position));
                dismiss();
            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("history", "onItemLongClick: " + history.get(position));
//                ((MainActivity)getActivity()).removeFromhistory(history.get(position));
//                // reload history list
//                dismiss();
//                ((MainActivity)getActivity()).displayhistory();
//                return true;
//            }
//        });
    }


}