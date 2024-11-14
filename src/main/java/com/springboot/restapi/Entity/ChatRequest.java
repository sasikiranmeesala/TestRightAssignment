package com.springboot.restapi.Entity;

public class ChatRequest {

    private String topic;
    private MessageContent message;

    // Inner class to represent the "message" object
    public static class MessageContent {
        private String data;

        // Getters and Setters
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    // Getters and Setters
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MessageContent getMessage() {
        return message;
    }

    public void setMessage(MessageContent message) {
        this.message = message;
    }
}
