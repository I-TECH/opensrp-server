package org.opensrp.repository.couch;

import java.util.ArrayList;
import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import org.motechproject.dao.MotechBaseRepository;
import org.opensrp.common.AllConstants;
import org.opensrp.domain.Multimedia;
import org.opensrp.repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("couchMultimediaRepository")
@Primary
public class MultimediaRepositoryImpl extends MotechBaseRepository<Multimedia> implements MultimediaRepository {
	
	@Autowired
	protected MultimediaRepositoryImpl(@Qualifier(AllConstants.OPENSRP_DATABASE_CONNECTOR) CouchDbConnector db) {
		super(Multimedia.class, db);
	}
	
	@GenerateView
	public Multimedia findByCaseId(String entityId) {
		List<Multimedia> files = queryView("by_caseId", entityId);
		if (files == null || files.isEmpty()) {
			return null;
		}
		return files.get(0);
	}
	
	@View(name = "all_multimedia_files", map = "function(doc) { if (doc.type === 'Multimedia' && doc.providerId) { emit(doc.providerId, doc); } }")
	public List<Multimedia> all(String providerId) {
		return db.queryView(createQuery("all_multimedia_files").key(providerId).includeDocs(true), Multimedia.class);
	}

	@Override
	public List<Multimedia> get(String entityId, String contentType, String fileCategory) {
		// todo: placeholder, not supporting couchdb queries for new api endpoints
		return new ArrayList<>();
	}
}
