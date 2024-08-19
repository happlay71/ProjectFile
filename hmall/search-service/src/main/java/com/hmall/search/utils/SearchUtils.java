package com.hmall.search.utils;

import cn.hutool.json.JSONUtil;
import com.hmall.api.dto.ItemDoc;
import com.hmall.common.domain.PageDTO;
import com.hmall.search.domain.query.ItemPageQuery;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SearchUtils {

    public void getMatchAll(BoolQueryBuilder bool) throws IOException {
        bool.must(QueryBuilders.matchAllQuery());
    }

    public void getMatch(BoolQueryBuilder bool, String keyWord) {
        bool.must(QueryBuilders.matchQuery("name", keyWord));
    }

    public void filter(BoolQueryBuilder bool, ItemPageQuery itemPageQuery) {
        String category = itemPageQuery.getCategory();
        String brand = itemPageQuery.getBrand();
        Integer maxPrice = itemPageQuery.getMaxPrice();
        Integer minPrice = itemPageQuery.getMinPrice();

        // 添加分类过滤条件
        if (category != null && !category.isEmpty()) {
            bool.filter(QueryBuilders.termQuery("category", category));
        }

        // 添加品牌过滤条件
        if (brand != null && !brand.isEmpty()) {
            bool.filter(QueryBuilders.termQuery("brand", brand));
        }

        // 添加价格区间过滤条件
        if (minPrice != null && maxPrice != null) {
            bool.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        } else if (minPrice != null) {
            bool.filter(QueryBuilders.rangeQuery("price").gte(minPrice));
        } else if (maxPrice != null) {
            bool.filter(QueryBuilders.rangeQuery("price").lte(maxPrice));
        }
    }

//    public void sort(BoolQueryBuilder bool, ItemPageQuery itemPageQuery) {
//        bool.filter(QueryBuilders.)
//    }

    public PageDTO<ItemDoc> parseResponseResult(SearchResponse response, Long pageSize) {

        SearchHits searchHits = response.getHits();
        // 总条数
        long total = searchHits.getTotalHits().value;
        // 命中的数据
        SearchHit[] hits = searchHits.getHits();
        List<ItemDoc> itemDocs = Arrays.stream(hits).map(hit -> {
            String json = hit.getSourceAsString();
            ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
            // 处理高亮结果
            Map<String, HighlightField> hfs = hit.getHighlightFields();
            if (hfs != null && !hfs.isEmpty()) {
                // 根据高亮字段名获取高亮结果
                HighlightField hf = hfs.get("name");
                // 覆盖非高亮结果
                String hfName = hf.getFragments()[0].toString();
                itemDoc.setName(hfName);
            }
            return itemDoc;
        }).collect(Collectors.toList());

        // 返回结果
        return new PageDTO<>(total, pageSize, itemDocs);
    }

    public Map parsePolymerizationResult(SearchResponse response) {
        Map<String, List<String>> result = new HashMap<>();
        // 获取聚合结果
        Aggregations aggregations = response.getAggregations();
        // 获取商品类别和品牌的结果--目前只能获取terms类型，其他类型会跳过
        Map<String, Aggregation> aggregationMap = aggregations.getAsMap();
        aggregationMap.forEach((key, value) -> {
            if (!(value instanceof Terms)) {
                return; // 如果不是Terms类型，跳过该循环
            }
            List<? extends Terms.Bucket> buckets = ((Terms) value).getBuckets();
            List<String> filtersResult = buckets.stream()
                    .map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList());
            result.put(key, filtersResult);
        });

        System.out.println(result);
        return result;
    }
}
