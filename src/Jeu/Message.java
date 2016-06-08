/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import Data.*;

/**
 *
 * @author carruggn
 */
public class Message {
    // Liste des types de messages

    public enum Types {
        GARE,
        PROPRIETE_A_CONSTRUIRE,
        COMPAGNIE,
        AUTRE_CARREAU
    };

    public Types type;  // type de message

    public Joueur joueur;  
    public Joueur proprietaire;
    public int prixPropriete;
    public int loyerTerrainNu;
    public int loyer;
  //  public String gagnant; // Champ utilisé pour le message JEU_TERMINE

}
