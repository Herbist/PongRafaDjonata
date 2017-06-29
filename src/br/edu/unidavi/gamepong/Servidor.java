package br.edu.unidavi.gamepong;

import java.net.*;
import java.io.*;
import javaPlay.GameEngine;
import br.edu.unidavi.gamepong.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcondes Maçaneiro
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(11000);
        } catch (IOException e) {
            System.err.println("Não foi possível se conectar a porta: 11000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        Socket clientSocket2 = null;
        System.out.println("Esperando conectar.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Conexão falhou.");
            System.exit(1);
        }
        System.out.println("Conectado");
        
        System.out.println("Esperando outro jogador.....");        
        try {
            clientSocket2 = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Conexão falhou.");
            System.exit(1);
        }

        System.out.println("Conectado");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(),
                true);
        BufferedReader in2 = new BufferedReader(
                new InputStreamReader(clientSocket2.getInputStream()));
        
        String inputLine = "";
        int barra2;
        while (clientSocket.isConnected() && clientSocket2.isConnected()) {            
            Controle contr = new Controle();
            String teste = in.readLine();
            String teste2 [];
            teste2 = teste.split(";");
           
            contr.setBarra1(Integer.parseInt(teste2[0]));
            contr.setBarra2(Integer.parseInt(teste2[1]));
            contr.setPosX(Integer.parseInt(teste2[2]));
            contr.setPosY(Integer.parseInt(teste2[3]));
            
            Controle contr2 = new Controle();
            String teste3 = in2.readLine();
            String teste4[];
            teste4 = teste3.split(";");
           
            contr2.setBarra1(Integer.parseInt(teste4[0]));
            contr2.setBarra2(Integer.parseInt(teste4[1]));
            contr2.setPosX(Integer.parseInt(teste4[2]));
            contr2.setPosY(Integer.parseInt(teste4[3]));
            
            contr2.setBarra1(contr.getBarra1());
            contr2.setPosX(contr.getPosX());
            contr2.setPosY(contr.getPosY());                
                
            
            out.println(Integer.toString(contr2.getBarra1()) + ";" +
                      Integer.toString(contr2.getBarra2()) +";" +
                      Integer.toString(contr2.getPosX()) +";" +
                      Integer.toString(contr2.getPosY()));  
            
            out2.println(Integer.toString(contr2.getBarra1()) + ";" +
                      Integer.toString(contr2.getBarra2()) +";" +
                      Integer.toString(contr2.getPosX()) +";" +
                      Integer.toString(contr2.getPosY()));            
            
        }
        out.close();
        in.close();
        out2.close();
        in2.close();
        clientSocket.close();
        clientSocket2.close();
        serverSocket.close();
    }
}
