package exception;

import org.eclipse.jetty.http.HttpStatus;

public class ErrorCreatePaymentException extends AbstractGeneralException {
    public ErrorCreatePaymentException(String message, Object requestData) {
        super(message, requestData, HttpStatus.BAD_REQUEST_400);
    }
}
