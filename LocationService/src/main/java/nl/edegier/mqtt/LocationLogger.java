package nl.edegier.mqtt;
import io.vertx.core.AbstractVerticle;

public class LocationLogger extends AbstractVerticle {
	
	private final String EB_ADDRESS = "io.github.giovibal.mqtt";
	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer(EB_ADDRESS, m -> {
			System.out.println(m.body());
		});
	}
}
