package br.com.cordovalabs.beautyclinicexecutante.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterExecutions;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterScheduling;

public class SchedulingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Room roomDto = null;
        try {
            roomDto = (Room) getIntent().getSerializableExtra("Room");
        } catch (Exception e) {
            Log.e("RoomDTO", "Dto Room não encontrado.");
        }

        setContentView(R.layout.activity_scheduling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        TextView tvdate = (TextView) findViewById(R.id.tv_date);
        tvdate.setText(date);

        if (roomDto != null) {
            final String codeSala = roomDto.getCodSala().toString();
            getSupportActionBar().setTitle(roomDto.getDescricao());

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_executions);
            final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
            final View tvNull = findViewById(R.id.tv_null_list);
            final View imgNull = findViewById(R.id.img_null_list);

            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    RequesterScheduling.request(SchedulingActivity.this, recyclerView, refreshLayout, codeSala, imgNull, tvNull);
                }
            });

            RequesterScheduling.request(this, recyclerView, refreshLayout, codeSala, imgNull, tvNull);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//                overridePendingTransition(0, android.R.transition.slide_left);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
