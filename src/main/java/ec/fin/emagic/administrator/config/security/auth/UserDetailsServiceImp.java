package ec.fin.emagic.administrator.config.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que permite cargar la información del usuario al momento de autenticarse
 */
@Service
public class UserDetailsServiceImp  implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Consulatr el usuario por username y pasar a la función build
		return AppUserDetails.build();
	}

}
