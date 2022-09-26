package com.example.asapd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuSearchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerStoreAdapter mRecyclerAdapter;
    private ArrayList<StoreData> mList;

    private SharedPreferences preferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuSearchFragment newInstance(String param1, String param2) {
        MainMenuSearchFragment fragment = new MainMenuSearchFragment();
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
        mList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_search, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        sendGetStore();
        return rootView;
    }

    private void sendGetStore(){
        RetrofitClient.getApiService().getStores(preferences.getString("TOKEN", "NULL")).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                StoreResponse result = response.body();
                if(result.getStatus() == 200){
                    Log.d("TAG", result.getMessage());
                    ArrayList<LinkedTreeMap<String, Object>> orderList = (ArrayList<LinkedTreeMap<String, Object>>) result.getData().get("content");

                    double storeId, owner;
                    String name, address;
                    for (LinkedTreeMap<String, Object> o : orderList) {
                        storeId = (double) o.get("storeId");
                        name = o.get("name").toString();
                        owner = (double) o.get("owner");
                        address = o.get("address").toString();

                        mList.add(new StoreData(storeId, name, owner, address));
                    }
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerAdapter = new RecyclerStoreAdapter(getActivity(), mList);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(mRecyclerAdapter);

                    mRecyclerAdapter.setOnItemClickListener(new RecyclerStoreAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClicked(View v, int pos) { //
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            StoreItemFragment storeItemFragment = new StoreItemFragment();
                            transaction.replace(R.id.menu_frame_layout, storeItemFragment);
                            transaction.commit();
                        }
                    });

                    Log.d("TAG", mList.get(0).storeId+"");
                    Log.d("TAG", mList.get(0).name);
                    Log.d("TAG", mList.get(0).owner+"");
                    Log.d("TAG", mList.get(0).address);
                    Log.d("TAG", "Sizeof mList(Inside) : " + mList.size());
                }
                else { Log.d("TAG", result.getMessage()); }

            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) { }
        });
    }
}