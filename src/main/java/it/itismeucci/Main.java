package it.itismeucci;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Server startato");

        ServerSocket ss = new ServerSocket(5672);
        while (true) {
            Socket mySocket2 = ss.accept();
            MyThread t = new MyThread(mySocket2);
            t.start();
        }

    }
}