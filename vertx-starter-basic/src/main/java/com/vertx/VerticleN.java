package com.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleN extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println(getClass().getName());
		startPromise.complete();
	}
}
