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
public abstract class CarreauCarte extends Carreau{

    public CarreauCarte(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    
    public abstract Carte tirerCarte();
    
    
}
