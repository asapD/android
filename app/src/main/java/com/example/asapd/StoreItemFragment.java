package com.example.asapd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreItemFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerItemAdapter mRecyclerAdapter;
    private ArrayList<ItemData> mList;

    private SharedPreferences preferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreItemFragment(){
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
    public static StoreItemFragment newInstance(String param1, String param2) {
        StoreItemFragment fragment = new StoreItemFragment();
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_store_itemlist, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        sendGetItem();

        TextView store_name = rootView.findViewById(R.id.store_name);
        if(getArguments() != null)
        {
            String name = getArguments().getString("title");
            store_name.setText(name);
        }
        return rootView;

    }

    private void sendGetItem(){
        RetrofitClient.getApiService().getItems(preferences.getString("TOKEN", "NULL")).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse result = response.body();
                if(result.getStatus() == 200){
                    Log.d("TAG", result.getMessage());
                    ArrayList<LinkedTreeMap<String, Object>> orderList = (ArrayList<LinkedTreeMap<String, Object>>) result.getData().get("content");

                    double itemId, price, storeId;
                    String name, description;
                    for (LinkedTreeMap<String, Object> o: orderList) {
                        itemId = (double)o.get("itemId");
                        price = (double)o.get("price");
                        storeId = (double)o.get("storeId");
                        name = o.get("name").toString();
                        description = o.get("description").toString();

                        mList.add(new ItemData(itemId, name, description, price, storeId));
                    }
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerAdapter = new RecyclerItemAdapter(getActivity(), mList);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(mRecyclerAdapter);

                    mRecyclerAdapter.setOnItemClickListener(new RecyclerItemAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClicked(View v, int pos) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("ITEMINFO", mList.get(pos));
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            ItemInfoFragment itemInfoFragment = new ItemInfoFragment();
                            itemInfoFragment.setArguments(bundle);
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.menu_frame_layout, itemInfoFragment);
                            transaction.commit();
                        }
                    });
                    Log.d("TAG", mList.get(0).itemId+"");
                    Log.d("TAG", mList.get(0).price+"");
                    Log.d("TAG", mList.get(0).name);
                    Log.d("TAG", mList.get(0).description);
                    Log.d("TAG", mList.get(0).storeId+"");
                    Log.d("TAG", "Sizeof mList(Item) : " + mList.size());

                }
                else { Log.d("TAG", result.getMessage()); }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

            }
        });
    }
}