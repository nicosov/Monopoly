/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;

/**
 *
 * @author girina
 */
public class CarreauChance extends CarreauCarte {

    public CarreauChance(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    @Override
    public Carte tirerCarte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void action(Joueur aJ) {
        //Carte carte=tirerCarte(aJ);
        //carte.actionCarte(aJ);
        
    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
        
    }

}
