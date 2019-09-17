/**
 *
 */
package it.pkg.model;

import io.github.jsoagger.core.model.api.VLServiceEvent;
import io.github.jsoagger.core.model.persistable.Persistable;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class PostDoActionOnModelEvent extends VLServiceEvent {

  public PostDoActionOnModelEvent(Persistable subject) {
    super(subject);
  }

}
