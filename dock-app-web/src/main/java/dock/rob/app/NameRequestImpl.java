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
  private BigDecimal id;
  private String name;
  private Date updated;
  
  /**
   * Create a new instance from an existing implementation.
   */
  public NameRequestImpl(NameRequest nameRequest)
  {
    this.id = nameRequest.getId();
    this.name = nameRequest.getName();
    this.updated = nameRequest.getUpdated();
  }
  
  @Override
  public BigDecimal getId() { return this.id; }
  
  @Override
  public String getName() { return this.name; }
  public void setName(String name) { this.name = name; }
  
  @Override
  public Date getUpdated() { return this.updated; }
  public void setUpdated(Date d) { this.updated = d; }
}