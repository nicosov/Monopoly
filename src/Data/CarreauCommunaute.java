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
public class CarreauCommunaute extends CarreauCarte {

    public CarreauCommunaute(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    @Override
    public Carte tirerCarte() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void action(Joueur aJ) {
        tirerCarte();
    }

    @Override
    public boolean etudeAchatPropriete(Joueur aJ) {
        return false;
    }

    @Override
    public void acheterPropriete(Joueur aJ) {
    }

}
