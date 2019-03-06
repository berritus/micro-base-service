package mis.berritus.cloud.uaa.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    //@Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private JdbcTokenStore tokenStore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApprovalStore approvalStore;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private RedisTokenStore redisTokenStore;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("myClientDetailsService")
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //这个地方指的是从jdbc查出数据来存储
        //clients.withClientDetails(clientDetailsService);

        //在数据库中
        clients.withClientDetails(clientDetailsService);
        //在内存中
//        clients
//                //.jdbc(dataSource)// oauth_client_details
//                //.and()
//                .inMemory()//inMemory是存储到内存中 并未到数据库
//                //.withClient("browser")
//                //.authorizedGrantTypes("refresh_token", "password")
//                //.scopes("ui")
//                //.and()
//                .withClient("product-server")
//                //.resourceIds("*")
//                .autoApprove(true)
//                .secret(passwordEncoder.encode("q123456"))//客户端的密码
//                .authorizedGrantTypes("client_credentials", "refresh_token", "password")//认证类型
//                .scopes("server");//客户端的域
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)//数据库保存token oauth_access_token & oauth_refresh_token
                //.tokenStore(redisTokenStore)//redis存储
                .approvalStore(approvalStore)// oauth_approvals
                .authorizationCodeServices(authorizationCodeServices)// oauth_code
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        tokenServices.setSupportRefreshToken(false);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1));
//        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")//开启/oauth/token_key验证端口无权限访问
                .checkTokenAccess("isAuthenticated()");// 开启/oauth/check_token验证端口认证权限访问
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        //tokenServices.setTokenStore(redisTokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(60 * 60);
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24);
        return tokenServices;
    }
}
