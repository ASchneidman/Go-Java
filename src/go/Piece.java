/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

/**
 *
 * @author Alex
 */
public class Piece  {

    private int row;
    private int colomn;
    private String type;
    private int place;

    /**
     *
     * @param row
     * @param colomn
     * @param currentPlayer
     */
    public Piece (int row, int colomn, Player currentPlayer, int pos) {
        this.row = row;
        this.colomn = colomn;
        place = pos;
        
        if (currentPlayer.getPlayerType() == 1) {
            type = "(@@)     ";
        } else if (currentPlayer.getPlayerType() == 2) {
            type = "(##)     ";
        }
        
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColomn() {
        return colomn;
    }

    public void setColomn(int colomn) {
        this.colomn = colomn;
    }

    public String getType() {
        return type;
    }

    public void setType(int currentPlayer) {
        if (currentPlayer == 1) {
            type = "(@@)     ";
        } else if (currentPlayer == 2) {
            type = "(##)     ";
        }
    }
    
    public int getPlace() {
        return place;
    }
    
    public void setPlace(int place) {
        this.place = place;
    }
    
}
