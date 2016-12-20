package dock.rob.app.dblayer;

import java.util.Date;

import java.math.BigDecimal;


/**
 * <code>NameRequest</code> is an interface describing the model for a record
 * from the namerequest table.
 *
 * @author Rob Benton
 */
public interface NameRequest
{
  public String getName();
  
  public Date getUpdated();
  
  public BigDecimal getAmount();
}