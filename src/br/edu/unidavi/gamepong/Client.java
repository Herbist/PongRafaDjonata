package br.edu.unidavi.gamepong;

import br.edu.unidavi.gamepong.Game1;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcondes Maçaneiro
 */
public class Client {
    
    public static void main(String[] args) throws IOException, Exception {
        Controle controle = new Controle();
        String serverHostname = new String("127.0.0.1");

        if (args.length > 0) {
            serverHostname = args[0];
        }
        System.out.println("Conectado ao host "
                + serverHostname + " na porta 11000.");

        Socket echoSocket = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostname, 11000);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Não conectado ao host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não conseguiu se conectar "
                    + "a conexão: " + serverHostname);
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Game1 game = new Game1();
        game.Run();
        
        while (echoSocket.isConnected()) {
            controle.setBarra1(game.getJogador().getPosBarra1());
            controle.setBarra2(game.getJogador().getPosBarra2());
            controle.setPosX(game.getJogador().getPosBolaX());
            controle.setPosY(game.getJogador().getPosBolaY());
            String teste = Integer.toString(game.getJogador().getPosBarra1()) + ";" +
                           Integer.toString(game.getJogador().getPosBarra2()) +";" +
                           Integer.toString(game.getJogador().getPosBolaX()) +";" +
                           Integer.toString(game.getJogador().getPosBolaY());
            
            out.println(teste);
            
            teste = in.readLine();
            String teste2 [];
            teste2 = teste.split(";");
           
            game.getJogador().setPosBarra1(Integer.parseInt(teste2[0]));
            game.getJogador().setPosBarra2(Integer.parseInt(teste2[1]));
            game.getJogador().setPosBolaX(Integer.parseInt(teste2[2]));
            game.getJogador().setPosBolaY(Integer.parseInt(teste2[3]));
                
            game.GetGameEngine().run();
        }
        
        in.close();
        stdOut.close();
        stdIn.close();
        echoSocket.close();
    }
}
