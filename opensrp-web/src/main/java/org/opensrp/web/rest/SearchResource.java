package org.opensrp.web.rest;

import static org.opensrp.common.AllConstants.BaseEntity.LAST_UPDATE;
import static org.opensrp.common.AllConstants.Client.BIRTH_DATE;
import static org.opensrp.common.AllConstants.Client.FIRST_NAME;
import static org.opensrp.common.AllConstants.Client.GENDER;
import static org.opensrp.common.AllConstants.Client.LAST_NAME;
import static org.opensrp.common.AllConstants.Client.MIDDLE_NAME;
import static org.opensrp.web.rest.RestUtils.getDateRangeFilter;
import static org.opensrp.web.rest.RestUtils.getIntegerFilter;
import static org.opensrp.web.rest.RestUtils.getStringFilter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.opensrp.common.AllConstants.BaseEntity;
import org.opensrp.domain.Client;
import org.opensrp.domain.Event;
import org.opensrp.service.ClientService;
import org.opensrp.service.EventService;
import org.opensrp.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping(value = "/rest/search")
public class SearchResource extends RestResource<Client> {

    private static Logger logger = LoggerFactory.getLogger(SearchResource.class.toString());

    private SearchService searchService;

    private ClientService clientService;

    private EventService eventService;

    @Autowired
    public SearchResource(SearchService searchService, ClientService clientService, EventService eventService) {
        this.searchService = searchService;
        this.clientService = clientService;
        this.eventService = eventService;
    }

