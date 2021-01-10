package com.dizach.androidbrowser;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class ListFragment extends DialogFragment {

    private ListView listView;
    private WebView webView;
    private ArrayList<String> bookmarks = new ArrayList<String>();

    public ListFragment(ArrayList<String> bookmarks, WebView webView) {
        this.bookmarks = bookmarks;
        this.webView = webView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.bookmarks_list);
        listView.setLongClickable(true); // enable long click listeners

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, bookmarks);
        listView.setAdapter(arrayAdapter);

        // set click listener to load bookmarked page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("BOOKMARKS", "onItemClick: " + bookmarks.get(position));
                // close bookmarks dialog and load clicked url
                webView.loadUrl(bookmarks.get(position));
                dismiss();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("BOOKMARKS", "onItemLongClick: " + bookmarks.get(position));
                ((MainActivity)getActivity()).removeFromBookmarks(bookmarks.get(position));
                // reload bookmarks list
                dismiss();
                ((MainActivity)getActivity()).displayBookmarks();
                return true;
            }
        });
    }


}