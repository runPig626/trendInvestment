package com.oyhp.trend.service;

import com.oyhp.trend.client.IndexDataClient;
import com.oyhp.trend.model.IndexData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author OYHP
 */
@Service
public class BackTestService {

    @Resource
    private IndexDataClient indexDataClient;

    /**
     * 指数数据list
     * @param code
     * @return
     */
    public List<IndexData> listIndexData(String code){
        List<IndexData> result = indexDataClient.getIndexData(code);
        Collections.reverse(result);
        return result;
    }

}