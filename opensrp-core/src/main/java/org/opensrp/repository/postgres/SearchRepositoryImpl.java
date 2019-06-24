package org.opensrp.repository.postgres;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.opensrp.domain.Client;
import org.opensrp.repository.SearchRepository;
import org.opensrp.repository.postgres.mapper.custom.CustomClientMetadataMapper;
import org.opensrp.search.AddressSearchBean;
import org.opensrp.search.ClientSearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("searchRepositoryPostgres")
public class SearchRepositoryImpl implements SearchRepository {
	
	@Autowired
	private CustomClientMetadataMapper clientMetadataMapper;
	
	@Autowired
	private ClientsRepositoryImpl clientsRepository;
	
	@Override
	public List<Client> findByCriteria (ClientSearchBean clientSearchBean, String nameLike, String firstName,
										String middleName, String lastName, String gender, Map<String, String> identifiers,
										Map<String, String> attributes, DateTime birthdateFrom, DateTime birthdateTo,
										DateTime lastEditFrom, DateTime lastEditTo, Integer limit) {
		clientSearchBean.setFirstName(firstName);
		clientSearchBean.setMiddleName(middleName);
		clientSearchBean.setLastName(lastName);
		List<org.opensrp.domain.postgres.Client> clients = clientMetadataMapper.selectBySearchBean(clientSearchBean,
		    new AddressSearchBean(), 0, limit == null ? BaseRepositoryImpl.DEFAULT_FETCH_SIZE : limit);
		return clientsRepository.convert(clients);
	}

}
