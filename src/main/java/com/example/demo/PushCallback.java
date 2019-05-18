package com.example.demo;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/13
 * @PROJECT_NAME: demo
 **/

class PushCallback implements MqttCallback {
    private String threadId;

    public PushCallback(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
//       System.out.println("deliveryComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msg = new String(message.getPayload());
        System.out.println(threadId + " " + msg + " callback");
    }
}