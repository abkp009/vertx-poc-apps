package com.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {

	public static void main(String[] args) {
		Vertx myVertx = Vertx.vertx();
		myVertx.deployVerticle(MainVerticle.class.getName());
	}

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		vertx.deployVerticle(new VerticleA());
		vertx.deployVerticle(new VerticleB());
		vertx.deployVerticle(new VerticleN());

		/*
		 * vertx.createHttpServer().requestHandler(req -> { req.response()
		 * .putHeader("content-type", "text/plain") .end("Hello from Vert.x!");
		 * }).listen(8888, http -> { if (http.succeeded()) { startPromise.complete();
		 * System.out.println("HTTP server started on port 8888"); } else {
		 * startPromise.fail(http.cause()); } });
		 */
	}
}
