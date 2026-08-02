package com.controller;

import com.model.HealthResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/health")
public class HealthApiImpl {

    private static final Logger LOG = LoggerFactory.getLogger(HealthApiImpl.class);

    @Get
    public Mono<HttpResponse<HealthResponse>> health(HttpRequest<?> request) {
        LOG.info("Method: {}", request.getMethod());
        LOG.info("URI: {}", request.getUri());
        LOG.info("Headers:");

        request.getHeaders().forEach((key, value) ->
            LOG.info("{}: {}", key, value)
        );

        LOG.info("Cookies:");

        request.getCookies().forEach((name, cookie) ->
            LOG.info("{}={}", name, cookie.getValue())
        );
        HealthResponse healthResponse = new HealthResponse().status("UP");
        return Mono.just(HttpResponse.ok(healthResponse));
    }
}
