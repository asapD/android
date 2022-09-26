package com.example.asapd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ItemInfoFragment extends Fragment {

    private ArrayList<ItemData> mList;

    private int count = 0;

    private String name, description;
    private double itemId, price, storeId;
    private ItemData itemData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemInfoFragment() {
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
    public static ItemInfoFragment newInstance(String param1, String param2) {
        ItemInfoFragment fragment = new ItemInfoFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_item_info, container, false);

        if(getArguments() != null){
            itemData = (ItemData) getArguments().getSerializable("ITEMINFO");
        }


        Button btn_minus = rootView.findViewById(R.id.btn_minus);
        Button btn_plus = rootView.findViewById(R.id.btn_plus);
        TextView tv_count =  rootView.findViewById(R.id.tv_count);
        Button btn_basket = rootView.findViewById(R.id.btn_basket);

        tv_count.setText(count+"");

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0) {
                    count--;
                    tv_count.setText(count + "");
                }
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_count.setText(count+"");
            }
        });

        btn_basket.setOnClickListener(new View.OnClickListener(){ // 장바구니에 담기 버튼 클릭 시 로직
            @Override
            public void onClick(View v) {
                mList.add(new ItemData(itemData.itemId, itemData.name, itemData.description, itemData.price, itemData.storeId)); // <----- Fragment 전환 시 받은 아이템 데이터 넣기
                // SharedPreference 값에 추가
                // SharedPreference 에는 객체가 들어갈 수 없어서, gson 으로 변환 후 재변환해서 들고 옴.
                ArrayList<ItemData> tmp = getStringArrayPref_item(getContext(), "BASKET");
                if(tmp.size() == 0){
                    JSONArray arr = new JSONArray();
                    setStringArrayPref(getContext(), "BASKET", mList, arr);
                }
                else{
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String json = prefs.getString("BASKET", null);
                    try {
                        setStringArrayPref(getContext(), "BASKET", mList, new JSONArray(json));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ArrayList<ItemData> test = getStringArrayPref_item(getContext(), "BASKET");
                for (ItemData d : test) {
                    Log.d("TAG", "장바구니에 들어감 : " + d.name);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StoreItemFragment storeItemFragment = new StoreItemFragment();
                transaction.replace(R.id.menu_frame_layout, storeItemFragment);
                transaction.commit();
            }
        });
        return rootView;

    }

    public void setStringArrayPref(Context context, String key, ArrayList<ItemData> values, JSONArray arr) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new GsonBuilder().create();
        for (int i=0; i<values.size(); i++) {
            String str = gson.toJson(values.get(i), ItemData.class);
            arr.put(str);
//            Log.d("TAG", "arr : " + arr);
        }
        if (!values.isEmpty()) {
            editor.putString(key, arr.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
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
