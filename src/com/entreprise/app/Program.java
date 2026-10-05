package com.entreprise.app;

import com.entreprise.compta.Facture;
import com.entreprise.compta.Payable;
import com.entreprise.exceptions.EquipeCompleteException;
import com.entreprise.exceptions.MontantInvalideException;
import com.entreprise.rh.Augmentable;
import com.entreprise.rh.ChefProjet;
import com.entreprise.rh.Commercial;
import com.entreprise.rh.Employe;
import com.entreprise.rh.Permanent;

public class Program {

    public static void main(String[] args) {

        // ==============================================
        // 1. Création du service
        // ==============================================

        Service service = new Service(4);

        try {

            Commercial karim = new Commercial(
                    "Karim",
                    3000,
                    40000,
                    "Rabat");

            service.ajouter(karim);

            ChefProjet salma = new ChefProjet(
                    "Salma",
                    6000,
                    "Casablanca");

            service.ajouter(salma);

        } catch (MontantInvalideException e) {

            System.out.println(
                    "Erreur : " + e.getMessage());
        }

        // ==============================================
        // 2. Candidats
        // ==============================================

        String[] noms = {
                "Nadia",
                "Omar",
                "Yassine",
                "Leila",
                "Hamza"
        };

        String[] salaires = {
                "4500",
                "abc",
                "-800",
                "3800",
                "5200"
        };

        int tentatives = 0;

        // ==============================================
        // 3. Recrutement
        // ==============================================

        for (int i = 0; i < noms.length; i++) {

            try {

                // Conversion String -> double
                double salaire =
                        Double.parseDouble(salaires[i]);

                // Peut lever MontantInvalideException
                Permanent employe =
                        new Permanent(noms[i], salaire);

                // Peut lever EquipeCompleteException
                service.ajouter(employe);

                System.out.println(
                        "Embauche de "
                        + noms[i]
                        + " : "
                        + salaire);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Saisie ignorée : \""
                        + salaires[i]
                        + "\" n'est pas un montant");

            } catch (MontantInvalideException e) {

                System.out.println(
                        "Erreur : "
                        + e.getMessage());

            } catch (EquipeCompleteException e) {

                System.out.println(
                        "Erreur : "
                        + e.getMessage());

            } finally {

                // Bloc toujours exécuté
                tentatives++;
            }
        }

        System.out.println(
                "Tentatives : "
                + tentatives
                + ", employés dans le service : "
                + service.getNb());

        // ==============================================
        // 4. Tri des employés
        // ==============================================

        service.trier();

        service.afficher();

        // ==============================================
        // 5. Masse salariale
        // ==============================================

        System.out.println(
                "Masse salariale : "
                + service.getMasseSalariale());

        // ==============================================
        // 6. Employé le moins payé
        // ==============================================

        Employe moinsPaye =
                service.getEmploye(0);

        try {

            // Transtypage Employe -> Augmentable
            Augmentable augmentable =
                    (Augmentable) moinsPaye;

            // +5 %
            augmentable.augmenterStandard();

            System.out.println(
                    "Après augmentation : "
                    + moinsPaye);

            // 50 % : interdit car TAUX_MAX = 20 %
            augmentable.augmenter(0.5);

            // Cette ligne ne sera jamais exécutée
            System.out.println(
                    "Cette ligne ne s'affiche jamais");

        } catch (MontantInvalideException e) {

            System.out.println(
                    "Erreur : "
                    + e.getMessage()
                    + " [valeur reçue : "
                    + e.getValeur()
                    + "]");
        }

       
        try {

            service.getEmploye(7);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Erreur : "
                    + e.getMessage());
        }

        

        Payable[] paiements = {

                new Facture(
                        "F-102",
                        "Bureau Plus",
                        450),

                service.getEmploye(1)
        };

        double totalAPayer = 0;

        for (Payable paiement : paiements) {

            System.out.println(
                    "À payer : "
                    + paiement);

            totalAPayer +=
                    paiement.getMontantAPayer();
        }

        System.out.println(
                "Total à payer : "
                + totalAPayer);

       

        System.out.println(
                "Nombre d'employés créés : "
                + Employe.getNbEmployes());


    }
}