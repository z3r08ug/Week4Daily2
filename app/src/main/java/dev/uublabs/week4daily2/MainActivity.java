package dev.uublabs.week4daily2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.uublabs.week4daily2.service.ForegroundService;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.btnStartForeground)
    Button btnStartForeground;
    @BindView(R.id.btnStopForeground)
    Button btnStopForeground;
    @BindView(R.id.btnCarList)
    Button btnCarList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btnStartForeground, R.id.btnStopForeground, R.id.btnCarList})
    public void onViewClicked(View view)
    {
        Intent startIntent = new Intent(this, ForegroundService.class);
        startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);

        Intent stopIntent = new Intent(this, ForegroundService.class);
        stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);

        switch (view.getId())
        {
            case R.id.btnStartForeground:
                startService(startIntent);
                break;
            case R.id.btnStopForeground:
                stopService(stopIntent);
                break;
            case R.id.btnCarList:
                startActivity(new Intent(this, CarListActivity.class));
                break;
        }
    }
}
