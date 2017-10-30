package com.andro.indie.school.myretrofitapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andro.indie.school.myretrofitapp.R;
import com.andro.indie.school.myretrofitapp.network.response.CityListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herisulistiyanto on 9/5/17.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<CityListResponse.City> cityList;
    private Context context;

    public MyRecyclerAdapter(Context context) {
        cityList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDesc.setText(cityList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return (null != cityList) ? cityList.size() : 0;
    }

    public void updateCityList(List<CityListResponse.City> cityList) {
        this.cityList.clear();
        this.cityList.addAll(cityList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvDesc = itemView.findViewById(R.id.tv_description);
        }
    }

}
