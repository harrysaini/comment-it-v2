package com.harrry.comment_it.security

import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.filters.ExceptionHandlerFilter
import com.harrry.comment_it.config.JWTConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@EnableWebSecurity
@EnableConfigurationProperties(value = [JWTConfig::class])
class WebSecurityConfig(
        val bCryptPasswordEncoder: BCryptPasswordEncoder,
        val jwtConfig: JWTConfig,
        @Qualifier("userDetailsServiceImpl")
        val userDetailsService: UserDetailsService,
        val userJPARepository: UserJPARepository
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
                ?.authorizeRequests()
                ?.antMatchers(HttpMethod.POST, "/api/v1/auth/**")?.permitAll()
                ?.and()
                ?.authorizeRequests()
                ?.anyRequest()?.authenticated()
                ?.and()
                ?.addFilterBefore(JWTAuthorizationFilter(jwtConfig, userJPARepository), UsernamePasswordAuthenticationFilter::class.java)
                ?.addFilterBefore(ExceptionHandlerFilter(), CorsFilter::class.java)
                ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsConfSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }

}