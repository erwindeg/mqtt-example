package mqttclient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Publisher {

	private final String TEMPERATURE_TOPIC = "erwindeg/temperature";

	private final MqttClient client;

	public Publisher(MqttClient client) {
		this.client = client;
		// this.randomGenerator = new RandomGenerator();
	}

	public void publishData() throws MqttException {
		// MqttMessage message = new MqttMessage();
		// message.setPayload("{foo: bar, lat: 0.23443, long:
		// 12.3453245}".getBytes());
		// client.publish("foo", message);
		Integer temperature = 1;
		client.publish(TEMPERATURE_TOPIC, temperature.toString().getBytes(), 2, false);
	}

}