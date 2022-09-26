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

    private ArrayList<ItemData> mList;
    private Context context;

    public RecyclerItemAdapter(Context context, ArrayList<ItemData> mList) {
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
    public RecyclerItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RecyclerItemAdapter.ViewHolder(view);
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
        TextView tv_name;
        TextView tv_description;
        TextView tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);

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
            tv_name.setText(item.getName());
            tv_description.setText(item.getDescription());
            tv_price.setText((CharSequence) (item.getPrice()+""));
        }
    }
}