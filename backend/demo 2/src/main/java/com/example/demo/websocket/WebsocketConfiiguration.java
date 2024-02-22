package com.example.demo.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chatroom","/user");
        registry.setUserDestinationPrefix("/user");
    }
}







// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.converter.DefaultContentTypeResolver;
// import org.springframework.messaging.converter.MappingJackson2MessageConverter;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.util.MimeType;
// import org.springframework.util.MimeTypeUtils;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

// import java.util.List;
// import ch.qos.logback.classic.pattern.MessageConverter;

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebsocketConfiiguration implements WebSocketMessageBrokerConfigurer
// {

// @Override
// public void configureMessageBroker(MessageBrokerRegistry registry)
// {
//     registry.setApplicationDestinationPrefixes("/app");
//     registry.enableSimpleBroker("chatroom", "/user");
//     registry.setUserDestinationPrefix("/user");
// }
// @Override
// public void registerStompEndpoints(StompEndpointRegistry registry)
// {
//     // registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
//     registry.addEndpoint("/ws").withSockJS();
// }
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters){
    
//     DefaultContentTypeResolver resolver= new DefaultContentTypeResolver();
//     resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//     MappingJackson2MessageConverter converter= new MappingJackson2MessageConverter();
//     converter.setObjectMapper(new ObjectMapper());
//     converter.setContentTypeResolver(resolver);
//     messageConverters.add(converter);

//     return false;
//    } 
// }
