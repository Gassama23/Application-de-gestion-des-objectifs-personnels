package org.odk.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MotDePasseHelper {
	
	/**
     * Hasher un mot de passe avec SHA-256
     */
    public static String hasherMotDePasse(String motDePasse) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    md.digest(
                            motDePasse.getBytes(StandardCharsets.UTF_8)
                    );

            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(
                    "Erreur lors du hashage du mot de passe",
                    e
            );
        }
    }

    /**
     * Vérifier si le mot de passe saisi correspond
     * au mot de passe hashé stocké.
     */
    public static boolean verifierMotDePasse(
            String motDePasseSaisi,
            String motDePasseStocke
    ) {

        String motDePasseHashe =
                hasherMotDePasse(motDePasseSaisi);

        return motDePasseHashe.equals(motDePasseStocke);
    }

}
