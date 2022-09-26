package com.example.asapd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuBasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuBasketFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerItemAdapter mRecyclerAdapter;
    private ArrayList<ItemData> mList;
    private Button btn_order;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuBasketFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuOrderlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuBasketFragment newInstance(String param1, String param2) {
        MainMenuBasketFragment fragment = new MainMenuBasketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_basket, container, false);

        btn_order = rootView.findViewById(R.id.btn_order);

        mList = getStringArrayPref_item(getContext(), "BASKET");
        Log.d("TAG", "in Basket, mList.size() : " + mList.size());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerAdapter = new RecyclerItemAdapter(getActivity(),mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        btn_order.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                OrderFragment orderFragment = new OrderFragment();
                transaction.replace(R.id.menu_frame_layout, orderFragment);
                transaction.commit();
            }
        });
        return rootView;

    }

    public ArrayList<ItemData> getStringArrayPref_item(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<ItemData> ItemDatas = new ArrayList<ItemData>();
        Gson gson =new GsonBuilder().create();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    ItemData itemData = gson.fromJson( a.get(i).toString() , ItemData.class);
                    ItemDatas.add(itemData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ItemDatas;
    }
}