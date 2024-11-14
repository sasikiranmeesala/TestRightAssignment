package com.springboot.restapi.MQTTConfig;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{"tcp://broker.mqtthq.com:1883"});
        options.setUserName("sasikiran");
        options.setPassword("sasikiran".toCharArray());
        options.setAutomaticReconnect(true);  // Enable automatic reconnect
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("clientId", mqttClientFactory(), "chat/topic");
        adapter.setOutputChannel(mqttInputChannel());
        adapter.setCompletionTimeout(5000);  // Set a timeout for message completion
        //adapter.setRecoveryInterval(5000);  // Set recovery interval for reconnect
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            System.out.println("Message received: " + message.getPayload());
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "outboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("clientId", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("chat/topic");
        return messageHandler;
    }

    @Bean
    public MessageChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public ObjectToStringTransformer objectToStringTransformer() {
        return new ObjectToStringTransformer();
    }
}
