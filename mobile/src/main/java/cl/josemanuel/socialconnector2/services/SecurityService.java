package cl.josemanuel.socialconnector2.services;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

import cl.josemanuel.socialconnector2.database.SecurityDB;

public class SecurityService {
    public SecurityService() {
    }

    public String getPassword(Context context, String social){
        String encrypted_pass = new SecurityDB(context).getPassword(social);
        if (encrypted_pass == null) return null;
        KeyPair kp = null;
        try {
            kp = this.getKeyPair(context, "SocialConnector");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return this.decrypt(kp.getPrivate(), encrypted_pass);
    }

    public boolean savePassword(Context context, String social, String plain_pass){
        KeyPair kp = null;
        try {
            kp = this.getKeyPair(context, "SocialConnector");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        long id = new SecurityDB(context).insertPassword(social, encrypt(kp.getPublic(), plain_pass));
        return id > 0;
    }

    private KeyPair getKeyPair(Context context, String alias)
            throws UnrecoverableEntryException,
            NoSuchAlgorithmException,
            KeyStoreException,
            IOException,
            CertificateException,
            NoSuchProviderException,
            InvalidAlgorithmParameterException {

        KeyPair keyPair = null;
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
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
        } else {
            PrivateKey key = null;
            key = ((KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null)).getPrivateKey();
            PublicKey publicKey = cert.getPublicKey();
            keyPair = new KeyPair(publicKey, key);
        }
        return keyPair;
    }

    private String encrypt(PublicKey publicKey, String plaintext) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeToString(cipher.doFinal(plaintext.getBytes()), Base64.NO_WRAP);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String decrypt(PrivateKey privateKey, String ciphertext) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decode(ciphertext, Base64.NO_WRAP)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(String.format("%s/%s/%s", "RSA", "NONE", "PKCS1Padding"));
    }
}