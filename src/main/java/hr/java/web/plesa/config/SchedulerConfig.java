//package hr.java.web.plesa.config;
//
//import hr.java.web.plesa.quartz.ExpenseStatisticQuartzJobBean;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SchedulerConfig {
//
//    @Bean
//    public JobDetail testJobDetail() {
//        return JobBuilder.newJob(ExpenseStatisticQuartzJobBean.class).withIdentity("testJob") .storeDurably().build();
//    }
//
//    @Bean
//    public SimpleTrigger testJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule() .withIntervalInSeconds(5).repeatForever();
//        return TriggerBuilder.newTrigger().forJob(testJobDetail())
//                .withIdentity("testTrigger")
//                .withSchedule(scheduleBuilder).build();
//    }
//    public CronTrigger testCronTrigger() {
//        CronTrigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("cronTrigger", "group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 15 10 ? * 5#1"))
//                .forJob("myJob", "group1")
//                .build();
//
//        return trigger;
//    }
//}
