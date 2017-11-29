package dev.uublabs.week4daily2.rxjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Admin on 11/28/2017.
 */

public class test
{
    public static void main(String[] args)
    {
        //create an observable for integer values
        Observable <Integer> integerObservable = Observable.range(1, 50);
        Observable <Integer> empty = Observable.empty();

        //create observer to get values from observable
        Observer<Integer> integerObserver = new Observer<Integer>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(Integer integer)
            {
                System.out.println("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        };

        integerObservable
                .filter(new Predicate<Integer>()
                {
                    @Override
                    public boolean test(Integer integer) throws Exception
                    {
                        if (integer > 5)
                            return true;
                        else
                            return false;
                    }
                })
                .map(new Function<Integer, Integer>()
                {
                    @Override
                    public Integer apply(Integer integer) throws Exception
                    {
                        return integer * 10;
                    }
                })
                .take(20)
                .subscribe(integerObserver);

        Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);

        Observer<Long> longObserver = new Observer<Long>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {

            }

            @Override
            public void onNext(Long aLong)
            {
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        };

        Observable<Long> clock = Observable.merge(
                slow,
                fast);

        clock
                .subscribe(longObserver);
        List<String> players = new ArrayList<String>();
        players.add("Tom");
        players.add("Edelman");
        players.add("Gronk");
        players.add("Amendola");
        players.add("Hogan");
        players.add("Cooks");
        Observable<List<String>> pats = Observable.fromArray(players);
        List<String> newPlayers = (List<String>) pats.toList();

    }

}
