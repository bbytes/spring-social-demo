package org.springframework.social.signin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Component;

import com.bbytes.socialdemo.User;

@Component
public class SignInUtils {

	/**
	 * Programmatically signs in the user with the given the user ID.
	 */
	public void signin(String userId, Connection<?> connection) {

		User user = null;
		if (connection.getKey().getProviderId().equals("facebook")) {
			user = new User(connection.fetchUserProfile().getEmail());
		} else if (connection.getKey().getProviderId().equals("google")) {
			Google google = (Google) connection.getApi();
			LegacyGoogleProfile profile = google.userOperations().getUserProfile();
			user = new User(profile.getEmail());
		}else if (connection.getKey().getProviderId().equals("github")) {
			user = new User(connection.fetchUserProfile().getEmail());
		}

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, null));
	}
}
