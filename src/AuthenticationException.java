import java.util.InvalidPropertiesFormatException;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super("Anda telah mencapai jumlah batas login");
    }
    public AuthenticationException(String msg) {
        super(msg);
    }
}
