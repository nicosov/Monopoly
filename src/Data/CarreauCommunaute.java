/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Jeu.Message;
import Jeu.Observateur;
import java.util.ArrayList;

/**
 *
 * @author girina
 */
public class CarreauCommunaute extends CarreauCarte {
    private Observateur observateur;

    public CarreauCommunaute(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

   

    @Override
    public Message action(Joueur aJ) {
        Message msg = new Message();
        msg.type=Message.Types.COMMUNAUTE;
        msg.joueur=aJ;
        return msg;
    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
    }

    @Override
    public int calculLoyer() {
        return 0;
    }

    
    
}
