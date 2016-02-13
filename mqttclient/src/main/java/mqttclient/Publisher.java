package mqttclient;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

	private final String TOPIC = "erwindeg/location";

	private final MqttClient client;

	public Publisher(MqttClient client) {
		this.client = client;
		// this.randomGenerator = new RandomGenerator();
	}

	public void publishData() throws MqttException, UnsupportedEncodingException {
		// MqttMessage message = new MqttMessage();
		// message.setPayload("{foo: bar, lat: 0.23443, long:
		// 12.3453245}".getBytes());
		// client.publish("foo", message);
		String payload = "{\"clientId\": \"erwin\", \"lat\": \"52.3667\", \"lon\": \"4.9000\"}";
		MqttMessage message = new MqttMessage(payload.getBytes(
				"UTF-8"));
		message.setQos(2);
		message.setRetained(false);
//		System.out.println(new String(message.getPayload(),"UTF-8"));
		client.publish(TOPIC, message);
	}

}