/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Message;

/**
 *
 * @author Alice
 */
public class AllerPrison extends Carreau {

    public AllerPrison(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    @Override
    public int calculLoyer() {
        return 0;
    }

    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        msg.type=Message.Types.ALLER_PRISON;
        msg.joueur=aJ;
        aJ.enPrison();
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
