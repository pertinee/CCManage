package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.bean.SysDictInfoKey;
import com.lcz.manage.sys.dao.SysDictDao;
import com.lcz.manage.sys.dao.SysDictInfoDao;
import com.lcz.manage.sys.redis.SysDictRedis;
import com.lcz.manage.sys.service.SysDictService;
import com.lcz.manage.util.IdUtils;
import com.lcz.manage.util.exception.CCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictDao sysDictDao;

    @Autowired
    private SysDictInfoDao sysDictInfoDao;

    @Autowired
    private SysDictRedis sysDictRedis;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDict(SysDictBean dict) {
        String dictId = IdUtils.uuid();
        dict.setId(dictId);
        sysDictDao.saveDict(dict);
        if(!CollectionUtils.isEmpty(dict.getDictInfoList())){
            for(SysDictInfoBean eachInfo : dict.getDictInfoList()){
                eachInfo.setId(dictId);
            }
            sysDictInfoDao.saveBatchDictInfo(dict.getDictInfoList());
        }
        sysDictRedis.saveOrUpdate(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(SysDictBean dict) {
        sysDictDao.updateDict(dict);
        sysDictRedis.saveOrUpdate(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchDict(String[] ids) {
        for(String id : ids){
            SysDictBean dict = this.queryDict(id);
            sysDictRedis.delete(dict.getId());
        }
        //TODO 删除对应的数据字典详情


        sysDictDao.deleteBatchDict(ids);
    }

    @Override
    public SysDictBean queryDict(String id) {
        return sysDictDao.queryDict(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDictInfo(SysDictInfoBean dict) {
        sysDictInfoDao.saveDictInfo(dict);
        //更新原先数据字典
        SysDictBean dictBean = sysDictDao.queryDict(dict.getId());
        if(null == dictBean){
            throw new CCException("数据字典详情插入失败");
        }
        SysDictInfoBean sysDict = new SysDictInfoBean();
        sysDict.setId(dictBean.getId());
        List<SysDictInfoBean> infoList = sysDictInfoDao.queryDictInfoList(sysDict);
        if(!CollectionUtils.isEmpty(infoList)){
            dictBean.setDictInfoList(infoList);
        }
        sysDictRedis.saveOrUpdate(dictBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictInfo(SysDictInfoBean dict) {
        sysDictInfoDao.updateDictInfo(dict);
        //更新原先数据字典
        SysDictBean dictBean = sysDictDao.queryDict(dict.getId());
        if(null == dictBean){
            throw new CCException("数据字典详情更新失败");
        }
        SysDictInfoBean sysDict = new SysDictInfoBean();
        sysDict.setId(dictBean.getId());
        List<SysDictInfoBean> infoList = sysDictInfoDao.queryDictInfoList(sysDict);
        if(!CollectionUtils.isEmpty(infoList)){
            dictBean.setDictInfoList(infoList);
        }
        sysDictRedis.saveOrUpdate(dictBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchDictInfo(List<SysDictInfoKey> keys) {
        Set<String> delIds = new HashSet<>();
        for(SysDictInfoKey key : keys){
            sysDictInfoDao.deleteDictInfo(key);
            delIds.add(key.getId());
        }
        if(delIds.size() > 0){
            for(String id : delIds){
                //更新原先数据字典
                SysDictBean dictBean = sysDictDao.queryDict(id);
                if(null == dictBean){
                    throw new CCException("数据字典详情更新失败");
                }
                SysDictInfoBean sysDict = new SysDictInfoBean();
                sysDict.setId(dictBean.getId());
                List<SysDictInfoBean> infoList = sysDictInfoDao.queryDictInfoList(sysDict);
                if(!CollectionUtils.isEmpty(infoList)){
                    dictBean.setDictInfoList(infoList);
                }
                sysDictRedis.saveOrUpdate(dictBean);
            }
        }
    }

    @Override
    public List<SysDictInfoBean> queryDictInfoList(SysDictInfoBean sysDict) {
        return sysDictInfoDao.queryDictInfoList(sysDict);
    }
}
