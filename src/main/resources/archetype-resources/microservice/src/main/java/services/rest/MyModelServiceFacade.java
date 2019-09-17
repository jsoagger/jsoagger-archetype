#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package}.services.rest;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

import ${package}.model.MyModel;
import ${package}.services.local.IMyModelService;

/**
 * @author Ramilafananana VONJISOA
 */
@RestController
public class MyModelServiceFacade {

  @Autowired
  private IMyModelService service;

  /**
   * Ping by connected user
   */
  @RequestMapping(value = "/anon/mymodel", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Serializable mymodel() {
    IOperationResult result = new SingleResult();
    result.addMetaData("Ok", "Installation is OK");
    service.doAction(new MyModel());
    return result;
  }
}
