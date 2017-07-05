package br.com.cordovalabs.beautyclinicexecutante.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterExecutions;

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
        if (roomDto != null) {
            getSupportActionBar().setTitle(roomDto.getDescricao());
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_executions);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RequesterExecutions.request(SchedulingActivity.this, recyclerView, refreshLayout);
            }
        });

        RequesterExecutions.request(this, recyclerView, null);

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
