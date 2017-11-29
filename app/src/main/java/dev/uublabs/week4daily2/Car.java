package dev.uublabs.week4daily2;

import android.graphics.drawable.Drawable;

/**
 * Created by Admin on 11/28/2017.
 */

public class Car
{
    private int image;
    private String make;
    private String model;
    private String year;

    public Car(int image, String make, String model, String year)
    {
        this.image = image;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return year + "\n" + make + "\n" + model;
    }
}
