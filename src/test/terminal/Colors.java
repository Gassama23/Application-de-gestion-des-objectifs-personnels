
import java.util.Map;

public class Colors {

        public enum ColorName {
                BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
        }

        public static Map<ColorName, String> colors = Map.of(
                ColorName.BLACK, "\033[30m",
                ColorName.RED, "\033[31m",
                ColorName.GREEN, "\033[32m",
                ColorName.YELLOW, "\033[33m",
                ColorName.BLUE, "\033[34m",
                ColorName.MAGENTA, "\033[35m",
                ColorName.CYAN, "\033[36m",
                ColorName.WHITE, "\033[37m"
        );
}
