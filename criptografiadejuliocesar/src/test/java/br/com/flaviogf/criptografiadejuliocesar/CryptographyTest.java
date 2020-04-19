package br.com.flaviogf.criptografiadejuliocesar;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CryptographyTest {
    private static final String ENCRYPTED_TEXT = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";
    private static final String DECRYPTED_TEXT = "a ligeira raposa marrom saltou sobre o cachorro cansado";

    private Cryptography cryptography;

    @Before
    public void setUp() {
        cryptography = new Cryptography();
    }

    @Test(expected = IllegalArgumentException.class)
    public void encrypt_when_has_been_executed_with_an_empty_string_should_throw_an_error() {
        cryptography.encrypt("");
    }

    @Test(expected = NullPointerException.class)
    public void encrypt_when_has_been_executed_with_an_null_reference_should_throw_an_error() {
        cryptography.encrypt(null);
    }

    @Test
    public void encrypt_when_has_been_executed_with_a_text_should_encrypt_it() {
        assertEquals(ENCRYPTED_TEXT, cryptography.encrypt(DECRYPTED_TEXT));
    }

    @Test
    public void encrypt_when_has_been_executed_with_digits_should_keep_it() {
        String expected = "vhmdp ehp ylqgrv dr dfhohud eudvlo 2019";

        String text = "sejam bem vindos ao Acelera Brasil 2019";

        assertEquals(expected, cryptography.encrypt(text));
    }

    @Test
    public void encrypt_when_has_been_executed_with_uppercase_letters_should_transform_it_to_lowercase() {
        String expected = "dsuhqghu mdyd jhud iholflgdgh";

        String text = "Aprender Java gera felicidade";

        assertEquals(expected, cryptography.encrypt(text));
    }
}
