package com.iban;

public class ForcaBrutaCCC {

    /**
     * Prova totes les combinacions possibles per als dos últims dígits d'un CCC amb dígits amagats
     * @param cccAmbForats CCC amb els dos últims dígits amagats, per exemple "207700240031025757XX"
     */
    public static void provarForcaBruta(String cccAmbForats) {
        if (cccAmbForats == null || cccAmbForats.length() != 20) {
            System.out.println("El CCC ha de tenir 20 caràcters");
            return;
        }
        if (!cccAmbForats.substring(0, 18).matches("\\d{18}")) {
            System.out.println("Els 18 primers caràcters han de ser dígits");
            return;
        }

        int comptador = 0;
        for (int i = 0; i <= 99; i++) {
            // Formatejar els dos últims dígits amb dos números (ex: 0 -> "00", 7 -> "07")
            String ultimDos = String.format("%02d", i);
            String possibleCCC = cccAmbForats.substring(0, 18) + ultimDos;

            if (ValidacioCompte.validarCCC(possibleCCC)) {
                System.out.println("CCC vàlid trobat: " + possibleCCC);
                comptador++;
            }
        }

        if (comptador == 0) {
            System.out.println("No s'ha trobat cap CCC vàlid amb els últims dos dígits amagats.");
        } else {
            System.out.println("S'han trobat " + comptador + " combinacions vàlides.");
        }
    }

    public static void main(String[] args) {
        // Exemple: els últims dos dígits amagats com "XX"
        String cccAmbForats = "207700240031025757XX".replace("XX", "00"); // només per estructura
        provarForcaBruta(cccAmbForats);
    }
}
