#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.model;

import io.github.jsoagger.core.model.api.VLServiceEvent;
import io.github.jsoagger.core.model.persistable.Persistable;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class PreDoActionOnModelEvent extends VLServiceEvent {

  public PreDoActionOnModelEvent(Persistable subject) {
    super(subject);
  }

}
