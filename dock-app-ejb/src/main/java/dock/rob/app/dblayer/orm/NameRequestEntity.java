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
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

import dock.rob.app.dblayer.NameRequest;


/**
 * <code>NameRequest</code> is an entity class to the name request table.
 *
 * @author Rob Benton
 */
@Entity
@Table(name="namerequest", schema="rob")
@SequenceGenerator(name="IdGenerator", schema="rob")
@NamedQueries({
  @NamedQuery(name="allNameRequests", query="select n from NameRequestEntity n")
})
public class NameRequestEntity implements NameRequest
{
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator="IdGenerator")
  private BigDecimal id;
  
  private String name;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;
  
  /**
   */
  public NameRequestEntity() {}
  
  @Override
  public BigDecimal getId() { return this.id; }
  public void setId(BigDecimal id) { this.id = id; }
  
  @Override
  public String getName() { return this.name; }
  public void setName(String name) { this.name = name; }
  
  @Override
  public Date getUpdated() { return this.updated; }
  public void setUpdated(Date updated) { this.updated = updated; }
}
