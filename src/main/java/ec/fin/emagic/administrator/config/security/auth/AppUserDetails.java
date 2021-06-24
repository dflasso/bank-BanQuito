package ec.fin.emagic.administrator.config.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Detalles del usuario necesarios para la autenticación
 */
public class AppUserDetails  implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Debe recibir los parametros del usuario, según se modelo en la base
	 * @return Los detalles del usuario necesarios para la autenticación
	 */
	public static AppUserDetails build() {
		return new AppUserDetails();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
