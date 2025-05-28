package com.iban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IBANValidatorTest {

    @Test
    void testIBANValid() {
        String iban = "ES7620770024003102575766";
        assertTrue(ValidacioCompte.validarIBAN(iban));
    }

    @Test
    void testIBANInvalid() {
        String iban = "ES0020770024003102575766";
        assertFalse(ValidacioCompte.validarIBAN(iban));
    }
}
