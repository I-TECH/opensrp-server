package org.opensrp.web.rest.shadow;

import org.opensrp.connector.openmrs.service.OpenmrsLocationService;
import org.opensrp.connector.openmrs.service.OpenmrsRelationshipService;
import org.opensrp.connector.openmrs.service.OpenmrsUserService;
import org.opensrp.service.OrganizationService;
import org.opensrp.service.PhysicalLocationService;
import org.opensrp.service.PractitionerService;
import org.opensrp.web.controller.UserController;
import org.opensrp.web.security.DrishtiAuthenticationProvider;

public class UserControllerShadow extends UserController {

	public UserControllerShadow(OpenmrsLocationService openmrsLocationService, OpenmrsUserService openmrsUserService,
								DrishtiAuthenticationProvider opensrpAuthenticationProvider, OpenmrsRelationshipService openmrsRelationshipService) {
		super(openmrsLocationService, openmrsUserService, opensrpAuthenticationProvider, openmrsRelationshipService);
	}

	public UserControllerShadow() {
		super(null, null, null, null);
	}

	@Override
	public void setPractitionerService(PractitionerService practitionerService) {
		super.setPractitionerService(practitionerService);
	}

	@Override
	public void setOrganizationService(OrganizationService organizationService) {
		super.setOrganizationService(organizationService);
	}

	@Override
	public void setLocationService(PhysicalLocationService locationService) {
		super.setLocationService(locationService);
	}
}
