package br.edu.unidavi.gamepong;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;

/**
 * Classe que define a incialização do Game Pong
 * @author marcondes
 */
public class Game1 {
    
    private Player jogador;
    
    public static GameEngine GetGameEngine(){
        return GameEngine.getInstance();
    }

    public Player getJogador() {
        return jogador;
    }

    public void setJogador(Player jogador) {
        this.jogador = jogador;
    }

    public Game1() {
        try {
            this.jogador = new Player();
        } catch (IOException ex) {
            Logger.getLogger(Game1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Run() {
        GameEngine.getInstance().addGameStateController(0, this.jogador);        
        GameEngine.getInstance().setStartingGameStateController(0);
        GameEngine.getInstance().addGameStateController(1, this.jogador);        
        GameEngine.getInstance().setStartingGameStateController(1);
        GameEngine.getInstance().run();
    }
    
    public static void main(String[] args) throws IOException {
        GameEngine.getInstance().addGameStateController(0, new Player());
        GameEngine.getInstance().setStartingGameStateController(0);
        GameEngine.getInstance().addGameStateController(1, new Player());
        GameEngine.getInstance().setStartingGameStateController(1);
        GameEngine.getInstance().run();
    }
    
}
