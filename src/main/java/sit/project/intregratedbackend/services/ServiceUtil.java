package sit.project.intregratedbackend.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ServiceUtil {
	public static String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}

		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (principal == null) {
			return null;
		}

		UserDetails user = (UserDetails) principal;
		return user.getUsername();
	}
}
