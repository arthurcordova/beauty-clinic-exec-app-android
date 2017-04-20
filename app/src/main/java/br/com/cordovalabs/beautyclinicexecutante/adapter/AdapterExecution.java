package br.com.cordovalabs.beautyclinicexecutante.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.model.Execution;
import br.com.cordovalabs.beautyclinicexecutante.util.Months;

/**
 * Created by acstapassoli on 23/01/2017.
 */

public class AdapterExecution extends RecyclerView.Adapter<AdapterExecution.ViewHolder> {

    private List<Execution> mList;

    public AdapterExecution(List<Execution> items) {
        mList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_execution, parent, false);
        return new AdapterExecution.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Execution model = mList.get(position);
        if (model != null) {
            holder.tvProduct.setText(model.getProduto());
            holder.tvClient.setText(model.getCliente());

            String day = model.getData().split("-")[2];
            String hour = model.getHorario().substring(0,5);
            String month = Months.values()[Integer.valueOf(model.getData().split("-")[1]) - 1].toString();

            holder.tvDay.setText(day);
            holder.tvHour.setText(hour);
            holder.tvMonth.setText(month);
        }



//        holder.tvDateTime.setText(model.getData().replaceAll("-","/") + " " + model.getHorario());
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    @Override
    public void onViewDetachedFromWindow(AdapterExecution.ViewHolder holder) {
        holder.clearAnimation();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        final TextView tvProduct;
        final TextView tvClient;
        final TextView tvDay;
        final TextView tvMonth;
        final TextView tvHour;

        ViewHolder(View view) {
            super(view);
            mView = view;
            tvProduct = (TextView) view.findViewById(R.id.tv_product);
            tvClient = (TextView) view.findViewById(R.id.tv_client);
            tvDay = (TextView) view.findViewById(R.id.tv_day);
            tvMonth = (TextView) view.findViewById(R.id.tv_month);
            tvHour = (TextView) view.findViewById(R.id.tv_hour);
        }

        void clearAnimation() {
            mView.clearAnimation();
        }

    }
}
