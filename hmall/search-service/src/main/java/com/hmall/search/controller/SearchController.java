package com.hmall.search.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.ItemDoc;
import com.hmall.common.domain.PageDTO;
import com.hmall.search.domain.query.ItemPageQuery;
import com.hmall.search.utils.SearchUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = "搜索相关接口")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchUtils searchUtils;
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
    @GetMapping("/list")
    public PageDTO<ItemDoc> pageSearch(ItemPageQuery itemPageQuery) throws IOException {
        // 分页查询
        Integer pageNo = itemPageQuery.getPageNo();
        Long pageSize = itemPageQuery.getPageSize().longValue();
        String keyWord = itemPageQuery.getKey();
        // 创建request
        SearchRequest request = new SearchRequest("items");
        BoolQueryBuilder bool = QueryBuilders.boolQuery();

        // 判断是否存在关键字
        if (keyWord != null && StrUtil.isNotBlank(keyWord)) {
            // 关键字查询
            searchUtils.getMatch(bool, keyWord);
        } else {
            searchUtils.getMatchAll(bool);
        }

        // 增加过滤条件
        searchUtils.filter(bool, itemPageQuery);

        // 排序
        if (itemPageQuery.getSortBy() != null && StrUtil.isNotBlank(itemPageQuery.getSortBy())) {
            request.source().sort(
                    itemPageQuery.getSortBy(), itemPageQuery.getIsAsc() ? SortOrder.ASC : SortOrder.DESC
            );
        }

        // 复合查询
        request.source().query(bool);
//        request.source().query(QueryBuilders.functionScoreQuery(bool,
//                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
//                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//                                QueryBuilders.termQuery("isAD", true),
//                                        ScoreFunctionBuilders.weightFactorFunction(100))
//                }).boostMode(CombineFunction.MULTIPLY));
//        // 是否投放广告
        request.source().sort("isAD", SortOrder.DESC);

        // 解析结果返回
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return searchUtils.parseResponseResult(response, pageSize);
    }

    @ApiOperation(value = "过滤条件聚合")
    @PostMapping("/filters")
    public Map filtersSearch(@RequestBody ItemPageQuery itemPageQuery) throws IOException {
        // 创建request
        SearchRequest request = new SearchRequest("items");
        // 查询条件
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        searchUtils.getMatch(bool, itemPageQuery.getKey());
        searchUtils.filter(bool, itemPageQuery);
        // 过滤条件聚合
        request.source().size(0);
        request.source().aggregation(
                AggregationBuilders.terms("category").field("category")
        ).aggregation(
                AggregationBuilders.terms("brand").field("brand")
        );

        request.source().query(bool);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return searchUtils.parsePolymerizationResult(response);
    }



}
