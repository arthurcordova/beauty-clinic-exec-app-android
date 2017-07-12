package br.com.cordovalabs.beautyclinicexecutante.adapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.Execution;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterStartExecution;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Execution model = mList.get(position);

        if (model != null) {
            holder.tvClient.setText(model.getCodigo());

            String day = model.getData().split("-")[2];
            String hour = model.getHrInicio().substring(0,5);
            String month = Months.values()[Integer.valueOf(model.getData().split("-")[1]) - 1].toString();

            holder.tvDay.setText(day);
            holder.tvHour.setText(hour);
            holder.tvMonth.setText(month);

//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                    builder.setTitle("Procedimento");
//                    builder.setMessage("Deseja iniciar o procedimento? \n ID: "+model.getIdAgenda());
//                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            User user = new User();
//                            user.setCodigo(93);
////                            RequesterStartExecution.request(holder.tvStatus.getContext(), user, model.getIdAgenda(), holder.tvStatus);
//                        }
//                    });
//                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                }
//            });
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
        final TextView tvStatus;

        ViewHolder(View view) {
            super(view);
            mView = view.findViewById(R.id.ll_content_item);
            tvProduct = (TextView) view.findViewById(R.id.tv_product);
            tvClient = (TextView) view.findViewById(R.id.tv_client);
            tvDay = (TextView) view.findViewById(R.id.tv_day);
            tvMonth = (TextView) view.findViewById(R.id.tv_month);
            tvHour = (TextView) view.findViewById(R.id.tv_hour);
            tvStatus = (TextView) view.findViewById(R.id.tv_status);
        }

        void clearAnimation() {
            mView.clearAnimation();
        }

    }
}
