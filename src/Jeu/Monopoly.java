package Jeu;

import Data.AutreCarreau;
import java.util.ArrayList;
import Data.Carreau;
import Data.Carte;
import Data.Compagnie;
import Data.Gare;
import Data.Groupe;
import Data.Joueur;
import Data.ProprieteAConstruire;
import java.awt.Color;

public class Monopoly {

    private ArrayList<Carreau> carreaux = new ArrayList<Carreau>();
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Joueur> joueursEnVie = new ArrayList<Joueur>();
    private ArrayList<Joueur> joueursMort = new ArrayList<Joueur>();
    private int i = 0;
    private ArrayList<Carte> chances = new ArrayList<Carte>();
    private ArrayList<Carte> communautes = new ArrayList<Carte>();
    private int nbMaison = 32;
    private int nbHotel = 12;

    

    

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

    public void addCarteCommunaute(Carte c) {
        communautes.add(c);
    }

    public void addCarteChance(Carte c) {
        chances.add(c);
    }

    public ArrayList<Carreau> getCarreaux() {
        return carreaux;
    }

    public void setCarreaux(ArrayList<Carreau> carreaux) {
        this.carreaux = carreaux;
    }

    public ArrayList<Carte> getChances() {
        return chances;
    }

    public void setChances(ArrayList<Carte> chances) {
        this.chances = chances;
    }

    public ArrayList<Carte> getCommunautes() {
        return communautes;
    }

    public void setCommunautes(ArrayList<Carte> communautes) {
        this.communautes = communautes;
    }

    public Carte tirerCommunaute() {
        Carte carte = null;
        ArrayList<Carte> communauteTemp = new ArrayList();
        carte = communautes.get(communautes.size() - 1);
        communauteTemp.add(carte);
        for (Carte c : communautes) {
            if (c != carte) {
                communauteTemp.add(c);
            }
        }
        communautes = communauteTemp;

        return carte;
    }

    public Carte tirerChance() {
        Carte carte = null;
        ArrayList<Carte> chanceTemp = new ArrayList();
        carte = chances.get(chances.size() - 1);
        chanceTemp.add(carte);
        for (Carte c : chances) {
            if (c != carte) {
                chanceTemp.add(c);
            }
        }
        communautes = chanceTemp;

        return carte;
    }

    public void payerAnniversaire(Joueur aJ) {
        for (Joueur j : getJoueursEnVie()) {
            j.payerLoyer(10);
            aJ.recevoirLoyer(10);
        }
    }

    public void construire(ProprieteAConstruire p) {
            
            int nbMaison = p.getNbMaison();
            p.getProprietaire().payerLoyer(p.getPrixMaison());
            if (nbMaison < 4) {
                p.setNbMaison(nbMaison + 1);
                this.nbMaison-=1;
            } else {
                p.setNbMaison(0);
                p.setNbHotel(1);
                this.nbHotel -=1;
                this.nbMaison += 4;
            }
           
        
    }

    public boolean etudeConstruire(ProprieteAConstruire p) {
        boolean etude = false;
        boolean jproprio = true;
        Groupe g = p.getGroupe();
        Joueur proprioTemp = p.getProprietaire();
        Joueur jProprio = p.getProprietaire();
        int nbMaisonMin = p.getNbMaison();
        for (ProprieteAConstruire prop : g.getPropriete()) {
            int nbMaisonP = prop.getNbMaison();
            int nbHotelP = prop.getNbHotel();
            if (nbHotelP == 1) {
                nbMaisonP = 5;
            }    
            if (nbMaisonP < nbMaisonMin) {
                nbMaisonMin = nbMaisonP;
            }
            if (prop.getProprietaire() != null) {
                proprioTemp = prop.getProprietaire();
                if (proprioTemp != jProprio) {
                    jproprio = false;

                }
            }else{
                jproprio = false;
            }
        }
        int cash = 0;
        cash = jProprio.getCash();
        int nbMaisonP = p.getNbMaison();
        int prixMaison = p.getPrixMaison();

        if (jproprio) {
            if (cash > prixMaison) {
                if (nbMaisonP == nbMaisonMin) {
                    if (nbMaisonP < 4) {
                        if (this.nbMaison > 0) {
                            etude = true;
                            
                        }
                    } else if (nbMaisonP == 4) {
                        if (this.nbHotel > 0) {
                            etude = true;
                        }
                    }
                }
            }
        }

        return etude;
    }
}
