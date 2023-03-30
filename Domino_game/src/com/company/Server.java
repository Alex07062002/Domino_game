package com.company;

import com.company.server.ClientServerApplication;
import com.company.server.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{
    private final ServerSocket serverSocket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Server server = new Server(port);
            server.start();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot start the server", e);
        }
    }

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Game server started on port: " + serverSocket.getLocalPort());
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ConnectionHandler handler = new ConnectionHandler(clientSocket);
            System.out.println("Client connected from: " + clientSocket.getInetAddress());
            ClientServerApplication gameSession = new ClientServerApplication(clientSocket, handler);
            Thread t = new Thread(gameSession);
            t.start();
        }
    }
}

