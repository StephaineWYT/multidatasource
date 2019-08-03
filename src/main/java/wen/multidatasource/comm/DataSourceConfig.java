package wen.multidatasource.comm;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //用户数据源
    @Bean(name = "userDataSource")
    @Qualifier(value = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    //书籍数据源
    @Bean(name = "bookDataSource")
    @Primary
    @Qualifier(value = "bookDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.book")
    public DataSource bookDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
