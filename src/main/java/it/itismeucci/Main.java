package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server startato");
        ServerSocket ss = new ServerSocket(5637);
        Socket mySocket = ss.accept();
        System.out.println("Qualcuno si è collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());
        String stringaRicevuta = "";

        do {
            stringaRicevuta = in.readLine();
            System.out.println("La stringa ricevuta: " + stringaRicevuta);

            String stringaMaiuscola = stringaRicevuta.toUpperCase();
            out.writeBytes(stringaMaiuscola + '\n');

        } while (!stringaRicevuta.equals("!"));

        mySocket.close();
        ss.close();

    }
}