    @Override
    public List<Client> search(HttpServletRequest request) throws ParseException {//TODO search should not call different url but only add params
        String firstName = getStringFilter(FIRST_NAME, request);
        String middleName = getStringFilter(MIDDLE_NAME, request);
        String lastName = getStringFilter(LAST_NAME, request);

        String nameLike = getStringFilter("name", request);

        String gender = getStringFilter(GENDER, request);
        DateTime[] birthdate = getDateRangeFilter(BIRTH_DATE, request);//TODO add ranges like fhir do http://hl7.org/fhir/search.html
        DateTime[] lastEdit = getDateRangeFilter(LAST_UPDATE, request);//TODO client by provider id
        //TODO lookinto Swagger https://slack-files.com/files-pri-safe/T0EPSEJE9-F0TBD0N77/integratingswagger.pdf?c=1458211183-179d2bfd2e974585c5038fba15a86bf83097810a

        Map<String, String> attributeMap = null;
        String attributes = getStringFilter("attribute", request);
        if (!StringUtils.isEmptyOrWhitespaceOnly(attributes)) {
            String attributeType = StringUtils.isEmptyOrWhitespaceOnly(attributes) ? null : attributes.split(":", -1)[0];
            String attributeValue = StringUtils.isEmptyOrWhitespaceOnly(attributes) ? null : attributes.split(":", -1)[1];

            attributeMap = new HashMap<String, String>();
            attributeMap.put(attributeType, attributeValue);
        }

        return searchService.searchClient(nameLike, firstName, middleName, lastName, gender, null, attributeMap,
                birthdate == null ? null : birthdate[0], birthdate == null ? null : birthdate[1], lastEdit == null ? null
                        : lastEdit[0], lastEdit == null ? null : lastEdit[1], null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/path")
    @ResponseBody
    private List<ChildRelationsGroup> searchPathBy(HttpServletRequest request) throws ParseException {
        List<ChildRelationsGroup> childMotherList = new ArrayList<ChildRelationsGroup>();

        try {
            String ZEIR_ID = "zeir_id";

            String FIRST_NAME = "first_name";
            String MIDDLE_NAME = "middle_name";
            String LAST_NAME = "last_name";
            String BIRTH_DATE = "birth_date";

            String INACTIVE = "inactive";
            String LOST_TO_FOLLOW_UP = "lost_to_follow_up";

            Integer limit = getIntegerFilter("limit", request);
            if (limit == null || limit.intValue() == 0) {
                limit = 100;
            }

            String zeirId = getStringFilter(ZEIR_ID, request);
            String firstName = getStringFilter(FIRST_NAME, request);
            String middleName = getStringFilter(MIDDLE_NAME, request);
            String lastName = getStringFilter(LAST_NAME, request);
            String gender = getStringFilter(GENDER, request);
            String inActive = getStringFilter(INACTIVE, request);
            String lostToFollowUp = getStringFilter(LOST_TO_FOLLOW_UP, request);

            DateTime[] birthdate = getDateRangeFilter(BIRTH_DATE, request);//TODO add ranges like fhir do http://hl7.org/fhir/search.html
            DateTime[] lastEdit = getDateRangeFilter(LAST_UPDATE, request);//TODO client by provider id
            //TODO lookinto Swagger https://slack-files.com/files-pri-safe/T0EPSEJE9-F0TBD0N77/integratingswagger.pdf?c=1458211183-179d2bfd2e974585c5038fba15a86bf83097810a

            String OPENMRS_ID_KEY = "OPENMRS_ID";
            Map<String, String> identifiers = new HashMap<String, String>();
            if (!StringUtils.isEmptyOrWhitespaceOnly(zeirId)) {
                zeirId = formatChildUniqueId(zeirId);
                identifiers.put(OPENMRS_ID_KEY, zeirId);
            }

            Map<String, String> attributes = new HashMap<String, String>();
            if (!StringUtils.isEmptyOrWhitespaceOnly(inActive) || !StringUtils.isEmptyOrWhitespaceOnly(lostToFollowUp)) {

                if (!StringUtils.isEmptyOrWhitespaceOnly(inActive)) {
                    attributes.put(INACTIVE, inActive);
                }

                if (!StringUtils.isEmptyOrWhitespaceOnly(lostToFollowUp)) {
                    attributes.put(LOST_TO_FOLLOW_UP, lostToFollowUp);
                }
            }

            List<Client> children = new ArrayList<Client>();

            if (!StringUtils.isEmptyOrWhitespaceOnly(firstName) || !StringUtils.isEmptyOrWhitespaceOnly(middleName)
                    || !StringUtils.isEmptyOrWhitespaceOnly(lastName) || !StringUtils.isEmptyOrWhitespaceOnly(gender)
                    || !identifiers.isEmpty() || !attributes.isEmpty() || birthdate != null || lastEdit != null) {

                children = searchService.searchClient(null, firstName, middleName, lastName, gender, identifiers,
                        attributes, birthdate == null ? null : birthdate[0], birthdate == null ? null : birthdate[1],
                        lastEdit == null ? null : lastEdit[0], lastEdit == null ? null : lastEdit[1], limit);

            }

            // Mother
            String MOTHER_GUARDIAN_FIRST_NAME = "mother_first_name";
            String MOTHER_GUARDIAN_LAST_NAME = "mother_last_name";
            String MOTHER_GUARDIAN_NRC_NUMBER = "mother_nrc_number";
            String MOTHER_GUARDIAN_PHONE_NUMBER = "mother_contact_phone_number";

            String motherFirstName = getStringFilter(MOTHER_GUARDIAN_FIRST_NAME, request);
            String motherLastName = getStringFilter(MOTHER_GUARDIAN_LAST_NAME, request);
            String motherGuardianNrc = getStringFilter(MOTHER_GUARDIAN_NRC_NUMBER, request);
            String motherGuardianPhoneNumber = getStringFilter(MOTHER_GUARDIAN_PHONE_NUMBER, request);

            String NRC_NUMBER_KEY = "NRC_Number";
            Map<String, String> motherAttributes = new HashMap<String, String>();
            if (!StringUtils.isEmptyOrWhitespaceOnly(motherGuardianNrc)) {
                motherAttributes.put(NRC_NUMBER_KEY, motherGuardianNrc);
            }

            List<Client> mothers = new ArrayList<Client>();
            if (!StringUtils.isEmptyOrWhitespaceOnly(motherFirstName)
                    || !StringUtils.isEmptyOrWhitespaceOnly(motherLastName) || !motherAttributes.isEmpty()) {

                String nameLike = null;
                if ((!StringUtils.isEmptyOrWhitespaceOnly(motherFirstName) && !StringUtils
                        .isEmptyOrWhitespaceOnly(motherLastName)) && motherFirstName.equals(motherLastName)) {
                    if (org.apache.commons.lang3.StringUtils.containsWhitespace(motherFirstName.trim())) {
                        String[] arr = motherFirstName.split("\\s+");
                        motherFirstName = arr[0];
                        motherLastName = arr[1];
                    } else {
                        nameLike = motherFirstName;
                        motherFirstName = null;
                        motherLastName = null;
                    }
                }
                mothers = searchService.searchClient(nameLike, motherFirstName, null, motherLastName, null, null,
                        motherAttributes, null, null, lastEdit == null ? null : lastEdit[0], lastEdit == null ? null
                                : lastEdit[1], limit);

            }

            List<Client> eventChildren = new ArrayList<Client>();
            if (!StringUtils.isEmptyOrWhitespaceOnly(motherGuardianPhoneNumber)) {
                List<Event> events = eventService.findEventsByConceptAndValue("159635AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        motherGuardianPhoneNumber);
                if (events != null && !events.isEmpty()) {
                    List<String> clientIds = new ArrayList<String>();
                    for (Event event : events) {
                        String entityId = event.getBaseEntityId();
                        if (entityId != null && !clientIds.contains(entityId)) {
                            clientIds.add(entityId);
                        }
                    }

                    eventChildren = clientService.findByFieldValue(BaseEntity.BASE_ENTITY_ID, clientIds);

                }
            }
            // Search conjunction is "AND" find intersection
            children = intersection(children, eventChildren);

            String MOTHER_RELATIONSHIP_KEY = "mother";
            String GUARDIAN_RELATIONSHIP_KEY = "guardian";

            List<Client> linkedMothers = getLinkedMothers(children, MOTHER_RELATIONSHIP_KEY);
            linkedMothers.addAll(getLinkedMothers(children, GUARDIAN_RELATIONSHIP_KEY));

            List<Client> linkedChildren = getLinkedChildren(mothers, OPENMRS_ID_KEY, MOTHER_RELATIONSHIP_KEY);
            linkedChildren.addAll(getLinkedChildren(mothers, OPENMRS_ID_KEY, GUARDIAN_RELATIONSHIP_KEY));

            // Search conjunction is "AND" find intersection
            children = intersection(children, linkedChildren);

            for (Client linkedMother : linkedMothers) {
                if (!contains(mothers, linkedMother)) {
                    mothers.add(linkedMother);
                }
            }

            for (Client child : children) {
                ChildRelationsGroup crg = null;
                for (Client c : mothers) {
                    String relationalId = getRelationalId(child, MOTHER_RELATIONSHIP_KEY);
                    String motherEntityId = c.getBaseEntityId();
                    if (relationalId != null && motherEntityId != null && relationalId.equalsIgnoreCase(motherEntityId)) {
                        if(crg == null){
                            crg = new ChildRelationsGroup(child, c, true);
                        } else {
                            crg.setMother(c);
                        }
                    }
                    relationalId = getRelationalId(child, GUARDIAN_RELATIONSHIP_KEY);
                    if (relationalId != null && motherEntityId != null && relationalId.equalsIgnoreCase(motherEntityId)) {
                        if(crg == null){
                            crg = new ChildRelationsGroup(child, c, false);
                        } else {
                            crg.setGuardian(c);
                        }
                    }
                }
                if(crg != null) {
                    childMotherList.add(crg);
                }
            }

        } catch (Exception e) {
            logger.error("", e);
        }

        return childMotherList;
    }

    private List<Client> getLinkedMothers(List<Client> children, String relationshipKey) {
        List<Client> linkedMothers = new ArrayList<Client>();

        if (!children.isEmpty()) {
            List<String> clientIds = new ArrayList<String>();
            for (Client c : children) {
                String relationshipId = getRelationalId(c, relationshipKey);
                if (relationshipId != null && !clientIds.contains(relationshipId)) {
                    clientIds.add(relationshipId);
                }
            }

            linkedMothers = clientService.findByFieldValue(BaseEntity.BASE_ENTITY_ID, clientIds);
        }

        return linkedMothers;
    }

    private List<Client> getLinkedChildren(List<Client> mothers, String openmrsIdKey, String relationshipKey) {
        List<Client> linkedChildren = new ArrayList<>();

        String M_KIP_ID = "M_KIP_ID";
        if (!mothers.isEmpty()) {
            List<String> cIndentifers = new ArrayList<>();
            for (Client m : mothers) {
                String childIdentifier = getChildIndentifier(m, M_KIP_ID, relationshipKey);
                if (childIdentifier != null && !cIndentifers.contains(childIdentifier)) {
                    cIndentifers.add(childIdentifier);
                }
            }

            linkedChildren = clientService.findByFieldValuex(openmrsIdKey, cIndentifers);
        }

        return linkedChildren;
    }

    @Override
    public List<Client> filter(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client getByUniqueId(String uniqueId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> requiredProperties() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client create(Client entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Client update(Client entity) {
        // TODO Auto-generated method stub
        return null;
    }

    private String getRelationalId(Client c, String relationshipKey) {
        Map<String, Map<String, String>> relationships = c.getRelationships();
        if (relationships != null) {
            for (Map.Entry<String, Map<String, String>> entry : relationships.entrySet()) {
                String key = entry.getKey();
                if (key.equalsIgnoreCase(relationshipKey)) {
                    Map<String, String> rMap = entry.getValue();
                    if (!rMap.isEmpty()) {
                        return rMap.get("relativeEntityId");
                    }
                }
            }
        }

        return null;
    }

    private String getChildIndentifier(Client m, String motherIdentifier, String relationshipKey) {
        String identifier = m.getIdentifier(motherIdentifier);
        if (!StringUtils.isEmptyOrWhitespaceOnly(identifier)) {
            String[] arr = identifier.split("_");
            if (arr != null && arr.length == 2 && arr[1].contains(relationshipKey)) {
                return arr[0];
            }
        }
        return null;
    }

    private class ChildRelationsGroup {

        private Client child;

        private Client mother;

        private Client guardian;

        public ChildRelationsGroup(Client child, Client mother, Client guardian) {
            this.child = child;
            this.mother = mother;
            this.guardian = guardian;
        }

        public ChildRelationsGroup(Client child, Client c, boolean isMother) {
            this.child = child;
            if (isMother) {
                this.mother = c;
            } else {
                this.guardian = c;
            }
        }

        @SuppressWarnings("unused")
        public Client getMother() {
            return mother;
        }

        @SuppressWarnings("unused")
        public Client getChild() {
            return child;
        }

        @SuppressWarnings("unused")
        public Client getGuardian() {
            return guardian;
        }

        public void setChild(Client child) {
            this.child = child;
        }

        public void setMother(Client mother) {
            this.mother = mother;
        }

        public void setGuardian(Client guardian) {
            this.guardian = guardian;
        }
    }

    private static String formatChildUniqueId(String unformattedId) {
        if (!StringUtils.isEmptyOrWhitespaceOnly(unformattedId)) {
            if (unformattedId.contains("-")) {
                unformattedId = unformattedId.split("-")[0];
            } else if (unformattedId.length() > 6) {
                unformattedId = unformattedId.substring(0, 6);
            }
        }

        return unformattedId;
    }

    private boolean contains(List<Client> clients, Client c) {
        if (clients == null || clients.isEmpty() || c == null) {
            return false;
        }
        for (Client client : clients) {
            if (client != null && client.getBaseEntityId() != null && c.getBaseEntityId() != null) {
                if (client.getBaseEntityId().equals(c.getBaseEntityId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Client> intersection(List<Client> list1, List<Client> list2) {
        if (list1 == null) {
            list1 = new ArrayList<Client>();
        }

        if (list2 == null) {
            list2 = new ArrayList<Client>();
        }

        if (list1.isEmpty() && list2.isEmpty()) {
            return new ArrayList<Client>();
        }

        if (list1.isEmpty() && !list2.isEmpty()) {
            return list2;
        }

        if (list2.isEmpty() && !list1.isEmpty()) {
            return list1;
        }

        List<Client> list = new ArrayList<Client>();

        for (Client t : list1) {
            if (contains(list2, t)) {
                list.add(t);
            }
        }

        return list;
    }
}
