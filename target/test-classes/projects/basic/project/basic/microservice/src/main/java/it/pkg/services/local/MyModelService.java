/**
 *
 */
package it.pkg.services.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.github.jsoagger.core.server.service.CoreServicesLocator;
import io.github.jsoagger.core.server.service.local.impl.DAOLocator;

import it.pkg.model.MyModel;
import it.pkg.model.PostDoActionOnModelEvent;
import it.pkg.model.PreDoActionOnModelEvent;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@Component("MyModelService")
public class MyModelService implements IMyModelService {

  @Autowired
  private DAOLocator dao;


  @Autowired
  private CoreServicesLocator services;

  @Autowired
  private ApplicationEventPublisher publisher;


  @Override
  @Transactional
  public void doAction(MyModel model) {
    publisher.publishEvent(new PreDoActionOnModelEvent(model));
    publisher.publishEvent(new PostDoActionOnModelEvent(model));
  }
}
