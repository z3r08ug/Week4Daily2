package dev.uublabs.week4daily2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.uublabs.week4daily2.service.MyBoundService;

public class CarListActivity extends AppCompatActivity
{
    @BindView(R.id.btnBindService)
    Button btnBindService;
    @BindView(R.id.btnUnbindService)
    Button btnUnbindService;
    @BindView(R.id.btnGetCarList)
    Button btnGetCarList;
    private List<Car> cars;
    private RecyclerView recyclerView;
    private MyBoundService myBoundService;
    private RecyclerAdapter recyclerAdapter;

    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_list);
        ButterKnife.bind(this);

        cars = new ArrayList<>();
        recyclerView = findViewById(R.id.rvCars);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
    }

    @OnClick({R.id.btnBindService, R.id.btnUnbindService, R.id.btnGetCarList})
    public void onViewClicked(View view)
    {
        Intent boundIntent = new Intent(this, MyBoundService.class);
        switch (view.getId())
        {
            case R.id.btnBindService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                if (isBound)
                    unbindService(serviceConnection);
                onServiceUnbind();
                break;
            case R.id.btnGetCarList:
                if (isBound)
                {
                    recyclerAdapter = new RecyclerAdapter(cars, alarmManager);
                    recyclerView.setAdapter(recyclerAdapter);
                }
                else
                {
                    cars = new ArrayList<>();
                    recyclerAdapter = new RecyclerAdapter(cars, alarmManager);
                    recyclerView.setAdapter(recyclerAdapter);
                }
                break;
        }
    }

    private boolean isBound;
    ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            isBound = true;
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) service;
            myBoundService = myBinder.getService();
            myBoundService.initData();
            cars = myBoundService.getData();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {

        }
    };

    private void onServiceUnbind()
    {
        isBound = false;
    }
}
