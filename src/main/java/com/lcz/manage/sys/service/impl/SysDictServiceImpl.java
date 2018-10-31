package com.lcz.manage.sys.service.impl;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictFront;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.bean.SysDictInfoKey;
import com.lcz.manage.sys.constants.CcConstants;
import com.lcz.manage.sys.dao.SysDictDao;
import com.lcz.manage.sys.dao.SysDictInfoDao;
import com.lcz.manage.sys.enums.AccessLevel;
import com.lcz.manage.sys.redis.SysDictRedis;
import com.lcz.manage.sys.service.SysDictService;
import com.lcz.manage.util.DictUtils;
import com.lcz.manage.util.IdUtils;
import com.lcz.manage.util.StringUtils;
import com.lcz.manage.util.exception.CCException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


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
    public List<SysDictBean> queryDictList(SysDictBean sysDict) {
        List<SysDictBean> dictList = sysDictDao.queryDictList(sysDict);
        if(!CollectionUtils.isEmpty(dictList)){
            for(SysDictBean eachDict : dictList){
                SysDictInfoBean qryDict = new SysDictInfoBean();
                qryDict.setId(eachDict.getId());
                List<SysDictInfoBean> infoList = sysDictInfoDao.queryDictInfoList(qryDict);
                if(!CollectionUtils.isEmpty(infoList)){
                    eachDict.setDictInfoList(infoList);
                }
                sysDictRedis.saveOrUpdate(eachDict);
            }
        }
        return dictList;
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

    @Override
    public List<SysDictFront> queryDictFrontList(Map<String, Object> map) {
        List<SysDictFront> list;
        list = sysDictDao.queryDictFrontList(map);
        if(!CollectionUtils.isEmpty(list)){
            for(SysDictFront eachDict : list){
                eachDict.setId(eachDict.getDictId() + "_" + eachDict.getDictValue());
                eachDict.setAccessLevelCn(StringUtils.isEmpty(eachDict.getAccessLevel()) ? "" : DictUtils.getDictPrompt(CcConstants.DICT_ID_5, eachDict.getAccessLevel()));
            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysDictDao.queryTotal(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] ids) {
        List<SysDictInfoKey> keys = new ArrayList<>();
        for(String each : ids){
            SysDictInfoKey key = new SysDictInfoKey();
            key.setId(each.split("_")[0]);
            key.setDictValue(each.split("_")[1]);
            keys.add(key);
        }
        deleteBatchDictInfo(keys);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDict(SysDictFront dictFront) {
        if(StringUtils.isEmpty(dictFront.getDictId())){
            // 新增数据字典和数据字典详情
            SysDictBean dict = this.executeDict(dictFront);
            this.saveDict(dict);
        }else{
            // 扩展数据字典详情
            SysDictInfoBean dictInfo = new SysDictInfoBean();
            BeanUtils.copyProperties(dictFront, dictInfo);
            dictInfo.setId(dictFront.getDictId());
            this.saveDictInfo(dictInfo);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(SysDictFront dictFront){
        // 删除原先的数据，再新增这一条数据，最后更新redis缓存数据
        if(!StringUtils.isEmpty(dictFront.getId()) && dictFront.getId().split("_").length == 2){
            SysDictInfoKey key = new SysDictInfoKey();
            String[] str = dictFront.getId().split("_");
            key.setId(str[0]);
            key.setDictValue(str[1]);
            // 判断原先的数据字典，可修改再修改
            SysDictInfoBean infoBean = this.sysDictInfoDao.queryDictInfo(key);
            if(!AccessLevel.EDITABLE.getCode().equals(infoBean.getAccessLevel())){
                throw new CCException("数据字典不可修改");
            }
            this.sysDictInfoDao.deleteDictInfo(key);
            SysDictInfoBean dict = new SysDictInfoBean();
            BeanUtils.copyProperties(dictFront, dict);
            dict.setId(dictFront.getDictId());
            sysDictInfoDao.saveDictInfo(dict);
            //更新redis
            SysDictBean dictBean = sysDictDao.queryDict(dict.getId());
            SysDictInfoBean sysDict = new SysDictInfoBean();
            sysDict.setId(dictBean.getId());
            List<SysDictInfoBean> infoList = sysDictInfoDao.queryDictInfoList(sysDict);
            if(!CollectionUtils.isEmpty(infoList)){
                dictBean.setDictInfoList(infoList);
            }
            sysDictRedis.saveOrUpdate(dictBean);
        }

    }

    /**
     * 封装数据字典和数据字典详情
     * @param dictFront
     * @return
     */
    private SysDictBean executeDict(SysDictFront dictFront){
        String id = IdUtils.uuid();
        SysDictBean dict = new SysDictBean();
        List<SysDictInfoBean> dictInfoList = new ArrayList<>();
        SysDictInfoBean infoBean = new SysDictInfoBean();
        dict.setId(id);
        dict.setDictName(dictFront.getDictName());
        BeanUtils.copyProperties(dictFront, infoBean);
        infoBean.setId(id);
        dictInfoList.add(infoBean);
        dict.setDictInfoList(dictInfoList);
        return dict;
    }
}
