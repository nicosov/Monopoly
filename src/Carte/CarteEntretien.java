/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carte;

import Data.Carte;
import Data.*;
import Data.TypeCarte;
import Jeu.Message;

/**
 *
 * @author girina
 */
public class CarteEntretien extends Carte{
    
    private int entretienMaison;
    private int entretienHotel;
    
    public CarteEntretien(TypeCarte typeCarte, String description, int entretienMaison, int entretienHotel) {
        super(typeCarte, description);
        this.entretienMaison=entretienMaison;
        this.entretienHotel=entretienHotel;
    }

    @Override
    public Message actionCarte(Joueur aJ) {
        int coutTotal;
        int nbMaison = 0;
        int nbHotel = 0;
        for(ProprieteAConstruire p : aJ.proprieteAConstruires){
            nbMaison=p.getNbMaison();
            nbHotel=p.getNbHotel();
        }
        coutTotal=nbHotel*entretienHotel + nbMaison*entretienMaison;
        aJ.payerLoyer(coutTotal);
        Message msg = new Message();
        msg.typeCarte=Message.TypeCarte.Entretien;
        msg.joueur=aJ;
        return msg;
    }
    
    public String geDesciption() {
        return super.getDescription();
    }
}
