package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.ScheduleJobBean;
import com.lcz.manage.sys.dao.ScheduleJobDao;
import com.lcz.manage.sys.enums.ScheduleStatus;
import com.lcz.manage.sys.service.ScheduleJobService;
import com.lcz.manage.util.schedule.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobDao schedulerJobDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobBean> scheduleJobList = schedulerJobDao.queryList(new HashMap<>());
		for(ScheduleJobBean scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public ScheduleJobBean queryObject(Long jobId) {
		return schedulerJobDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobBean> queryList(Map<String, Object> map) {
		return schedulerJobDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return schedulerJobDao.queryTotal(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ScheduleJobBean scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        schedulerJobDao.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(ScheduleJobBean scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        schedulerJobDao.update(scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	schedulerJobDao.deleteBatch(jobIds);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
    	return schedulerJobDao.updateBatch(map);
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, queryObject(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }
    
}
