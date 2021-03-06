package mqttclient;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

	private static final String LAST_WILL_TOPIC = "erwindeg/last_will_topic";
	private static final String CLIENT_ID = UUID.randomUUID().toString();
	private final String TOPIC = "erwindeg/location";
	private final MqttClient client;

	public Publisher() throws MqttException {
		this.client = new MqttClient("tcp://broker.mqttdashboard.com", CLIENT_ID, new MemoryPersistence());

		final MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setWill(LAST_WILL_TOPIC, "client offline".getBytes(), 2, true);

		this.client.connect(mqttConnectOptions);
	}

	public void publishData() throws MqttException, UnsupportedEncodingException {
		String payload = "{\"clientId\": \"erwin\", \"lat\": \"52.3667\", \"lon\": \"4.9000\"}";
		MqttMessage message = new MqttMessage(payload.getBytes("UTF-8"));
		message.setQos(2);
		message.setRetained(false);
		client.publish(TOPIC, message);
	}

	public static void main(String[] args) throws MqttException {
		Publisher pub = new Publisher();
		while (true) {
			try {
				pub.publishData();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}