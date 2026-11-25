package com.sap.leave;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class LeaveServiceApplication {

    public static void main(String[] args) throws Exception {

        String environment = System.getenv()
                .getOrDefault("APP_ENV", "local");

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8080), 0);

        server.createContext("/", exchange -> {

            String response =
                    "Employee Leave Service is running. Environment: "
                    + environment;

            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/plain; charset=UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);

            exchange.getResponseBody().write(bytes);
            exchange.close();
        });

        server.start();

        System.out.println(
                "Employee Leave Service started in " + environment
                + " environment on port 8080");
    }
}
