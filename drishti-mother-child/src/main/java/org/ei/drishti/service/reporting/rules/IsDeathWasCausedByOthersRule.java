package org.ei.drishti.service.reporting.rules;

import org.ei.drishti.util.SafeMap;
import org.springframework.stereotype.Component;

import static org.ei.drishti.common.AllConstants.ChildCloseFormFields.DEATH_CAUSE;
import static org.ei.drishti.common.AllConstants.ChildCloseFormFields.OTHERS_VALUE_LIST;

@Component
public class IsDeathWasCausedByOthersRule implements IRule {

    @Override
    public boolean apply(SafeMap reportFields) {
        return OTHERS_VALUE_LIST.contains(reportFields.get(DEATH_CAUSE));
    }
}
