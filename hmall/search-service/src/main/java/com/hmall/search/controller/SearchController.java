package com.hmall.search.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.ItemDoc;
import com.hmall.common.domain.PageDTO;
import com.hmall.search.domain.query.ItemPageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(tags = "搜索相关接口")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
            RestClient.builder(HttpHost.create("http://192.168.88.130:9200"))
    );

    @ApiOperation(value = "根据id查询商品操作")
    @GetMapping("/{id}")
    public ItemDTO searchById(@PathVariable("id") Long id) throws IOException {
        GetRequest request = new GetRequest("items").id(id.toString());
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        String json = response.getSourceAsString();
        ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
        return BeanUtil.copyProperties(itemDoc, ItemDTO.class);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping
    public PageDTO<ItemDoc> pageSearch(ItemPageQuery itemPageQuery) {
        return null;
    }
}
