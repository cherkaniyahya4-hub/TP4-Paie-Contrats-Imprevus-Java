package com.entreprise.rh;

import com.entreprise.exceptions.MontantInvalideException;

public class ChefProjet extends Permanent {

    public ChefProjet(
            String nom,
            double salaire,
            String agence)
            throws MontantInvalideException {

        super(nom, salaire, agence);
    }

    public double getPrime() {
        return 500;
    }

    @Override
    public String getPoste() {
        return "ChefProjet";
    }

    @Override
    public double getSalaire() {
        return super.getSalaire() + getPrime();
    }

    /*
     * ChefProjet est augmentable même sans écrire
     * "implements Augmentable" ici,
     * car ChefProjet hérite de Permanent,
     * et Permanent réalise Augmentable.
     */
}