package dock.rob.app.dblayer;


/**
 * <code>UpdateException</code> is a checked exception thrown when an update
 * fails.
 *
 * @author Rob Benton
 */
public class UpdateException extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public UpdateException(String s) { super(s); }
}