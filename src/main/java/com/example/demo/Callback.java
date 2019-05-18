package com.example.demo;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/16
 * @PROJECT_NAME: demo
 **/

public class Callback implements MqttCallbackExtended {
    static int count = 0;
    String topic ;
    MqttClient client;

    public Callback(String topic, MqttClient client) {
        this.topic = topic;
        this.client = client;
    }

    @Override
    public void connectComplete(boolean b, String s) {
        try {
            client.subscribe(topic,1);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage message) {
        String msg = new String(message.getPayload());
        count ++;
        System.out.println(msg + ":" + new Date(System.currentTimeMillis())+ Thread.currentThread().getName() + count);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
