/**
 * @project: api
 * @Title: workGroupController.java
 * @Package: com.smartdatachain.api.web.controller
 * <p>
 * Copyright (c) 2014-2017 Jeeframework Limited, Inc.
 * All rights reserved.
 */
package com.smartdatachain.api.web.controller;

import com.alibaba.fastjson.JSON;
import com.jeeframework.util.validate.Validate;
import com.jeeframework.webframework.exception.SystemCode;
import com.jeeframework.webframework.exception.WebException;
import com.smartdatachain.api.biz.service.WorkGroupService;
import com.smartdatachain.api.integration.bo.WorkGroupBO;
import com.smartdatachain.api.integration.bo.WorkGroupDetailBO;
import com.smartdatachain.api.web.exception.MySystemCode;
import com.smartdatachain.api.web.po.*;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("workGroupController")
@Api(value = "首页任务组", description = "首页任务组相关接口", position = 2)
@RequestMapping("/workGroup")
public class WorkGroupController {

    @Resource
    private WorkGroupService workGroupService;

    @RequestMapping(value = "/getWorkGroup.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询任务组接口 ", notes = "", position = 0)
    public List<WorkGroupPO> getDataCrawlList(@RequestParam(value = "name", required = false) @ApiParam(value = "任务组名", required = false) String name,
                                              HttpServletRequest req, HttpServletResponse res) {

        Map<String, Object> param = new HashMap<>();
        if (name != null && !("").equals(name)) {
            param.put("name", name);
        }
        List<WorkGroupBO> workGroupBOS = workGroupService.getWorkGroupList(param);
        if (workGroupBOS.size() == 0) {
            throw new WebException(MySystemCode.BIZ_DATA_QUERY_EXCEPTION);
        }
        List<WorkGroupPO> workGroupPOList = new ArrayList<>();
        for (WorkGroupBO workGroupBO : workGroupBOS) {
            WorkGroupPO workGroupPOS = new WorkGroupPO();
            workGroupPOS.setId(workGroupBO.getId());
            workGroupPOS.setUser_id(workGroupBO.getUserId());
            workGroupPOS.setName(workGroupBO.getName());
            workGroupPOList.add(workGroupPOS);
        }
        return workGroupPOList;
    }

