package com.blctek.loggerserver.controller;

import com.blctek.commonserver.pojo.Logger;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.loggerserver.service.LoggerService;
import com.blctek.loggerserver.vo.list.VoLoggerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/14:21
 * @Description:
 */
@RestController
@RequestMapping("/logger")
@CrossOrigin("*")
public class LoggerController {
    @Autowired
    private LoggerService loggerService;
    @GetMapping("/selectListByCondition")
    public ResultResponse selectListByCondition(@RequestParam(required = false)String executor,
                                                @RequestParam(required = false)String client,
                                                @RequestParam(required = true)Integer currentPage,
                                                @RequestParam(required = true)Integer pageSize){
        List<Logger> loggerList = loggerService.selectListByCondition(executor, client, currentPage, pageSize);
        Logger logger = new Logger();
        logger.setExecutor(executor);
        logger.setClient(client);
        Long totalSize = loggerService.selectTotalSize(logger);
        VoLoggerList voLoggerList = new VoLoggerList(loggerList);
        voLoggerList.setCurrentPage(currentPage);
        voLoggerList.setPageSize(pageSize);
        voLoggerList.setTotalSize(totalSize);
        return new ResultResponse()
                .setMessage("查询日志")
                .setData(voLoggerList);
    }
}
