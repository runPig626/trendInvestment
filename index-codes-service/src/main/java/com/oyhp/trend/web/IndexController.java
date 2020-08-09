package com.oyhp.trend.web;

import ch.qos.logback.classic.spi.LoggerContextListener;
import com.oyhp.trend.config.IpConfiguration;
import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 指数代码Controller
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IndexService indexService;

    @Resource
    private IpConfiguration ipConfiguration;

    /**
     * 获取指数代码
     * @return
     */
    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> codes(){
        logger.info("当前服务运行端口号: " + ipConfiguration.getServerPort());
        return indexService.getIndexes();
    }

}
