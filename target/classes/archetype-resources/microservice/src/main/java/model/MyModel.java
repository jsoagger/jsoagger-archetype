#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.github.jsoagger.core.model.persistable.BusinessObject;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@Entity
@Table(name = "MC_MODEL")
public class MyModel extends BusinessObject {


  private String modelName;


  @Override
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getOid() {
    return super._getOid();
  }

  /**
   *
   */
  public MyModel() {
    super();
  }

  @Override
  public Class getDomainClass() {
    return MyModel.class;
  }

  /**
   * @return
   */
  public String getModelName() {
    return modelName;
  }

  /**
   * @param modelName
   */
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }
}
