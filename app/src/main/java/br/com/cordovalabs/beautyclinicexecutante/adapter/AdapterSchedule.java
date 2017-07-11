package br.com.cordovalabs.beautyclinicexecutante.adapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.Schedule;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterStartExecution;
import br.com.cordovalabs.beautyclinicexecutante.util.SessionManager;

/**
 * Created by acstapassoli on 23/01/2017.
 */

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.ViewHolder> {

    private List<Schedule> mList;

    public AdapterSchedule(List<Schedule> items) {
        mList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_scheduling, parent, false);
        return new AdapterSchedule.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Schedule model = mList.get(position);
        if (model != null) {
            holder.tvProduct.setText(model.getProduto());
            holder.tvName.setText(model.getCliente());
            holder.tvTime.setText(model.getHorario());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Procedimento");
                    builder.setMessage("Deseja iniciar o procedimento? \n ID: " + model.getIdAgenda());
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SessionManager sm = new SessionManager(holder.mView.getContext());
                            User user = sm.getSessionUser();
                            RequesterStartExecution.request(holder.mView.getContext(), user, model.getIdAgenda(), holder.mView);
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    @Override
    public void onViewDetachedFromWindow(AdapterSchedule.ViewHolder holder) {
        holder.clearAnimation();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        final TextView tvProduct;
        final TextView tvName;
        final TextView tvTime;


        ViewHolder(View view) {
            super(view);
            mView = view.findViewById(R.id.ll_content_item);
            tvProduct = (TextView) view.findViewById(R.id.tv_product);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
        }

        void clearAnimation() {
            mView.clearAnimation();
        }

    }
}
