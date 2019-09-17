#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.services.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.github.jsoagger.core.server.service.CoreServicesLocator;
import io.github.jsoagger.core.server.service.local.impl.DAOLocator;

import ${package}.model.MyModel;
import ${package}.model.PostDoActionOnModelEvent;
import ${package}.model.PreDoActionOnModelEvent;

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
