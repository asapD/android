package com.example.asapd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder> {

    private ArrayList<ItemData> mList =  new ArrayList<>();
    private Context context;

    public RecyclerItemAdapter(Context context, ArrayList<ItemData> mList) {
        this.context = context;
        this.mList = mList;
    }

    private RecyclerStoreAdapter.OnItemClickListener itemClickListener;

    public interface OnItemClickListener{
        void onItemClicked(View v, int pos);
    }


    public void setOnItemClickListener(RecyclerStoreAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    public void setItemList(ArrayList<ItemData> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.name);
            content = (TextView) itemView.findViewById(R.id.address);

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

        void onBind(ItemData item){
            title.setText(item.getTitle());
            content.setText(item.getContent());
        }
    }
}