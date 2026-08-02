package com.controller;

import com.model.CreditResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth.JwtVerify;

@Controller("/accounts")
public class AccountApiImpl {

    private static final Logger LOG = LoggerFactory.getLogger(AccountApiImpl.class);
    private final JwtVerify jwtVerify;

    public AccountApiImpl() {
        this.jwtVerify = new JwtVerify();
    }

    @Get("/{id}/credit")
    public HttpResponse<CreditResponse> accountsIdCreditGet(String id, HttpRequest<?> request) {
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

        var authorizationHeader = request.getHeaders().get("Authorization");

        if (authorizationHeader == null){
            return HttpResponse.unauthorized();
        }

        boolean isValid = jwtVerify.verifyToken(authorizationHeader);
        if (!isValid) {
            return HttpResponse.unauthorized();
        }


        CreditResponse creditResponse = new CreditResponse(id, 1000.0, "USD");
        return HttpResponse.ok(creditResponse);
    }
}

