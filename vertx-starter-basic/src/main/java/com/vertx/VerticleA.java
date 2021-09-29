package com.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println(getClass().getName());
		vertx.deployVerticle(new VerticleAA());
		vertx.deployVerticle(new VerticleAB());
		startPromise.complete();
	}

	@Override
	public void stop(Promise<Void> stopPromise) throws Exception {
		System.out.println("Stopping "+ getClass().getName());
		super.stop(stopPromise);
		stopPromise.complete();
	}
}
