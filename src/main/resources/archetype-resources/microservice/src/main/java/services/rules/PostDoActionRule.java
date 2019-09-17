#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.services.rules;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import io.github.jsoagger.core.model.api.VLServiceEvent;
import io.github.jsoagger.core.server.service.local.IVetoableBusinessRule;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@Component(value ="PostDoActionRule")
@Transactional
public class PostDoActionRule implements IVetoableBusinessRule{

  /**
   *
   */
  @Override
  public void apply(VLServiceEvent event) throws Exception {
    System.out.println("${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound} PostDoActionRule");
  }
}
