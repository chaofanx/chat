package xyz.chaofan.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

@Configuration
public class AppConfig {
  @Bean(value = "db1", typed = true)
  public DataSource db1(@Inject("${demo.db1}") HikariDataSource ds) {
    return ds;
  }
}
