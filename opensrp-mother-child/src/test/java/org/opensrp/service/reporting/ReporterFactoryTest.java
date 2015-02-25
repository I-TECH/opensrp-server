package org.opensrp.service.reporting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import org.opensrp.service.reporting.ChildReporter;
import org.opensrp.service.reporting.EligibleCoupleReporter;
import org.opensrp.service.reporting.IReporter;
import org.opensrp.service.reporting.IReporterFactory;
import org.opensrp.service.reporting.MotherReporter;
import org.opensrp.service.reporting.ReporterFactory;

public class ReporterFactoryTest {

    @Mock
    private ChildReporter childReporter;
    @Mock
    private EligibleCoupleReporter eligibleCoupleReporter;
    @Mock
    private MotherReporter motherReporter;

    private IReporterFactory reporterFactory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        reporterFactory = new ReporterFactory(eligibleCoupleReporter, motherReporter, childReporter);
    }

    @Test
    public void shouldReturnChildReporterWhenBindTypeIsChild() {
        IReporter reporter = reporterFactory.reporterFor("child");
        assertTrue(reporter instanceof ChildReporter);
    }

    @Test
    public void shouldReturnMotherReporterWhenBindTypeIsMother() {
        IReporter reporter = reporterFactory.reporterFor("mother");
        assertTrue(reporter instanceof MotherReporter);
    }

    @Test
    public void shouldReturnEligibleCoupleReporterWhenBindTypeIsEligibleCouple() {
        IReporter reporter = reporterFactory.reporterFor("eligible_couple");
        assertTrue(reporter instanceof EligibleCoupleReporter);
    }

    @Test
    public void shouldReturnNullWhenBindTypeIsNotMatched() {
        IReporter reporter = reporterFactory.reporterFor("NonExistentBindType");
        assertNull(reporter);
    }
}
