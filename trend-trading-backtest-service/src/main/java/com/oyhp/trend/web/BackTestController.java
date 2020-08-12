package com.oyhp.trend.web;

import com.oyhp.trend.model.IndexData;
import com.oyhp.trend.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
  
/**
 * 回测指数数据控制器
 * @author OYHP
 */
@RestController
public class BackTestController {

    @Resource
    private BackTestService backTestService;
 
    @GetMapping("/simulate/{code}")
    @CrossOrigin
    public Map<String,Object> backTest(@PathVariable("code") String code) {
        List<IndexData> allIndexDataList = backTestService.listIndexData(code);
        Map<String,Object> result = new HashMap<>(16);
        result.put("indexDataList", allIndexDataList);
        return result;
    }

}