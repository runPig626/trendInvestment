package com.oyhp.trend.web;

import com.oyhp.trend.model.IndexData;
import com.oyhp.trend.service.IndexDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
@RestController
@RequestMapping("/indexData")
public class IndexDataController {

    @Resource
    private IndexDataService indexDataService;

    /**
     * 刷新指数数据
     * @param code
     * @return
     */
    @GetMapping("/freshIndexData/{code}")
    public String freshIndexData(@PathVariable("code") String code){
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }

    /**
     * 获取指数数据
     * @param code
     * @return
     */
    @GetMapping("/getIndexData/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code){
        return indexDataService.get(code);
    }

    @GetMapping("/removeIndexData/{code}")
    public String removeIndexData(@PathVariable("code") String code){
        indexDataService.remove(code);
        return "remove index data successfully";
    }

}
