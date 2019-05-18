package com.example.demo;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.example.demo
 * @AUTHOR: JcD
 * @DATE: 2019/5/13
 * @PROJECT_NAME: demo
 **/
@RestController
@Scope(value = "session")
public class Consummer {

    //	 private static String topic = "$share/testgroup/wyptest1";
//	 private static String topic = "$queue/wyptest1";
//	 private static String topic = "wyptest1";
    private static int qos = 1;
    private static String broker = "tcp://35.185.134.14:1883";
    private static int count = 0;



    private  MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
//    	 String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.206:1883"};
        connOpts.setCleanSession(false);
//        connOpts.setUserName(userName);
//        connOpts.setPassword(passWord.toCharArray());
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);
        connOpts.setAutomaticReconnect(true);
//         connOpts.setServerURIs(uris);
//         connOpts.setWill(topic, "close".getBytes(), 2, true);
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        Callback callback = new Callback(topic, mqttClient);
        mqttClient.setCallback(callback);
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    public static IMqttToken sub(MqttClient mqttClient,String topic) throws MqttException{
        int[] Qos  = {qos};
        String[] topics = {topic};
//        mqttClient.subscribe(topics, Qos);
        IMqttToken iMqttToken = mqttClient.subscribeWithResponse(topic, qos);
        return iMqttToken;

    }
    MqttClient mqttClient = null;
    String topic;
    @RequestMapping("/consummer")
    private void runsub(String clientId, String topic, HttpSession session) throws MqttException{
//        MqttClient mqttClient = (MqttClient) session.getAttribute("mqttClient");
        this.topic = topic;
        if (mqttClient == null){
            mqttClient = connect(clientId);
//            session.setAttribute("mqttClient",mqttClient);
            System.out.println(mqttClient);
        }
        if(mqttClient != null){
            sub(mqttClient,topic);
        }
    }

    @RequestMapping("/destroy")
    public void destroy(HttpSession session){
        try {
//            MqttClient mqttClient = (MqttClient) session.getAttribute("mqttClient");
            if (mqttClient != null){
//                session.removeAttribute("mqttClient");
                mqttClient.disconnect();
                mqttClient = null;
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
