public class Terminal {

    public static void activerModeRaw() throws Exception {
        Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty raw -echo < /dev/tty"}).waitFor();
    }

    public static void restaurer() throws Exception {
        Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty sane < /dev/tty"}).waitFor();
    }

    public static void clear() throws Exception {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
}