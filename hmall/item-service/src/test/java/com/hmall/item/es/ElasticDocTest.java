package com.hmall.item.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.item.domain.po.Item;
import com.hmall.item.domain.po.ItemDoc;
import com.hmall.item.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest(properties = "spring.profiles.active=local")  // 激活数据库
public class ElasticDocTest {
    private RestHighLevelClient client;

    @Test
    void testConnection() {
        System.out.println("client = " + client);
    }

    @Autowired
    private IItemService iItemService;
    @Test
    void testIndexDoc() throws IOException {
        // 0.准备文档数据
        // 查询数据
        Item item = iItemService.getById(100000011127L);
        // 转成文档数据
        ItemDoc itemDoc = BeanUtil.copyProperties(item, ItemDoc.class);

        // 1.准备Request
        IndexRequest request = new IndexRequest("items").id(item.getId().toString());
        // 2.准备请求参数
        request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetDoc() throws IOException {
        // 1.准备Request
        GetRequest request = new GetRequest("items").id("100000011127");
        // 2.发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.解析出响应中的_source
        String json = response.getSourceAsString();
        // 4.转为文档实体类
        ItemDoc itemDoc = JSONUtil.toBean(json, ItemDoc.class);
        System.out.println(itemDoc);
    }

    @Test
    void testDeleteDoc() throws IOException {
        // 1.准备Request
        DeleteRequest request = new DeleteRequest("items", "100000011127");
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);

    }

    @Test
    void testUpdateDoc() throws IOException {
        // 1.准备Request
        UpdateRequest request = new UpdateRequest("items", "100000011127");
        // 2.准备请求参数
        request.doc(
                "price", 25600
        );
        // 3.发送请求
        client.update(request, RequestOptions.DEFAULT);

    }

    @Test
    void testBulkDoc() throws IOException {
        // 0.准备文档数据
        int pageNo = 1;
        int pageSize = 500;
        while (true) {
            Page<Item> page = iItemService.lambdaQuery()
                    .eq(Item::getStatus, 1)
                    .page(Page.of(pageNo, pageSize));
            List<Item> records = page.getRecords();
            if (records == null || records.isEmpty()) {
                return;
            }


            // 1.准备Request
            BulkRequest request = new BulkRequest();
            // 2.准备请求参数
            for (Item item : records) {
                request.add(new IndexRequest("items").id(item.getId().toString())
                        .source(JSONUtil.toJsonStr(BeanUtil.copyProperties(item, ItemDoc.class)), XContentType.JSON));
            }
            // 3.发送请求
            client.bulk(request, RequestOptions.DEFAULT);
            // 4.翻页
            pageNo++;
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
