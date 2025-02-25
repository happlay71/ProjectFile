package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//@SpringBootTest(properties = "spring.profiles.active=local")  // 激活数据库
public class ElasticSearchTest {
    private RestHighLevelClient client;

    @Test
    void testMatchAll() throws IOException {
        // 1.创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2.配置request参数
        request.source()
                .query(QueryBuilders.matchAllQuery());
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        parseResponseResult(response);
    }

    @Test
    void testSearch() throws IOException {
        // 1.创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2.配置request参数
        request.source()
                .query(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", "脱脂牛奶"))
                        .filter(QueryBuilders.termQuery("brand", "德亚"))
                        .filter(QueryBuilders.rangeQuery("price").lt(30000))
                );
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        parseResponseResult(response);
    }

    @Test
    void testSortAndPage() throws IOException {
        // 0.模拟前端传递的分页参数
        int pageNo = 1, pageSize = 5;
        // 1.创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2.配置request参数
        // 2.1.条件
        request.source().query(QueryBuilders.matchAllQuery());
        // 2.2.分页
        request.source().from((pageNo - 1) * pageSize).size(pageSize);
        // 2.3.排序
        request.source().sort("sold", SortOrder.DESC)
                .sort("price", SortOrder.ASC);
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        parseResponseResult(response);
    }

    @Test
    void testHighlight() throws IOException {
        // 1.创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2.配置request参数
        request.source().query(QueryBuilders.matchQuery("name", "脱脂牛奶"));
        // 配置高亮条件
        request.source().highlighter(SearchSourceBuilder.highlight()
                .field("name"));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        parseResponseResult(response);
    }

    @Test
    void testAgg() throws IOException {
        // 1.创建request对象
        SearchRequest request = new SearchRequest("items");
        // 2.1.分页
        request.source().size(0);
        // 2.2.聚合
        String brandAggName = "brandAgg";
        request.source().aggregation(AggregationBuilders
                .terms(brandAggName).field("brand").size(5));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        Aggregations aggregations = response.getAggregations();
        // 4.1.根据聚合名称获取对应聚合
        Terms aggregation = aggregations.get(brandAggName);
        // 4.2.获取buckets
        List<? extends Terms.Bucket> buckets = aggregation.getBuckets();

        // 4.3.遍历获取bucket
        for (Terms.Bucket bucket : buckets) {
            System.out.println("brand: " + bucket.getKeyAsString());
            System.out.println("docCount: " + bucket.getDocCount());
        }
    }

    private static void parseResponseResult(SearchResponse response) {

        SearchHits searchHits = response.getHits();
        // 4.1.总条数
        long total = searchHits.getTotalHits().value;
        // 4.2.命中的数据
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
            // 4.3.处理高亮结果
            Map<String, HighlightField> hfs = hit.getHighlightFields();
            if (hfs != null && !hfs.isEmpty()) {
                // 根据高亮字段名获取高亮结果
                HighlightField hf = hfs.get("name");
                // 覆盖非高亮结果
                String hfName = hf.getFragments()[0].toString();
                itemDoc.setName(hfName);
            }
            System.out.println("doc：" + itemDoc);
        }
    }

    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.88.130:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (client != null) {
            client.close();
        }
    }

}
