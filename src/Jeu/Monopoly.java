package Jeu;

import Data.AutreCarreau;
import java.util.ArrayList;
import Data.Carreau;
import Data.Compagnie;
import Data.Gare;
import Data.Joueur;
import Data.ProprieteAConstruire;
import java.awt.Color;

public class Monopoly {

    private ArrayList<Carreau> carreaux = new ArrayList<Carreau>();
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Joueur> joueursEnVie = new ArrayList<Joueur>();
    private ArrayList<Joueur> joueursMort = new ArrayList<Joueur>();
    private int i = 0;

    public void avancer(Joueur aJ, int aDes1, int aDes2) {
        System.out.println("--------------------------------------------------");
        System.out.println("Joueur " + aJ.getNom() + " lance les dès...");
        int numc = aJ.getPositionCourante().getNumero();
        System.out.println("Total des dès : " + (aDes1 + aDes2));
        aJ.setPositionCourante(getCarreau(numeroArrivee(numc, aDes1, aDes2)));
        System.out.println("Le joueur " + aJ.getNom() + " arrive sur la case " + aJ.getPositionCourante().getNomCarreau());
    }

    public int numeroArrivee(int aNumc, int aDes1, int aDes2) {
        return (aNumc + aDes1 + aDes2 - 1) % 40;
    }

    public Carreau getCarreau(int aRes) {
        return carreaux.get(aRes);
    }

    public ArrayList<Carreau> getCarreaux_p() {
        return carreaux;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /*   public void getNomCarreau() {
        
    }*/
    public void addCarreau_p(Carreau c) {
        carreaux.add(c);
    }

    public void addJoueur_p(Joueur j) {
        joueurs.add(j);
    }

    public void addJoueursEnVie() {
        for (Joueur j : joueurs) {
            if (j.getCash() >= 0) {
                joueursEnVie.add(j);
            }
        }
    }

    public void removeJoueursEnVie() {
        for (Joueur j : joueurs) {
            if (j.getCash() < 0) {
                joueursEnVie.remove(j);
            }
        }
    }

    public ArrayList<Joueur> getJoueursEnVie() {
        return joueursEnVie;
    }

    void addJoueurMort(Joueur j) {
       joueursMort.add(j);
    }

   public ArrayList<Joueur> getJoueursMort() {
    return joueursMort;
    }
}
