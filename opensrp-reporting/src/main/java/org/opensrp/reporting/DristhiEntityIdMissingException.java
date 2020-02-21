package org.opensrp.reporting;

import java.text.MessageFormat;

import org.opensrp.common.domain.ReportDataDeleteRequest;

public class DristhiEntityIdMissingException extends Exception {
	
	public DristhiEntityIdMissingException(ReportDataDeleteRequest request) {
		super(MessageFormat.format("Cannot delete {0} report for the report data, as the DristhiEntityId is missing.",
		    request.type()));
	}
}
