package exception;

public class AbstractGeneralException extends  RuntimeException{
    public Object requestData;

    public int httpStatus;

    public AbstractGeneralException(String message, Object requestData, int httpStatus) {
        super(message);
        this.requestData = requestData;
        this.httpStatus = httpStatus;
    }
}
