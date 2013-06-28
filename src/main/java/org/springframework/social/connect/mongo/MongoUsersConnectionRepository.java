/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.connect.mongo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import com.bbytes.socialdemo.User;

/**
 * {@link UsersConnectionRepository} that uses the JDBC API to persist
 * connection data to a relational database. The supporting schema is defined in
 * JdbcMultiUserConnectionRepository.sql.
 */
public class MongoUsersConnectionRepository implements UsersConnectionRepository {


	@Autowired
	private ConnectionService mongoService;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private TextEncryptor textEncryptor;

	
	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		List<String> localUserIds = mongoService.getUserIds(key.getProviderId(), key.getProviderUserId());

		// if user conn is not in mongo db then it means that the user is
		// signing in for the first time so we create an account along with
		// connection for the user in mongodb
		// to do - we need to indicate user that we are creating an account with
		// his social profile ..get an confirmation and then create the account.

		// When the user id is not there we redirect the user form
		// private RedirectView handleSignIn(Connection<?> connection,
		// NativeWebRequest request) in ProviderSignInController..this will
		// happen if we dont create the user and send the user id list from thsi
		// method..we configure a signup redirect for social login to show page with user info and jus create account button 
		try {
			if (localUserIds.size() == 0 && key.getProviderUserId() != null) {
				createConnectionRepository(key.getProviderUserId()).addConnection(connection);
				User user = new User(key.getProviderUserId());
				System.out.println("it worked ...."+user.getId());
				return Arrays.asList(key.getProviderUserId());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return localUserIds;
	}

	@Override
	public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {

		return mongoService.getUserIds(providerId, providerUserIds);
	}

	@Override
	public ConnectionRepository createConnectionRepository(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId cannot be null");
		}
		return new MongoConnectionRepository(userId, mongoService, connectionFactoryLocator, textEncryptor);
	}

}