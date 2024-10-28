package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread extends Thread {
    private Socket mySocket;
    private ArrayList<String> listaNote;

    public MyThread(Socket s) {
        mySocket = s;

    }

    @Override
    public void run() {
        listaNote = new ArrayList<>();
        try {
            System.out.println("Client collegato");

            BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());
            String stringaRicevuta = "";

            do {

                stringaRicevuta = in.readLine();

                if (stringaRicevuta.equals("?")) {
                    for (String oggetto : listaNote) {
                        out.writeBytes(oggetto + '\n');
                    }
                    out.writeBytes("@" + "\n");

                } else {

                    listaNote.add(stringaRicevuta);
                    out.writeBytes("OK" + "\n");

                }

            } while (!stringaRicevuta.equals("!"));

            mySocket.close();
        } catch (IOException e) {
            System.out.println("CLIENT SCOLLEGATO");
        }
    }

}
