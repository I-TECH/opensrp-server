package org.opensrp.reporting.repository;

import static org.opensrp.reporting.domain.Indicator.FIND_BY_INDICATOR;

import java.util.List;

import org.opensrp.reporting.domain.Indicator;
import org.opensrp.reporting.repository.cache.IndicatorCacheableRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AllIndicatorsRepository implements IndicatorCacheableRepository {
	
	private DataAccessTemplate dataAccessTemplate;
	
	protected AllIndicatorsRepository() {
	}
	
	public AllIndicatorsRepository(DataAccessTemplate dataAccessTemplate) {
		this.dataAccessTemplate = dataAccessTemplate;
	}
	
	@Override
	public Indicator fetch(Indicator objectWhichShouldBeFilledWithMoreInformation) {
		return (Indicator) dataAccessTemplate.getUniqueResult(FIND_BY_INDICATOR, new String[] { "indicator" },
		    new Object[] { objectWhichShouldBeFilledWithMoreInformation.indicator() });
	}
	
	@Override
	public List<Indicator> fetchAll() {
		return dataAccessTemplate.loadAll(Indicator.class);
	}
}
