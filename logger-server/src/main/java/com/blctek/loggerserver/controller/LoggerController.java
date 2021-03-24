package com.blctek.loggerserver.controller;

import com.blctek.commonserver.pojo.Logger;
import com.blctek.commonserver.response.ResultResponse;
import com.blctek.commonserver.vo.VoList;
import com.blctek.loggerserver.service.LoggerService;
import com.blctek.loggerserver.vo.VoLogger;
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
        Logger logger = new Logger();
        logger.setExecutor(executor);
        logger.setClient(client);
        Long totalSize = loggerService.selectTotalSize(logger);

        List<VoLogger> voLoggerList = loggerService.selectListByCondition(executor, client, currentPage, pageSize);

        VoList<VoLogger> voList = new VoList<>();
        voList.setItems(voLoggerList);
        voList.setTotalSize(totalSize);
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);

        return new ResultResponse()
                .setMessage("查询日志")
                .setData(voList);
    }
}