    @RequestMapping(value = "/addWorkGroup.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加任务组 ", notes = "", position = 0)
    public CommonPO addDataCrawl(@RequestParam(value = "name", required = true) @ApiParam(value = "任务组名", required = true) String name,
                                 @RequestParam(value = "userId", required = false) @ApiParam(value = "用户id", required = false) String userId,
                                 HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> param = new HashMap<>();
        if (!Validate.isEmpty(name)) {
            param.put("name", name);
        } else {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        if (userId != null && !("").equals(userId)) {
            param.put("userId", userId);
        }
        int addWorkGroupResultInt = workGroupService.addWorkGroup(param);
        if (addWorkGroupResultInt < 0) {
            throw new WebException(MySystemCode.ACTION_EXCEPTION);
        }
        CommonPO commonPO = new CommonPO();
        commonPO.setCode(0);
        return commonPO;
    }

    @RequestMapping(value = "/deleteWorkGroupById.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据id删除任务组 ", notes = "", position = 0)
    public CommonPO deleteWorkGroupById(@RequestParam(value = "id", required = true) @ApiParam(value = "任务组id", required = true) String id,
                                        @RequestParam(value = "isDelete", required = false) @ApiParam(value = "是否删除 1为删除，0为不删除，默认为 0", required = false) String isDelete,
                                        HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> param = new HashMap<>();
        if (id != null && !("").equals(id)) {
            param.put("collectionId", Integer.parseInt(id));
        } else {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        int collectionIdInt = Integer.parseInt(id);
        int count = workGroupService.getWorkGroupDetailCountByParam(null, collectionIdInt, null, null);
        // 如果查询到的任务组下面存在子任务，且isDelete=0，则不删除，并返回提示
        String isDeletes;
        if (isDelete == null || "".equals(isDelete)){
             isDeletes = "0";
        }else {
            isDeletes = isDelete;
        }
        if (count > 0 && ("0").equals(isDeletes)) {
            throw new WebException(MySystemCode.DELETE_EXCEPTION_VISPROJECT_ERROR);
        }
        int deleteWorkGroupById = workGroupService.deleteWorkGroupById(param);
        int deleteWorkGroupDetailById = workGroupService.deleteWorkGroupDetailById(param);
        if (deleteWorkGroupById < 0 && deleteWorkGroupDetailById < 0) {
            throw new WebException(MySystemCode.ACTION_EXCEPTION);
        }
        CommonPO commonPO = new CommonPO();
        commonPO.setCode(0);
        return commonPO;
    }

    @RequestMapping(value = "/updateWorkGroupById.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据id修改任务组名称 ", notes = "", position = 0)
    public CommonPO updateWorkGroupById(@RequestParam(value = "id", required = true) @ApiParam(value = "任务组id", required = true) String id,
                                        @RequestParam(value = "name", required = true) @ApiParam(value = "任务组名", required = true) String name,
                                        HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> param = new HashMap<>();
        if (id != null && !("").equals(id)) {
            param.put("id", Integer.parseInt(id));
        } else {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        if (name != null && !("").equals(name)) {
            param.put("name", name);
        } else {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        int updateWorkGroupById = workGroupService.updateWorkGroupById(param);
        if (updateWorkGroupById < 0) {
            throw new WebException(MySystemCode.ACTION_EXCEPTION);
        }
        CommonPO commonPO = new CommonPO();
        commonPO.setCode(0);
        return commonPO;
    }


    @RequestMapping(value = "/updateWorkGroupDetailById.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据id修改任务组下详细任务名称和分组 ", notes = "", position = 0)
    public CommonPO updateWorkGroupDetailById(@RequestParam(value = "appId", required = true) @ApiParam(value = "任务id", required = true) String appId,
                                              @RequestParam(value = "name", required = false) @ApiParam(value = "任务名", required = false) String name,
                                              @RequestParam(value = "collectionId", required = false) @ApiParam(value = "任务组id", required = false) String collectionId,
                                              HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> param = new HashMap<>();
        if (appId != null && !("").equals(appId)) {
            param.put("appId", Integer.parseInt(appId));
        } else {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        if (name != null && !("").equals(name)) {
            param.put("name", name);
        }
        if (collectionId != null && !("").equals(collectionId)) {
            param.put("collectionId", collectionId);
        }
        int updateWorkGroupById = workGroupService.updateWorkGroupDetailById(param);
        if (updateWorkGroupById < 0) {
            throw new WebException(MySystemCode.ACTION_EXCEPTION);
        }
        CommonPO commonPO = new CommonPO();
        commonPO.setCode(0);
        return commonPO;
    }

    @RequestMapping(value = "/getWorkGroupDetailByParam.json", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询任务组下任务详细信息及其规则 接口 ", notes = "", position = 0)
    public WorkGroupDetailListPO getWorkGroupDetailByParam(@RequestParam(value = "taskName", required = false) @ApiParam(value = "任务名", required = false) String taskName,
                                                           @RequestParam(value = "collectionId", required = true) @ApiParam(value = "任务组id", required = true) String collectionId,
                                                           @RequestParam(value = "timeCreate", required = false) @ApiParam(value = "任务创建时间", required = false) String timeCreate,
                                                           @RequestParam(value = "size", required = false) @ApiParam(value = "查询记录数 默认 10 条", required = false) String size,
                                                           @RequestParam(value = "page", required = false) @ApiParam(value = "查询页数 默认第 1 页", required = false) String page,
                                                           HttpServletRequest req, HttpServletResponse res) {
        if (collectionId == null || "".equals(collectionId) || !collectionId.matches("\\d+")) {
            throw new WebException(SystemCode.SYS_REQUEST_EXCEPTION);
        }
        Integer collectionIdInt = Integer.parseInt(collectionId);

        int sizeInt = 10;
        if (!Validate.isEmpty(size) && size.matches("\\d+")) {
            sizeInt = Integer.parseInt(size);
        }
        int pageInt = 0;
        if (!Validate.isEmpty(page) && page.matches("\\d+")) {
            pageInt = Integer.parseInt(page);
            pageInt = (pageInt - 1) * sizeInt;
        } else {
            page = "1";
        }

        String endTime = "";
        if (!Validate.isEmpty(timeCreate)) {
            endTime = timeCreate + " 23:59:59";
            timeCreate = timeCreate + " 00:00:00";
        }

        List<WorkGroupDetailBO> workGroupDetailBOS = workGroupService.getWorkGroupDetailByParam(taskName, collectionIdInt, timeCreate, endTime, sizeInt, pageInt);
        int count = workGroupService.getWorkGroupDetailCountByParam(taskName, collectionIdInt, timeCreate, endTime);

        if (workGroupDetailBOS.size() == 0 || count == 0) {
            throw new WebException(MySystemCode.BIZ_DATA_QUERY_EXCEPTION);
        }

        WorkGroupDetailListPO workGroupDetailListPO = new WorkGroupDetailListPO();


        List<WorkGroupDetailPO> workGroupDetailPOList = new ArrayList<>();
        for (WorkGroupDetailBO workGroupDetailBO : workGroupDetailBOS) {
            WorkGroupDetailPO workGroupDetailPO = new WorkGroupDetailPO();

            workGroupDetailPO.setApp_id(workGroupDetailBO.getAppId());
            workGroupDetailPO.setCollection_id(workGroupDetailBO.getCollectionId());
            workGroupDetailPO.setCount_local(workGroupDetailBO.getCountLocal());
            workGroupDetailPO.setName(workGroupDetailBO.getName());
            workGroupDetailPO.setInfo(workGroupDetailBO.getInfo());
            workGroupDetailPO.setRule(workGroupDetailBO.getRule());
            workGroupDetailPO.setStatus(workGroupDetailBO.getStatus());
            workGroupDetailPO.setType(workGroupDetailBO.getType());
            workGroupDetailPO.setType_sub(workGroupDetailBO.getTypeSub());
            workGroupDetailPO.setTime_create(workGroupDetailBO.getTimeCreate());
            // 把数据库的数据转换成对象
            WorkGroupRegulationDetailPO workGroupRegulationDetailPO = JSON.parseObject(workGroupDetailBO.getGraph(), WorkGroupRegulationDetailPO.class);
            workGroupDetailPO.setGraph(workGroupRegulationDetailPO);
            // 最后将整合的数据放在集合中
            workGroupDetailPOList.add(workGroupDetailPO);
        }
        workGroupDetailListPO.setList(workGroupDetailPOList);
        workGroupDetailListPO.setPage(Integer.parseInt(page));
        workGroupDetailListPO.setTotal(count);

        return workGroupDetailListPO;
    }


}
