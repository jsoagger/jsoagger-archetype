#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package};

import java.io.File;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
@ShellComponent
@ShellCommandGroup(value = "Hello world command")
public class HelloWorldCommand {


  @ShellMethod("Say hello to world")
  public Integer helloWorld(
      @ShellOption(help="Your name")
      final String name) {
    try {
		System.out.println("Hello world, hello " +  name);
      return 1;
    }
    catch (Exception e) {
      e.printStackTrace();
      return -11;
    }
  }
}
