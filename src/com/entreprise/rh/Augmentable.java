package com.entreprise.rh;

import com.entreprise.exceptions.MontantInvalideException;

public interface Augmentable {

    double TAUX_MAX = 0.20;

    void augmenter(double taux) throws MontantInvalideException;

    // Méthode par défaut
    default void augmenterStandard() throws MontantInvalideException {
        augmenter(0.05);
    }
}