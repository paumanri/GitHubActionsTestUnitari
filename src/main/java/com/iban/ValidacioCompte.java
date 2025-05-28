package com.iban;

public class ValidacioCompte {

    /**
     * Valida un número de compte corrent espanyol (CCC)
     * @param ccc Número CCC sense espais ni separadors (20 dígits)
     * @return true si és vàlid, false si no
     */
    public static boolean validarCCC(String ccc) {
        if (ccc == null || ccc.length() != 20 || !ccc.matches("\\d{20}")) {
            return false; // Ha de tenir 20 dígits numèrics
        }

        String entitat = ccc.substring(0, 4);
        String oficina = ccc.substring(4, 8);
        String dc = ccc.substring(8, 10); // dígits de control
        String compte = ccc.substring(10);

        // Comprovar dígits de control
        int dcCalculat1 = calcularDigitControl("00" + entitat + oficina);
        int dcCalculat2 = calcularDigitControl(compte);

        int dc1 = Character.getNumericValue(dc.charAt(0));
        int dc2 = Character.getNumericValue(dc.charAt(1));

        return (dc1 == dcCalculat1 && dc2 == dcCalculat2);
    }

    /**
     * Calcula el dígit de control segons la normativa del banc
     * @param cadena String de 10 dígits (entitat, oficina o compte)
     * @return dígit de control (0-9)
     */
    private static int calcularDigitControl(String cadena) {
        int[] pesos = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
        int suma = 0;
        for (int i = 0; i < pesos.length; i++) {
            suma += Character.getNumericValue(cadena.charAt(i)) * pesos[i];
        }
        int residu = suma % 11;
        int dc = 11 - residu;
        if (dc == 11) dc = 0;
        if (dc == 10) dc = 1;
        return dc;
    }

    /**
     * Valida un codi IBAN espanyol
     * @param iban Codi IBAN sense espais ni separadors (per exemple "ES7620770024003102575766")
     * @return true si és vàlid, false si no
     */
    public static boolean validarIBAN(String iban) {
        if (iban == null) return false;
        iban = iban.replaceAll("\\s", "").toUpperCase();

        if (!iban.matches("ES\\d{22}")) {
            return false; // Format bàsic IBAN espanyol: ES + 22 dígits
        }

        // Moure els 4 primers caràcters al final
        String codi = iban.substring(4) + iban.substring(0, 4);

        // Convertir lletres a números: A=10, B=11, ..., Z=35
        StringBuilder sb = new StringBuilder();
        for (char c : codi.toCharArray()) {
            if (Character.isLetter(c)) {
                int valor = c - 'A' + 10;
                sb.append(valor);
            } else {
                sb.append(c);
            }
        }

        // Comprovar mòdul 97
        return modulo97(sb.toString()) == 1;
    }

    /**
     * Càlcul del mòdul 97 d'un número molt llarg representat com a cadena
     * @param numero Cadena numèrica
     * @return residu del mòdul 97
     */
    private static int modulo97(String numero) {
        int longitud = numero.length();
        int pos = 0;
        long fragment = 0;

        while (pos < longitud) {
            int longitudFragment = Math.min(9, longitud - pos);
            fragment = fragment * (long) Math.pow(10, longitudFragment) + Long.parseLong(numero.substring(pos, pos + longitudFragment));
            pos += longitudFragment;
            fragment %= 97;
        }
        return (int) fragment;
    }
}
