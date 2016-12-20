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
  public void setName(String name);
  
  public Date getUpdated();
  public void setUpdated(Date updated);
  
  public BigDecimal getAmount();
  public void setAmount(BigDecimal b);
}