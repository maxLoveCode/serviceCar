package serviceCar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
		 @Autowired
		    private CustomAuthenticationProvider authProvider;
			
			@Autowired
		    private ClientDetailsService clientDetailsService;
			
		    @Autowired
		    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
				auth.authenticationProvider(authProvider);
		    }
		    
		    @Override
		    public void configure(WebSecurity web) throws Exception {
		        web.ignoring().antMatchers("/admin/**")
		        .antMatchers("/car/**")
		        .antMatchers("/carModel/**")
		        .antMatchers("/common/**")
		        .antMatchers("/company/**")
		        .antMatchers("/constTeam/**")
		        .antMatchers("/driver/**")
		        .antMatchers("/order/**")
		        .antMatchers("/role/**")
		        .antMatchers("/route/**");
		    }
		    
		    @Override
		    protected void configure(HttpSecurity http) throws Exception {
		        http
		        .csrf().disable()
		        .anonymous().disable()
		        .headers().frameOptions().sameOrigin().and()
		        .authorizeRequests()
		        .antMatchers("/oauth/token").permitAll();

		    }
		    
		    @Override
		    @Bean
		    public AuthenticationManager authenticationManagerBean() throws Exception {
		        return super.authenticationManagerBean();
		    }
		 
		 
		    @Bean
		    public TokenStore tokenStore() {
		        return new InMemoryTokenStore();
		    }
		 
		    @Bean
		    @Autowired
		    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		        handler.setTokenStore(tokenStore);
		        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		        handler.setClientDetailsService(clientDetailsService);
		        return handler;
		    }
		     
		    @Bean
		    @Autowired
		    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		        TokenApprovalStore store = new TokenApprovalStore();
		        store.setTokenStore(tokenStore);
		        return store;
		    }
   
}

