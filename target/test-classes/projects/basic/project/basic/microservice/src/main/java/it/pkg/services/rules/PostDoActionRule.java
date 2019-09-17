/**
 *
 */
package it.pkg.services.rules;

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
    System.out.println("######## PostDoActionRule");
  }
}
