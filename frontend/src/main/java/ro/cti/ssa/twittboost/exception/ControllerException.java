package ro.cti.ssa.twittboost.exception;

/**
 * @author adrian.zamfirescu
 * @since 23/01/2015
 */
public class ControllerException extends Exception{

    private String message;

    public ControllerException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
