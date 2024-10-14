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
                String op = in.readLine();
                
                switch (op) {
                case "1":
                stringaRicevuta = stringaRicevuta.toUpperCase();
                    break;

                case "2":
                stringaRicevuta = stringaRicevuta.toLowerCase();
                    break;

                case "3":
                stringaRicevuta = reverseString(stringaRicevuta);
                    break; 

                case "4":
                stringaRicevuta = "Numero caratteri: " + stringaRicevuta.length();
                    break;
            
                default:
                    break;
            }

                System.out.println("La stringa ricevuta: " + stringaRicevuta);

                out.writeBytes(stringaRicevuta + '\n');

            } while (!stringaRicevuta.equals("!"));

            mySocket.close();
        } catch (IOException e) {
            System.out.println("Errore");
        }
    }

    public String reverseString(String str) {
        var newString = "";
        for (var i = str.length() - 1; i >= 0; i--) {
            newString += str.charAt(i);
        }
        return newString;
    }
}

