package com.example.demo;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/13
 * @PROJECT_NAME: demo
 **/

@RestController
public class PubMsg {
    private static int qos = 2; //只有一次
    private static String broker = "tcp://localhost:1883";
    private static String userName = "tuyou";
    private static String passWord = "tuyou";
    static MqttClient mqttClient = null;


    private static MqttClient connect(String clientId, String userName,
                                      String password) throws MqttException {

        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
//        connOpts.setUserName(userName);
//        connOpts.setPassword(password.toCharArray());
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);
        connOpts.setAutomaticReconnect(true);
        //		String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.207:1883"};
//		connOpts.setServerURIs(uris);  //起到负载均衡和高可用的作用
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.setCallback(new PushCallback("test"));
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    private static void pub(MqttClient sampleClient, String msg,String topic)
            throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(qos);
        message.setRetained(false);
        sampleClient.publish(topic, message);
    }

    @RequestMapping("/test")
    private static void publish(String msg) throws MqttException{
        String clientId = "testDemo";
        String topic = "test";
        if (mqttClient == null){
            mqttClient = connect(clientId,userName,passWord);
            System.out.println(mqttClient);
        }

        if (mqttClient != null) {
            pub(mqttClient, msg, topic);
            System.out.println("pub-->" + msg);
        }
    }
}





