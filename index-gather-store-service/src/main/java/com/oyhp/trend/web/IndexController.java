package com.oyhp.trend.web;

import com.oyhp.trend.model.Index;
import com.oyhp.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 指数控制器
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/getCodes")
    public List<Index> getCodes(){
        return indexService.fetchIndicesFromThirdPart();
    }
}
