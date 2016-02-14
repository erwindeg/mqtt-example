package mqttclient;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Consumer implements MqttCallback {

	private static final String LAST_WILL_TOPIC = "erwindeg/last_will_topic";
	private static final String CLIENT_ID = UUID.randomUUID().toString();
	private final String TOPIC = "erwindeg/location";
	private final MqttClient client;

	public Consumer() throws MqttException {
		this.client = new MqttClient("tcp://broker.mqttdashboard.com", CLIENT_ID, new MemoryPersistence());

		final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setWill(LAST_WILL_TOPIC, "client offline".getBytes(), 2, true);

		client.connect(mqttConnectOptions);
		client.setCallback(this);
		client.subscribe(TOPIC);
	}

	public static void main(String[] args) throws MqttException {
		new Consumer();
	}

	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		System.out.println(new String(message.getPayload(), "UTF-8"));
	}
	
	@Override
	public void connectionLost(Throwable arg0) {
		//TODO: retry connection
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		//TODO: implement message complete action
	}

}