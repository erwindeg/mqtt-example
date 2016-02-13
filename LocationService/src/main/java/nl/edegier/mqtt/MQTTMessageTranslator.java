package nl.edegier.mqtt;

import io.github.giovibal.mqtt.MQTTSession;
import io.github.giovibal.mqtt.parser.MQTTDecoder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import org.dna.mqtt.moquette.proto.messages.PublishMessage;

public class MQTTMessageTranslator extends AbstractVerticle {

	Logger vertxLogger = LoggerFactory.getLogger(MQTTMessageTranslator.class.getName());

	@Override
	public void start() throws Exception {
		final MQTTDecoder dec = new MQTTDecoder();

		vertx.eventBus().consumer(MQTTSession.ADDRESS, m -> {
			Buffer buffer = (Buffer) m.body();

			try {
				PublishMessage message = (PublishMessage) dec.dec(buffer);
				JsonObject json = new JsonObject(message.getPayloadAsString());
				vertxLogger.info(json.toString());
				vertx.eventBus().publish(message.getTopicName(), json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
