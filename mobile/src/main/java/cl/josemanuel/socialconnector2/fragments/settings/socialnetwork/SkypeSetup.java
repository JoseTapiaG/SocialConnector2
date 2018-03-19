package cl.josemanuel.socialconnector2.fragments.settings.socialnetwork;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

import cl.josemanuel.socialconnector2.services.SocialNetworkService;

/**
 * Created by farodrig on 19-03-18.
 */

public class SkypeSetup extends SocialNetworkSetup {

    public SkypeSetup(String name){
        super("skype", name);
        setListener((View view, String pass) -> {
            SkypeSetup setup = this;
            try {
                String url = SocialNetworkService.configure(this.getId(), pass);
                saveSkypePassword(view, pass);
                setConnected(true);
            } catch (Exception e) {
                String msg = "Error conectando al servidor o clave incorrecta. Intente de nuevo más tarde.";
                Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }
            return null;
        });

    }

    private void saveSkypePassword(View view, String pass) throws KeyStoreException {
        String alias = "SocialConnector";
        KeyPair kp = null;
        try {
            kp = getKeyPair(view.getContext(), alias);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encryptedPass = encrypt(kp.getPublic(), pass);
        //TODO: Guardar en base de datos
    }
    public KeyPair getKeyPair(Context context, String alias)
            throws UnrecoverableEntryException,
            NoSuchAlgorithmException,
            KeyStoreException,
            IOException,
            CertificateException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        KeyPair keyPair = null;
        KeyStore keyStore = keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Certificate cert = keyStore.getCertificate(alias);

        if (!keyStore.containsAlias(alias) || cert == null) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.YEAR, 30);
            KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSubject(new X500Principal("CN=" + alias))
                    .setSerialNumber(BigInteger.TEN)
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build();
            KeyPairGenerator generator = null;
                generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                generator.initialize(spec);
                generator.generateKeyPair();
            keyPair = generator.generateKeyPair();
        }
        else{
            PrivateKey key = null;
            key = ((KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null)).getPrivateKey();
            PublicKey publicKey = cert.getPublicKey();
            keyPair = new KeyPair(publicKey, key);
        }
        return keyPair;
    }

    public static String encrypt(PublicKey publicKey, String plaintext) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeToString(cipher.doFinal(plaintext.getBytes()), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(PrivateKey privateKey, String ciphertext) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decode(ciphertext, Base64.NO_WRAP)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(String.format("%s/%s/%s","RSA", "NONE", "PKCS1Padding"));
    }
}
