Chat App Project

A real-time chat application built using HTML, CSS, JavaScript, Spring Boot, and MQTT for message communication.

This project demonstrates a simple chat application where messages are exchanged in real-time. It uses MQTT (via MQTT HQ) as the messaging protocol to ensure lightweight and fast message delivery.

Features:

Real-time messaging using MQTT
Simple and responsive UI
Backend powered by Spring Boot

Technologies Used:

Frontend: HTML, CSS, JavaScript
Backend: Spring Boot
Messaging Protocol: MQTT

Project Structure :

project-root/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── springboot/
│   │   │           └── restapi/
│   │   │               ├── Controller/
│   │   │               │   ├── UserController.java
│   │   │               │   └── ChatController.java
│   │   │               ├── Entity/
│   │   │               │   ├── User.java
│   │   │               │   └── ChatMessage.java
│   │   │               ├── Repository/
│   │   │               │   └── UserRepository.java
│   │   │               ├── Service/
│   │   │               │   ├── UserService.java
│   │   │               │   └── UserServiceImp.java
│   │   │               └── Application.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   ├── style.css
│   │       │   │   └── chat.css
│   │       │   ├── images/
│   │       │   │   └── logo_png.png
│   │       │   ├── js/
│   │       │   │   └── app.js
│   │       │   ├── chat.html
│   │       │   └── index.html
│   └── test/
│       └── java/
│           └── com/
│               └── springboot/
│                   └── restapi/
│                       └── UserControllerTest.java
│
├── .gitignore
├── pom.xml
└── README.md

Features
Real-time Chat: Send and receive messages in real time.
MQTT Integration: Uses MQTT protocol for message delivery.
Responsive UI: Built using HTML, CSS, and JavaScript for a smooth user experience.
Spring Boot Backend: Handles chat functionality and MQTT broker configuration.
Prerequisites
Before running the app, make sure you have the following installed:

Java (version X or higher)
Spring Boot (version X or higher)
MQTT Broker (e.g., MQTT HQ or your own broker)
Setup and Running the Application
1. Clone the Repository
Clone the repository to your local machine:

bash
Copy code
git clone [repository-url]
cd ChatApp
2. Install Dependencies
Ensure you have Maven installed, then navigate to the project directory and run:

bash
Copy code
mvn clean install
3. Configure MQTT Broker
Configure the MQTT broker in application.properties (located in src/main/resources):

properties
Copy code
mqtt.broker.url=tcp://localhost:1883
mqtt.client.id=chat-client
mqtt.topic=chat/topic
Make sure to replace the broker.url with the actual MQTT broker URL if you are using a different broker.

4. Run the Application
To run the Spring Boot application:

bash
Copy code
mvn spring-boot:run
This will start the backend server on http://localhost:8080.

5. Access the Chat App
Open your web browser and go to:

arduino
Copy code
http://localhost:8080
You should now see the chat interface. Start sending messages, and they will be delivered in real time using MQTT.

Project Walkthrough
A video explaining the project is included in the repository as ChatApp_Assignment.mp4 The video provides a detailed overview of the app's architecture, setup, and functionality.

License
This project is licensed under the MIT License.
