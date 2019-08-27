package org.opensrp.repository;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.opensrp.domain.Client;
import org.opensrp.search.ClientSearchBean;

public interface SearchRepository {

	List<Client> findByCriteria(ClientSearchBean clientSearchBean, String nameLike, String firstName, String middleName, String lastName, String gender,
								Map<String, String> identifiers, Map<String, String> attributes,
								DateTime birthdateFrom, DateTime birthdateTo, DateTime lastEditFrom,
								DateTime lastEditTo, Integer limit);
}
