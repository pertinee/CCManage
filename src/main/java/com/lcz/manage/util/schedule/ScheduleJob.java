package com.lcz.manage.util.schedule;

import com.alibaba.fastjson.JSON;
import com.lcz.manage.sys.bean.ScheduleJobBean;
import com.lcz.manage.sys.bean.ScheduleJobLogBean;
import com.lcz.manage.sys.service.ScheduleJobLogService;
import com.lcz.manage.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;


/**
 * 定时任务
 *
 * @author luchunzhou
 */
public class ScheduleJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJob.class);
	//private ExecutorService service = Executors.newSingleThreadExecutor();
	/**
	 * 定时任务线程池改为手动创建
	 */
	private ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1,
			new BasicThreadFactory.Builder().namingPattern("cc-schedule-pool-%d").daemon(true).build());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String jsonJob = context.getMergedJobDataMap().getString(ScheduleJobBean.JOB_PARAM_KEY);
		ScheduleJobBean scheduleJob = JSON.parseObject(jsonJob, ScheduleJobBean.class);

		//获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLogBean log = new ScheduleJobLogBean();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

			future.get();

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);

			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);

			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(log);
		}
    }
}
