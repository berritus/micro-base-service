package mis.berritus.cloud.uaa.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * 认证中心会提供User信息，所以也是资源服务器
 * 必须要的，不然会出现
 * "error": "invalid_token",
 * "error_description": "67d6a4b5-6360-4a73-8b2a-0cabc212f3ad"
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/noauth/**", "/sys/user/**").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        //注意属性文件里配置的server.servlet.context-path=/mis，
        //不要写成.antMatchers("/mis/noauth/*").permitAll()，不然会匹配不到
        //写成.antMatchers("/noauth/*").permitAll()即可，不需要加上/mis
    }
}
