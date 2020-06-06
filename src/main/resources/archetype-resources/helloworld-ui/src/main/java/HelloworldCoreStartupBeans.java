#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package};

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import io.github.jsoagger.core.server.batch.common.JobMappingFileResolver;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
@ImportResource({"classpath:/spring-config/context/ep-core-application-context.xml",
    "classpath:/spring-config/context/ep-core-batchjobs-context.xml",
    "classpath:/spring-config/helloworld-security-context.xml",
    "/spring-config/helloworld-startup-application-context.xml"})
public class HelloworldCoreStartupBeans {

  @Bean
  public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    config.setAllowedMethods(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }

  @Bean("JobMappingFileResolver")
  @Primary
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
  public Jaxb2Marshaller castorUnMarshaller2() {
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setPackagesToScan("io.github.jsoagger.core.server.batch.common.record", "${groupId}.core.server.batch.common.importer.util");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("jaxb.formatted.output", true);
    jaxb2Marshaller.setMarshallerProperties(map);
    return jaxb2Marshaller;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(5242880);
    return multipartResolver;
  }
}
