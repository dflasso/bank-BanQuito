package ec.fin.emagic.administrator.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro que debe validar el token en solicitudes que necesitan autorización  
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtConfig jwtConfig;
	
    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {

	        this.jwtConfig = jwtConfig;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        // ir al siguiente filtro
		filterChain.doFilter(request, response);

		
	}

}
