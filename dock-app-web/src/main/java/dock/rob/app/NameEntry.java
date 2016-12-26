package dock.rob.app;

import java.util.Date;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.ejb.EJB;

import javax.persistence.PersistenceException;

import javax.enterprise.context.Dependent;

import dock.rob.app.dblayer.NameRequest;
import dock.rob.app.dblayer.TableAccessBean;
import dock.rob.app.dblayer.UpdateException;


/**
 * <code>NameEntry</code> represents a name record on the database.
 *
 * @author Rob Benton
 */
@Name
@Dependent
public class NameEntry implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  private BigDecimal id;
  private String name;
  private Date updated;
  
  @EJB
  private TableAccessBean tableAccess;
  
  /**
   */
  public NameEntry() {}
  
  /**
   * @param id <code>BigDecimal</code>
   * @param name <code>String</code>
   * @param updated <code>Date</code>
   */
  protected void init(BigDecimal id, String name, Date updated)
  {
    this.id = id;
    this.name = name;
    this.updated = updated;
  }
  
  /**
   */
  public String getName() { return this.name; }
  
  /**
   * Update the name value on the database.
   *
   * @throws {@ UpdateException} if the name could not be updated.
   */
  public void setName(String name) throws UpdateException
  {
    NameRequest nr = this.tableAccess.updateName(this.id, name);
    this.updated = nr.getUpdated();
  }
  
  /**
   */
  public Date getUpdated() { return this.updated; }
}