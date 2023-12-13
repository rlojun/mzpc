        package com.fivemin.mzpc.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.messaging.simp.config.MessageBrokerRegistry;
        import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
        import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
        import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
        import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


        @Configuration
        @EnableWebSocketMessageBroker
        public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

            @Override
            public void registerStompEndpoints(StompEndpointRegistry registry) {
                registry.addEndpoint("/ws")
                        .addInterceptors(new HttpSessionHandshakeInterceptor())
                      .setAllowedOrigins("http://www.mzpc.net") // 특정 도메인만 허용
                   //     .setAllowedOrigins("htt://localhost:9010/ws")
                        //      .allowedOrigins("http://www.mzpc.net")
                        .setAllowedOriginPatterns("*")
                        .withSockJS();
            }

            @Override
            public void configureMessageBroker(MessageBrokerRegistry registry) {
                registry.setApplicationDestinationPrefixes("/app");
                registry.enableSimpleBroker("/topic");
            }
        }
