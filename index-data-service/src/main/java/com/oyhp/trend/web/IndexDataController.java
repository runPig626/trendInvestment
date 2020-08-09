package com.oyhp.trend.web;

import com.oyhp.trend.config.IpConfiguration;
import com.oyhp.trend.model.IndexData;
import com.oyhp.trend.service.IndexDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-10
 */
@RestController
@RequestMapping("/index")
public class IndexDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IndexDataService indexDataService;

    @Resource
    private IpConfiguration ipConfiguration;

    @GetMapping("/data/{code}")
    @CrossOrigin
    public List<IndexData> getIndexDataList(@PathVariable("code") String code){
        logger.info("current instance is :" + ipConfiguration.getServerPort());
        return indexDataService.getIndexData(code);
    }
}
