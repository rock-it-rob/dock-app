package dock.rob.app.dblayer.orm;

import java.math.BigDecimal;

import java.util.Date;

import dock.rob.app.dblayer.NameRequest;


/**
 * <code>NameRequest</code> is an data class.
 *
 * @author Rob Benton
 */
public class NameRequestEntity implements NameRequest
{
  private BigDecimal id; // primary key
  private String name;
  private Date updated;
  
  /**
   */
  public NameRequestEntity(BigDecimal id, String name, Date updated)
  {
    this.id = id;
    this.name = name;
    this.updated = updated;
  }
  
  @Override
  public BigDecimal getId() { return this.id; }
  public void setId(BigDecimal id) { this.id = id; }
  
  @Override
  public String getName() { return this.name; }
  public void setName(String name) { this.name = name; }
  
  @Override
  public Date getUpdated() { return this.updated; }
  public void setUpdated(Date date) { this.updated = date; }
}
