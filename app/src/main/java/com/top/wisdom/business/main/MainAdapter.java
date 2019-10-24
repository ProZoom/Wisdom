package com.top.wisdom.business.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.top.wisdom.R;
import com.top.wisdom.bean.GridBean;
import com.top.wisdom.intf.onItemClickListenner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH> {

    private List<GridBean> mDatas;
    private Context mContext;


    private onItemClickListenner onItemClickListenner;

    public MainAdapter(Context context, List<GridBean> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    public void setOnItemClickListenner(com.top.wisdom.intf.onItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_grid, null);

        return new VH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (holder != null) {

            GridBean gridBean = mDatas.get(position);



            //holder.mTitle.setText(mDatas.get(position).getTitleResID());
            //holder.mIcon.setBackgroundResource(mDatas.get(position).getIconResID());
            Glide.with(mContext)
                    .load(mDatas.get(position).getIconResID())
                    .into(holder.mIcon);


            holder.mTitle.setText(mDatas.get(position).getTitle());
            /*Glide.with(mContext)
                    .load(mDatas.get(position).getIcon())
                    .into(holder.mIcon);*/

            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListenner.onClickListenner(position);
                }
            });

            holder.rootLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListenner.onLongClickListenner(position);
                    return true;//拦截
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        AppCompatImageView mIcon;
        AppCompatTextView mTitle;
        RelativeLayout rootLayout;

        public VH(@NonNull View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.item_grid_iv_icon);
            mTitle = itemView.findViewById(R.id.item_grid_tv_title);

            rootLayout = itemView.findViewById(R.id.item_gird);
        }
    }
}
