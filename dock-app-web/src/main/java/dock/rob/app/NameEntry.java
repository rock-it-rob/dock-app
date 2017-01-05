package dock.rob.app;

import java.util.Date;

import java.math.BigDecimal;

import dock.rob.app.dblayer.NameRequest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessOrder;


/**
 * <code>NameEntry</code> is a JAXB class containing a {@ NameRequest}.
 *
 * @author Rob Benton
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class NameEntry
{
  private BigDecimal id;
  private String name;
  private Date updated;
  
  /**
   */
  public NameEntry() {}
  
  /**
   * @param nameRequest {@ NameRequest}
   */
  public NameEntry(NameRequest nameRequest)
  {
    this.id = nameRequest.getId();
    this.name = nameRequest.getName();
    this.updated = nameRequest.getUpdated();
  }
  
  public BigDecimal getId() { return this.id; }
  public void setId(BigDecimal id) { this.id = id; }
  
  public String getName() { return this.name; }
  public void setName(String name) { this.name = name; }
  
  public Date getUpdated() { return this.updated; }
  public void setUpdated(Date d) { this.updated = d; }
}
