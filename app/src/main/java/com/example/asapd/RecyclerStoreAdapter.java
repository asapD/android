package com.example.asapd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStoreAdapter extends RecyclerView.Adapter<RecyclerStoreAdapter.ViewHolder>{
    private ArrayList<StoreData> mList =  new ArrayList<>();
    private Context context;

    public RecyclerStoreAdapter(Context context, ArrayList<StoreData> mList) {
        this.context = context;
        this.mList = mList;
    }

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener{
        void onItemClicked(View v, int pos);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public RecyclerStoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list, parent, false);
        return new RecyclerStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStoreAdapter.ViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    public void setItemList(ArrayList<StoreData> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView title;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = (ImageView) itemView.findViewById(R.id.profile);
            title = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.content);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(itemClickListener != null){
                            itemClickListener.onItemClicked(v,pos);
                        }
                    }
                }
            });
        }

        void onBind(StoreData item){
            profile.setImageResource(item.getResId());
            title.setText(item.getTitle());
            content.setText(item.getContent());
        }
    }

}
