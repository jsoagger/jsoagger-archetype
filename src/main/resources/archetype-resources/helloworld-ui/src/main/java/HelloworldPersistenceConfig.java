#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *
 */
package ${package};

import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class HelloworldPersistenceConfig extends JpaBaseConfiguration {

  private static final String IO_GITHUB_JSOAGGER_SOAGGYSHOP_PERSISTENCE_UNIT = "${groupId}.soaggyshop-persistence-unit";

  protected HelloworldPersistenceConfig(DataSource dataSource, JpaProperties properties,
      ObjectProvider<JtaTransactionManager> jtaTransactionManager,
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
  }

  @Override
  @PersistenceContext(unitName = IO_GITHUB_JSOAGGER_SOAGGYSHOP_PERSISTENCE_UNIT)
  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder.dataSource(getDataSource())
        .persistenceUnit(IO_GITHUB_JSOAGGER_SOAGGYSHOP_PERSISTENCE_UNIT)
        .properties(getVendorProperties())
        .jta(true)
        .packages("${groupId}.core.model",
            "${groupId}.soaggyshop.model",
            "${package}",
            "${groupId}.core.server.feed.model")
        .build();
  }

  @Override
  protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(false);
    return vendorAdapter;
  }

  @Override
  protected Map<String, Object> getVendorProperties() {
    final Map<String, Object> ret = new HashMap<>();
    return ret;
  }
}
