/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Message;

/**
 *
 * @author girina
 */
public class Impots extends Carreau{

    private int taxe;
    public Impots(int numero, String nomCarreau, int taxe) {
        super(numero, nomCarreau);
        this.taxe=taxe;
    }

    @Override
    public int calculLoyer() {
        return 0;
    }

    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        msg.type=Message.Types.IMPOTS;
        msg.taxe=taxe;
        return msg;
    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
        
    }
    
}
