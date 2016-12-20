package dock.rob.app.dblayer.orm;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * <code>NameRequest</code> is an entity class to the name request table.
 *
 * @author Rob Benton
 */
@Entity
@Table(name="namerequest", schema="rob")
@NamedQueries({
  @NamedQuery(name="allNameRequests", query="select n from NameRequest n")
})
public class NameRequest implements dock.rob.app.dblayer.NameRequest
{
  @Id
  private String name;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;
  
  private BigDecimal amount;
  
  /**
   */
  public NameRequest() {}
  
  @Override
  public String getName() { return this.name; }
  @Override
  public void setName(String name) { this.name = name; }
  
  @Override
  public Date getUpdated() { return this.updated; }
  @Override
  public void setUpdated(Date updated) { this.updated = updated; }
  
  @Override
  public BigDecimal getAmount() { return this.amount; }
  @Override
  public void setAmount(BigDecimal b) { this.amount = b; }
}
