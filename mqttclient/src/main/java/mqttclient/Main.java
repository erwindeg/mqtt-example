package mqttclient;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {

	public static void main(String[] args) throws MqttSecurityException, MqttException {
//		 MqttClient client = new MqttClient("tcp://broker.mqttdashboard.com", "simulator112233", new MemoryPersistence());
		 MqttClient client = new MqttClient("tcp://localhost", "simulator112233", new MemoryPersistence());

         final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
         //mqttConnectOptions.setWill(DEVICE_STATUS_TOPIC, "offline".getBytes(), 2, true);

         client.connect(mqttConnectOptions);
         Publisher pub = new Publisher(client);
         
         while(true){
        	 try {
				pub.publishData();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	 
         }
	}

}
