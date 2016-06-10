/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carte;

import Data.Carte;
import Data.Joueur;
import Data.TypeCarte;
import Jeu.Message;

/**
 *
 * @author girina
 */
public class CarteLiberePrison extends Carte{

    public CarteLiberePrison(TypeCarte typeCarte, String description) {
        super(typeCarte, description);
    }

    @Override
    public Message actionCarte(Joueur aJ) {
        aJ.setNbCarteLiberePrison(aJ.getNbCarteLiberePrison()+1);
        Message msg = new Message();
        msg.typeCarte=Message.TypeCarte.LiberePrison;
        msg.joueur=aJ;
        return msg;
    }
    
    public String geDesciption() {
        return super.getDescription();
    }
}
