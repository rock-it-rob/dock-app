package dock.rob.app.dblayer;


/**
 * <code>UpdateException</code> is thrown when a record cannot be successfully
 * updated.
 *
 * @author Rob Benton
 */
public class UpdateException extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public UpdateException(String s) { super(s); }
  public UpdateException(Throwable t) { super(t); }
}