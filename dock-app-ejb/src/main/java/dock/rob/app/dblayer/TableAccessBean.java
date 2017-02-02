package dock.rob.app.dblayer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import dock.rob.app.dblayer.orm.NameRequestEntity;


/**
 * <code>TableAccessBean</code> is a simple EJB for accessing data objects.
 *
 * @author Rob Benton
 */
@Singleton
public class TableAccessBean
{
  /**
   * This collection holds a collection of data objects. It simulates a table
   * on a database.
   */
  private ArrayList<NameRequestEntity> nameRequests;
  
  /**
   * Inititalize
   */
  @PostConstruct
  private void init()
  {
    this.nameRequests = new ArrayList<NameRequestEntity>();
    this.nameRequests.add(new NameRequestEntity(new BigDecimal(1), "Tom", new Date()));
    this.nameRequests.add(new NameRequestEntity(new BigDecimal(2), "Dick", new Date()));
    this.nameRequests.add(new NameRequestEntity(new BigDecimal(3), "Harry", new Date()));
  }
  
  /**
   * Gets all records.
   *
   * @return a List of {@ NameRequest}s
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<NameRequest> allNameRequests()
  {
    return this.nameRequests;
  }
  
  /**
   * Updates the name on a {@ NameRequest}.
   *
   * <p>
   * The provided {@ NameRequest} is looked up by its id and then its name and
   * update timestamp are updated.
   *
   * @param id <code>BigDecimal</code> primary key of the {@ NameRequest}
   * @param name <code>String</code> new value for the name.
   * @return the updated {@ NameRequest}
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest updateName(BigDecimal id, String name)
  {
    NameRequestEntity nr = null;
    
    for (NameRequestEntity n : this.nameRequests)
    {
      if (n.getId().equals(id))
      {
        nr = n;
        nr.setName(name);
        nr.setUpdated(new Date());
        break;
      }
    }
    
    return nr;
  }
  
  /**
   * Gets a {@ NameRequest} by its name
   *
   * @param name <code>String</code>
   * @return the matching {@ NameRequest} or null if none was found.
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest getNameRequest(String name)
  {
    NameRequestEntity result = null;
    
    for (NameRequestEntity nr: this.nameRequests)
    {
      if (nr.getName().equals(name))
      {
        result = nr;
        break;
      }
    }
    
    return result;
  }
}
