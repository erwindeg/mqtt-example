package nl.edegier.mqtt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class WebVerticle extends AbstractVerticle {
	private static final int PORT = 9080;
	private static final String PATH = "app";
	private static final String WELCOME_PAGE = "index.html";

	// public static void main(String[] args) {
	// if (args.length > 0) {
	// port = Integer.parseInt(args[0]);
	// }
	//
	// Vertx.clusteredVertx(new VertxOptions(), result ->
	// {result.result().deployVerticle(new WebVerticle());});
	// }

	@Override
	public void start() throws Exception {
		Router router = Router.router(vertx);
		vertx.createHttpServer(new HttpServerOptions().setPort(PORT))
				.requestHandler(req -> router.accept(req)).listen();
		router.get("/").handler(
				StaticHandler.create().setWebRoot(PATH)
						.setIndexPage(WELCOME_PAGE));
		router.get("/" + PATH + "/*").handler(
				StaticHandler.create().setWebRoot(PATH));
		SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
		sockJSHandler.bridge(new BridgeOptions()
				.addOutboundPermitted(new PermittedOptions()
						.setMatch(new JsonObject())));
		router.route("/eventbus/*").handler(sockJSHandler);
	}

}
