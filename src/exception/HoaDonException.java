package exception;

public class HoaDonException extends RuntimeException {
	private String errorCode;
    public HoaDonException(String message) {
        super(message);
    }
    public HoaDonException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }

}
