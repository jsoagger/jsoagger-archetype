#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package};

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.jsoagger.core.server.batch.common.JobMappingFileResolver;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author vonji
 *
 */
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
@ImportResource({"classpath:/spring-config/context/ep-common-application-context.xml",
  "classpath:/spring-config/context/ep-core-batchjobs-context.xml",
  "classpath:/spring-config/context/ep-core-security-context.xml",
"/spring-config/startup-application-context.xml"})
public class CoreStartupBeans {

  /**
   * Root context of the application if not customized
   *
   * @return
   */
  @Bean
  @ConditionalOnMissingBean(name = "applicationRootContext")
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>applicationRootContext() {
    return factory -> factory.setContextPath("/jsoagger");
  }


  @Bean("JobMappingFileResolver")
  @ConditionalOnMissingBean(name = "JobMappingFileResolver")
  public JobMappingFileResolver mappingFileResolver() {
    JobMappingFileResolver m = new JobMappingFileResolver();
    m.setUnmarshaller(castorUnMarshaller2());

    List<String> locations = new ArrayList<>();
    locations.add("classpath:/spring-config/jobs/job-mapping.xml");

    m.setMappingLocations(locations);
    m.loadMappingFiles();
    return m;
  }

  @Bean
  public CastorMarshaller castorUnMarshaller2() {
    CastorMarshaller m = new CastorMarshaller();
    Class clazz;
    try {
      clazz = Class.forName("io.github.jsoagger.core.server.batch.common.record.CSVInputFileDefinition");
      m.setTargetClass(clazz);

      Resource r = new ClassPathResource("classpath:/spring-config/castor-mapping.xml");
      m.setMappingLocation(r);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return m;
  }
  
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedHeaders("Autorization", "Cache-Control", "Content-Type",
            "Accept", "X-Requested-With", "Access-Control-Allow-Origin",
            "Access-Control-Allow-Headers", "Origin", "Cookie", "Set-Cookie")
        .exposedHeaders("Autorization", "Cache-Control", "Content-Type",
            "Accept", "X-Requested-With", "Access-Control-Allow-Origin",
            "Access-Control-Allow-Headers", "Origin", "Cookie", "Set-Cookie");
      }
    };
  }
}
