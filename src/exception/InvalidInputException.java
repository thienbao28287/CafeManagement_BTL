package exception;
// Sửa thành kế thừa AppException
public class InvalidInputException extends AppException {
    public InvalidInputException(String message) {
        super(message);
    }
}