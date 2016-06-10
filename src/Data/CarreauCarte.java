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
public abstract class CarreauCarte extends Carreau{
    private Observateur observateur;

    public CarreauCarte(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }
    @Override
    public abstract Message action(Joueur aJ);   
    
    
    
    
}
