package exceptions;

public class OnvoldoendeKredietException extends RuntimeException
{
    public OnvoldoendeKredietException()
    {
    }
    
    public OnvoldoendeKredietException(String message)
    {
        super(message);
    }

    public OnvoldoendeKredietException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public OnvoldoendeKredietException(Throwable cause)
    {
        super(cause);
    }

    public OnvoldoendeKredietException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
