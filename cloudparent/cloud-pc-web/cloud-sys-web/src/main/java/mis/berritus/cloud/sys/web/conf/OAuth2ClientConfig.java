package mis.berritus.cloud.sys.web.conf;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * OAuth2客户端
 */
//@Configuration
//@EnableOAuth2Client
//@EnableConfigurationProperties
public class OAuth2ClientConfig {

    //@Bean
    //@ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails(){
        return new ClientCredentialsResourceDetails();
    }

    //@Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    }

    //@Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate(){
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
