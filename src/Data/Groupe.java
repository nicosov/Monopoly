package Data;

import java.util.ArrayList;

public class Groupe {

    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(CouleurPropriete couleur) {
        this.couleur = couleur;

    }

    public ArrayList<ProprieteAConstruire> getPropriete() {

        return proprietes;
    }

    public void addPropriete(ProprieteAConstruire e) {
        proprietes.add(e);
    }
}
