package com.example.socketio.component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.socketio.message.Message;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named
public class SocketModule {

    private final SocketIOServer socketIOServer;


    public SocketModule(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("message", Message.class, onChatReceived());
    }

    private ConnectListener onConnected() {
        return client -> log.info("Client connected: {}", client.getSessionId());
    }

    private DisconnectListener onDisconnected() {
        return client -> log.info("Client disconnected: {}", client.getSessionId());
    }

    private DataListener<Message> onChatReceived() {
        return (client, data, ackSender) -> {
            log.info("Message received: {}", data);
            socketIOServer.getBroadcastOperations().sendEvent("message", data);
        };
    }
}
