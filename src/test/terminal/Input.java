public class Input {

    public static Touche lire() throws Exception {
        int c = System.in.read();

        if (c == 27) {
            long start = System.currentTimeMillis();
            while (System.in.available() == 0) {
                if (System.currentTimeMillis() - start > 50)
                    return Touche.ECHAP;
            }
            System.in.read();
            int direction = System.in.read();
            return switch (direction) {
                case 68 -> Touche.FLECHE_GAUCHE;
                case 67 -> Touche.FLECHE_DROITE;
                case 65 -> Touche.FLECHE_HAUT;
                case 66 -> Touche.FLECHE_BAS;
                default -> Touche.INCONNU;
            };
        }

        if (c == 10) return Touche.ENTREE;
        if (c >= 32 && c <= 126) return Touche.CARACTERE;

        return Touche.INCONNU;
    }
}