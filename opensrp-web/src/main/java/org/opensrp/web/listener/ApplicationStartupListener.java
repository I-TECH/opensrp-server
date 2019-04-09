package org.opensrp.web.listener;

import java.util.concurrent.TimeUnit;

import org.opensrp.common.AllConstants;
import org.opensrp.connector.dhis2.DHIS2DatasetPush;
import org.opensrp.connector.openmrs.constants.OpenmrsConstants;
import org.opensrp.scheduler.RepeatingCronSchedule;
import org.opensrp.scheduler.TaskSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    public static final String APPLICATION_ID = "/opensrp";
    public static final String APPLICATION_ID_FULL = "org.springframework.web.context.WebApplicationContext:"+APPLICATION_ID;

    private TaskSchedulerService scheduler;

    private RepeatingCronSchedule eventsSchedule;
    private RepeatingCronSchedule atomfeedSchedule;
    private RepeatingCronSchedule encounterSchedule;
    private RepeatingCronSchedule dhis2Schedule;
    private RepeatingCronSchedule validateSyncedToOMRS;


    @Autowired
    public ApplicationStartupListener(TaskSchedulerService scheduler,
                                      @Value("#{opensrp['form.poll.time.interval']}") int formPollInterval,
                                      @Value("#{opensrp['mcts.poll.time.interval.in.minutes']}") int mctsPollIntervalInHours,
                                      @Value("#{opensrp['openmrs.scheduletracker.syncer.interval-min']}") int openmrsSchSyncerMin) {
        this.scheduler = scheduler;
        eventsSchedule = new RepeatingCronSchedule(AllConstants.EVENTS_SCHEDULE_SUBJECT, 2, TimeUnit.MINUTES, "0 0/2 * * * ?");

        encounterSchedule = new RepeatingCronSchedule(OpenmrsConstants.SCHEDULER_OPENMRS_DATA_PUSH_SUBJECT, 5, TimeUnit.MINUTES, "0 0/5 * * * ?");
        atomfeedSchedule = new RepeatingCronSchedule(OpenmrsConstants.SCHEDULER_OPENMRS_ATOMFEED_SYNCER_SUBJECT, 8, TimeUnit.MINUTES, "0 0/5 * * * ?");
        dhis2Schedule = new RepeatingCronSchedule(DHIS2DatasetPush.SCHEDULER_DHIS2_DATA_PUSH_SUBJECT, 11, TimeUnit.MINUTES, "0 0/5 * * * ?");
        validateSyncedToOMRS = new RepeatingCronSchedule(OpenmrsConstants.SCHEDULER_OPENMRS_SYNC_VALIDATOR_SUBJECT, 15, TimeUnit.MINUTES, "0 0/10 * * * ?");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(contextRefreshedEvent.getApplicationContext().getId());
        if (contextRefreshedEvent.getApplicationContext().getId().endsWith(APPLICATION_ID)) {
            scheduler.startJob(eventsSchedule);
            scheduler.startJob(atomfeedSchedule);
            scheduler.startJob(encounterSchedule);
            scheduler.startJob(dhis2Schedule);
            scheduler.startJob(validateSyncedToOMRS);

            System.out.println("STARTED ALL SCHEDULES");
        }
    }
}
