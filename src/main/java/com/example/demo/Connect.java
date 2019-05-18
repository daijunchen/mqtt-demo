package com.example.demo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/15
 * @PROJECT_NAME: demo
 **/

public class Connect {
    private static int qos = 1; //只有一次
    private static String broker = "tcp://35.185.134.14:1883";
    MqttClient mqttClient = null;



    public Connect(String clientId) {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
//        connOpts.setUserName(userName);
//        connOpts.setPassword(password.toCharArray());
            connOpts.setConnectionTimeout(10);
            connOpts.setKeepAliveInterval(20);
//		String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.207:1883"};
//		connOpts.setServerURIs(uris);  //起到负载均衡和高可用的作用
            MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
            mqttClient.connect(connOpts);
            this.mqttClient = mqttClient;
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}