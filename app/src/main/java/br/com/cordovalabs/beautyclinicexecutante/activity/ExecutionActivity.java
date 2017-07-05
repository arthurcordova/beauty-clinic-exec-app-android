package br.com.cordovalabs.beautyclinicexecutante.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterExecutions;

public class ExecutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Room roomDto = null;
        try {
            roomDto = (Room) getIntent().getSerializableExtra("Room");
        } catch (Exception e) {
            Log.e("RoomDTO", "Dto Room não encontrado.");
        }

        setContentView(R.layout.activity_execution);
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

                RequesterExecutions.request(ExecutionActivity.this, recyclerView, refreshLayout);
            }
        });

        RequesterExecutions.request(this, recyclerView, null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
