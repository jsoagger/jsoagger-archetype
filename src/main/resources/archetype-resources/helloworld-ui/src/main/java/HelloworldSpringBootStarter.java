#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package};

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({HelloworldCoreStartupBeans.class, HelloworldPersistenceConfig.class})
@ComponentScan({"${groupId}.soaggyshop", "${package}"})
public class HelloworldSpringBootStarter {

  public static void main(String[] args) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    new SpringApplicationBuilder(HelloworldSpringBootStarter.class)
        .logStartupInfo(false)
        .run(args);
  }
}
