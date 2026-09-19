/**
 * Interface for bank accounts that checks if the account can be closed
 */
public interface Closeable{
    /**
     * Determines if the account can be closed
     * @return true if account can be closed
     */
    boolean isCloseable();
}