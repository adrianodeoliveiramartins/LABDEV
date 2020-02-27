package br.edu.ifrs.canoas.lds.webapp.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrs.canoas.lds.webapp.service.UserDetailsImplService;
import lombok.AllArgsConstructor;

/**
 * Created by rodrigo on 2/22/17.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@AllArgsConstructor
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    UserDetailsImplService accountUserDetailsService;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/photos/**","/img/**", "/resources/**", "/public/**", "/dist/**", "/db/**",
				"/test/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            //Se especifica múltiplos padrões de URL autorizados para qualquer usuário autenticado
            //Independente da role, todos usuários tem acesso a essas requisições abaixo
            .antMatchers("/login**", "/dist/**", "/webjars**", "/db/**").permitAll()
            //Apenas usuários ROLE_ADMIN tem acesso ao subdomínio localhost:8080/admin
            .antMatchers("/admin/**").hasRole("ADMIN")
            //Qualquer URL que não foi previamente mapeada necessita que o usuário seja autenticado
            .anyRequest().authenticated()
            .and()
        .formLogin()
            //permite que qualquer um pode acessar o login
            .loginPage("/login").permitAll()
            .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true) //Invalidar a HttpSession durante o logout.
            .and()
        .csrf()
            //Se habilitado, gera no form de login um input type="hidden" name="_csrf"
            // com valor aleatório para verificação cliente-servidor
            .disable()
        ;
	}

}