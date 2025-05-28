package com.iban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CCCValidatorTest {

    @Test
    void testCCCValid() {
        String ccc = "20770024003102575766";
        assertTrue(ValidacioCompte.validarCCC(ccc));
    }

    @Test
    void testCCCInvalid() {
        String ccc = "20770024003102575700"; // dígits de control incorrectes
        assertFalse(ValidacioCompte.validarCCC(ccc));
    }
}
