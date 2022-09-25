package com.example.asapd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asapd.MemberRequest.MemberContactRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class OrderFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerItemAdapter mRecyclerAdapter;
    private ArrayList<ItemData> mList;
    private Button btn_charge;
    private EditText et_detail_address;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ItemData> mlist;

    private SharedPreferences preferences;

    public OrderFragment(){
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
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_order_info, container, false);

        btn_charge = rootView.findViewById(R.id.btn_charge); // 결제하기 버튼
        et_detail_address = rootView.findViewById(R.id.et_detail_address); // 상세 주소 입력

        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        Log.d("TAG", preferences.getString("TOKEN", "NULL"));

        btn_charge.setOnClickListener(new View.OnClickListener() { // 결제하기 버튼 클릭 시 서버에 POST 전송
            @Override
            public void onClick(View v) {
                String destination = et_detail_address.getText().toString();
                HashMap<String, Integer> items = new HashMap<>();
                // items 정보 들고 오기
                items.put("1", 1);
                items.put("2", 3);
                items.put("3", 5); // item 정보 임시 삽입
                // -> "CHECK" 장바구니에 담는건 안드로이드 단에서만 처리(따로 POST 안 함)
                // -> SharedPreference 로 갖고 있다가 결제 완료되면 editor.delete 하면 될 듯

                OrderRequest orderRequest = new OrderRequest(destination, items);
                sendOrderInfo(orderRequest);
            }
        });
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mList = ItemData.createList(5); // <- "CHECK" 실제 GET 요청으로 아이템 리스트 받아오는 코드 필요

        mRecyclerAdapter = new RecyclerItemAdapter(getActivity(),mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }

    private void sendOrderInfo(OrderRequest orderRequest){
        RetrofitClient.getApiService().orderItems(preferences.getString("TOKEN", "NULL"), orderRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse result = response.body();
                if(result.getStatus() == 200){
                    Log.d("TAG", result.getMessage());
                    Log.d("TAG", "orderId : " + result.getData().get("orderId"));
                    Log.d("TAG", "serialNum : " + result.getData().get("serialNum"));
                }
                else if(result.getStatus() == 401){
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("Send code Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}