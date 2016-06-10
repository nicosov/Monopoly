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
import Jeu.Observateur;

/**
 *
 * @author girina
 */
public class CarteDeplacementRelatif extends Carte{
    private Observateur observateur;
    public CarteDeplacementRelatif(TypeCarte typeCarte, String description) {
        super(typeCarte, description);
    }

    @Override
    public Message actionCarte(Joueur aJ) {
        
        Message msg = new Message();
        msg.typeCarte=Message.TypeCarte.DeplacementRelatif;
        msg.joueur=aJ;
        return msg;
    }
    
    public String geDesciption() {
        return super.getDescription();
    }
}
