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
public class CarteTransaction extends Carte{
    
    private int montant;
    
    public CarteTransaction(TypeCarte typeCarte, String description, int montant) {
        super(typeCarte, description);
        this.montant=montant;
    }

    
    
    
    @Override
    public Message actionCarte(Joueur aJ) {
        aJ.recevoirLoyer(montant);
        Message msg = new Message();
        msg.typeCarte=Message.TypeCarte.Transaction;
        msg.joueur=aJ;
        return msg;
    }
    
    public String geDesciption() {
        return super.getDescription();
    }
}
