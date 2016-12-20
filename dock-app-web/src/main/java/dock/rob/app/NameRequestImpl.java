package dock.rob.app;

import java.util.Date;

import java.math.BigDecimal;

import dock.rob.app.dblayer.NameRequest;


/**
 * <code>NameRequestImpl</code> is an implementation of {@ NameRequest} for
 * transient use.
 *
 * @author Rob Benton
 */
public class NameRequestImpl implements NameRequest
{
  private String name;
  private Date updated;
  private BigDecimal amount;
  
  private String oldName;
  
  /**
   * Create a new instance from an existing implementation.
   */
  public NameRequestImpl(NameRequest nameRequest)
  {
    this.name = nameRequest.getName();
    this.updated = nameRequest.getUpdated();
    this.amount = nameRequest.getAmount();
  }
  
  @Override
  public String getName() { return this.name; }
  
  public void setName(String name)
  {
    this.oldName = this.name;
    this.name = name;
  }
  
  public String getOldName() { return this.oldName; }
  
  @Override
  public Date getUpdated() { return this.updated; }
  public void setUpdated(Date d) { this.updated = d; }
  
  @Override
  public BigDecimal getAmount() { return this.amount; }
  public void setAmount(BigDecimal b) { this.amount = b; }
}