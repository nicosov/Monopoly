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
public class CarteDeplacementAbsolut extends Carte{
    private String paye;
    private int numCarreau;
    private Observateur observateur;
    public CarteDeplacementAbsolut(TypeCarte typeCarte, String description, int numCarreau, String paye) {
        super(typeCarte, description);
        this.numCarreau=numCarreau;
        this.paye=paye;
    }

    @Override
    public Message actionCarte(Joueur aJ) {
        Message msg = new Message();
        msg.typeCarte=Message.TypeCarte.DeplacementAbsolut;
        msg.joueur=aJ;
        if(paye.compareTo("oui")==0){
            if(aJ.getPositionCourante().getNumero()>numCarreau){
                aJ.recevoirLoyer(200);
            }
        }
       return msg;
    }
    
    public String getDesciption() {
        return super.getDescription();
    }
}
