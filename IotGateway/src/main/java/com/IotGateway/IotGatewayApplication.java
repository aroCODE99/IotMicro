package com.IotGateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class IotGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println("Gateway Started....");
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_routes", p -> p
                       .path("/auth/**")
                       .filters(f -> f
                                .addRequestHeader("X-Source", "Gateway")
                                .addResponseHeader("X-Gateway-ID", "MockAi")
                                .rewritePath("/auth/(?<segment>.*)", "/api/auth/${segment}")
                                .circuitBreaker(config -> config
                                                .setName("authCircuitBreaker")
                                                .setFallbackUri("forward:/fallback/auth"))
                                .filter(new RequestLoggingFilter()))
                       .uri("http://authservice:9091")).build();
    }
}

class RequestLoggingFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Request: " + exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
