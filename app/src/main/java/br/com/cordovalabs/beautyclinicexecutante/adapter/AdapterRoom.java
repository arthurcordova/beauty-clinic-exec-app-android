package br.com.cordovalabs.beautyclinicexecutante.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.activity.ExecutionActivity;
import br.com.cordovalabs.beautyclinicexecutante.dto.Execution;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterStartExecution;
import br.com.cordovalabs.beautyclinicexecutante.util.Months;

/**
 * Created by acstapassoli on 23/01/2017.
 */

public class AdapterRoom extends RecyclerView.Adapter<AdapterRoom.ViewHolder> {

    private List<Room> mList;

    public AdapterRoom(List<Room> items) {
        mList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_rooms, parent, false);
        return new AdapterRoom.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Room model = mList.get(position);
        if (model != null) {
            holder.tvId.setText(String.valueOf(model.getCodSala()));
            holder.tvRoom.setText(model.getDescricao());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(view.getContext(), ExecutionActivity.class);
                    it.putExtra("Room", model);
                    view.getContext().startActivity(it);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    @Override
    public void onViewDetachedFromWindow(AdapterRoom.ViewHolder holder) {
        holder.clearAnimation();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        final TextView tvRoom;
        final TextView tvId;

        ViewHolder(View view) {
            super(view);
            mView = view.findViewById(R.id.ll_content_item);
            tvId = (TextView) view.findViewById(R.id.tv_id);
            tvRoom = (TextView) view.findViewById(R.id.tv_name);
        }

        void clearAnimation() {
            mView.clearAnimation();
        }

    }
}
