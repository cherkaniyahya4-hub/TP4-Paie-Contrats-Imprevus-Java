package com.entreprise.exceptions;

// Exception non vérifiée
public class EquipeCompleteException extends RuntimeException {

    public EquipeCompleteException(int capacite) {
        super("équipe complète (capacité : " + capacite + ")");
    }
}