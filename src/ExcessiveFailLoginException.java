public class ExcessiveFailLoginException extends Exception {
    public ExcessiveFailLoginException() {
        super("Anda telah mencapai jumlah batas login");
    }
    public ExcessiveFailLoginException(String message) {
        super(message);
    }
}
