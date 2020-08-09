package com.oyhp.trend.web;

import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 指数控制器
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Resource
    private IndexService indexService;

    /**
     * 刷新redis缓存数据
     * @return
     */
    @GetMapping("/freshCodes")
    public List<Index> getCodes(){
        return indexService.fresh();
    }

    /**
     * 获取redis缓存数据
     * @return
     */
    @GetMapping("/getCodes")
    public List<Index> get(){
        return indexService.getIndexes();
    }

    /**
     * 移除redis缓存指数代码
     * @return
     */
    @GetMapping("/removeCodes")
    public String remove(){
        indexService.remove();
        return "remove codes successfully";
    }

}
