package nl.edegier.mqtt;

import io.github.giovibal.mqtt.MQTTBroker;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;



public class MainVerticle extends AbstractVerticle {
	@Override
	public void start() throws Exception {
		JsonObject config = new JsonObject().put("brokers", new JsonArray().add(new JsonObject().put("tcp_port", 1883)));
		DeploymentOptions options = new DeploymentOptions().setConfig(config);
		vertx.deployVerticle(new MQTTBroker(),options);
		vertx.deployVerticle(new LocationLogger());
	}
}
