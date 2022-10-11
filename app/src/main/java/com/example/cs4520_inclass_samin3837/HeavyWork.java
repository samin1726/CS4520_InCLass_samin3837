package com.example.cs4520_inclass_samin3837;

import static java.util.Collections.max;
import static java.util.Collections.min;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;

public class HeavyWork implements Runnable {
    static final int COUNT = 9000000;
    private int complexity;
    private Handler messageQueue;
    public final static int STATUS_START = 0x01;
    public final static int STATUS_PROGRESS = 0x02;
    public final static int STATUS_END = 0x03;
    public final static int STATUS_DATA_SENT = 0x04;


    static ArrayList<Double> getArrayNumbers(int n, HeavyWork heavyWork){
        ArrayList<Double> returnArray = new ArrayList<>();

        for (int i=0; i<n; i++){
            returnArray.add(getNumber());
            Message progressMessage = new Message();
            progressMessage.what = STATUS_PROGRESS;
            Bundle data = new Bundle();
            data.putInt("progress", i);
            progressMessage.setData(data);
            heavyWork.messageQueue.sendMessage(progressMessage);
        }

        return returnArray;
    }

    static double getNumber(){
        double num = 0;
        Random ran = new Random();
        for(int i=0;i<COUNT; i++){
            num = num + (Math.random()*ran.nextDouble()*100+ran.nextInt(50))*1000;
        }
        return num / ((double) COUNT);
    }

    public HeavyWork(int complexity, Handler messageQueue) {
        this.complexity = complexity;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        Message startMessage = new Message();
        startMessage.what = HeavyWork.STATUS_START;
        messageQueue.sendMessage(startMessage);
        ArrayList<Double> arrayOfNumbers = getArrayNumbers(complexity, this);
        Message endMessage = new Message();
        endMessage.what = HeavyWork.STATUS_END;
        messageQueue.sendMessage(endMessage);

        double minimum = min(arrayOfNumbers);
        double maximum = max(arrayOfNumbers);
        double average = 0;
        for (double number: arrayOfNumbers) {
            average += number;
        }
        average = average / complexity;

        Message sendData = new Message();
        sendData.what = STATUS_DATA_SENT;
        Bundle data = new Bundle();
        data.putDouble("min", minimum);
        data.putDouble("max", maximum);
        data.putDouble("avg", average);
        sendData.setData(data);
        messageQueue.sendMessage(sendData);

    }
}
