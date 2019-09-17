#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.services.rules;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.jsoagger.core.model.api.VLServiceEvent;
import io.github.jsoagger.core.server.service.local.IVetoableBusinessRule;
import io.github.jsoagger.core.server.service.local.impl.DAOLocator;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@Component(value = "PreDoActionRule")
@Transactional
public class PreDoActionRule implements IVetoableBusinessRule{

  @Autowired
  DAOLocator dao;

  /**
   *
   */
  @Override
  public void apply(VLServiceEvent event) throws Exception {
    System.out.println("${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound} PreDoActionRule");
  }
}
