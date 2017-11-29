package dev.uublabs.week4daily2;

/**
 * Created by Admin on 11/28/2017.
 */

public class Constants
{
    public interface ACTION
    {
        String MAIN_ACTION = "dev.uublabs.week4daily2.action.main";
        String STOP_ACTION = "dev.uublabs.week4daily2.action.prev";
        String PLAY_ACTION = "dev.uublabs.week4daily2.action.play";
        String PAUSE_ACTION = "dev.uublabs.week4daily2.action.next";
        String STARTFOREGROUND_ACTION = "dev.uublabs.week4daily2.action.startforeground";
        String STOPFOREGROUND_ACTION = "dev.uublabs.week4daily2.action.stopforeground";
    }

    public interface NOTIFICATION_ID
    {
        int FOREGROUND_SERVICE = 101;
    }
}
