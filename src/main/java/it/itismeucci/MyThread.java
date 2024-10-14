package it.itismeucci;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyThread  extends Thread {
    private Socket mySocket;

    public MyThread(Socket s) {
        mySocket = s;

    }

    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            System.out.println("Errore");
        }
    }
}
