package dev.uublabs.week4daily2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 11/27/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    List<Car> cars;
    AlarmManager alarmManager;

    public RecyclerAdapter(List<Car> cars, AlarmManager alarmManager)
    {
        this.cars = cars;
        this.alarmManager = alarmManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position)
    {
        Car car = cars.get(position);
        if(car != null)
        {
            holder.tvCarInfo.setText(car.toString());
            holder.ivCar.setImageResource(car.getImage());
        }
    }

    @Override
    public int getItemCount()
    {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvCarInfo;
        private final ImageView ivCar;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvCarInfo = itemView.findViewById(R.id.tvCarInfo);
            ivCar = itemView.findViewById(R.id.ivCar);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String info = cars.get(getAdapterPosition()).toString();
                    int picture = cars.get(getAdapterPosition()).getImage();


                    Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                    notificationIntent.addCategory("android.intent.category.DEFAULT");
                    notificationIntent.putExtra("info", info);
                    notificationIntent.putExtra("pic", picture);

                    PendingIntent broadcast = PendingIntent.getBroadcast(v.getContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, 10);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
                }
            });
        }
    }
}
