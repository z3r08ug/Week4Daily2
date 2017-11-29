package dev.uublabs.week4daily2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import dev.uublabs.week4daily2.Car;
import dev.uublabs.week4daily2.R;

public class MyBoundService extends Service
{

    private List<Car> cars;

    public MyBoundService()
    {

    }
    IBinder iBinder = new MyBinder();

    public class MyBinder extends Binder
    {
        public  MyBoundService getService()
        {
            return MyBoundService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return iBinder;
    }

    public void initData()
    {
        cars = new ArrayList<>();
        cars.add(new Car(R.drawable.vette94, "Chevrolet", "Corvette", "1994"));
        cars.add(new Car(R.drawable.jag, "Jaguar", "XE", "2017"));
        cars.add(new Car(R.drawable.silverado, "Chevrolet", "Silverado", "2017"));
        cars.add(new Car(R.drawable.f150, "Ford", "F-150", "2017"));
        cars.add(new Car(R.drawable.porsche, "Porsche", "718 Boxster", "2017"));
        cars.add(new Car(R.drawable.benz, "Mercedes-Benz", "S-Class", "2017"));
        cars.add(new Car(R.drawable.mustang, "Ford", "Mustang", "2017"));
        cars.add(new Car(R.drawable.tesla, "Tesla", "Roadster", "2017"));
        cars.add(new Car(R.drawable.maserati, "Maserati", "Ghibli", "2018"));
    }

    public List<Car> getData()
    {
        return cars;
    }
}
