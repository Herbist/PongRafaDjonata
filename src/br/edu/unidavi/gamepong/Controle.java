package br.edu.unidavi.gamepong;

import java.io.Serializable;

/**
 *
 * @author 38560
 */
public class Controle implements Serializable{
    private int barra1;
    private int barra2;
    private int posX;
    private int posY;

    public int getBarra1() {
        return barra1;
    }

    public void setBarra1(int barra1) {
        this.barra1 = barra1;
    }

    public int getBarra2() {
        return barra2;
    }

    public void setBarra2(int barra2) {
        this.barra2 = barra2;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Controle() {
    }
}
