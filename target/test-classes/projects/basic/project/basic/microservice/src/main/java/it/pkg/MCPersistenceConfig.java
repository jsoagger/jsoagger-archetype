/**
 *
 */
package it.pkg;

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

/**
 *
 */
@Configuration
public class MCPersistenceConfig extends JpaBaseConfiguration {

  protected MCPersistenceConfig(DataSource dataSource, JpaProperties properties,
      ObjectProvider<JtaTransactionManager> jtaTransactionManager,
      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
  }

  @Override
  @PersistenceContext(unitName = "it.pkg-persistence-unit")
  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
    return builder.dataSource(getDataSource())
        .persistenceUnit("it.pkg-persistence-unit")
        .properties(getVendorProperties())
        .jta(true)
        .packages("io.github.jsoagger.core.model","it.pkg.model")
        .build();
  }

  @Override
  protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true);
    return vendorAdapter;
  }

  @Override
  protected Map<String, Object> getVendorProperties() {
    final Map<String, Object> ret = new HashMap<>();
    //ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
    // ret.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
    return ret;
  }
}