package com.hmall.search.listener;

import cn.hutool.json.JSONUtil;
import com.hmall.api.dto.ItemDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.hmall.common.constants.MQConstants.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ESDocListener {

    private final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
            RestClient.builder(HttpHost.create("http://192.168.88.130:9200"))
    );


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = ES_INDEX_QUEUE_NAME),
            exchange = @Exchange(name = ES_DOC_EXCHANGE_NAME),
            key = ES_INDEX_KEY
    ))
    public void esDocIndex(ItemDoc itemDoc) throws IOException {
        // 1.准备Request
        IndexRequest request = new IndexRequest("items").id(itemDoc.getId());
        // 2.准备请求参数
        request.source(JSONUtil.toJsonStr(itemDoc), XContentType.JSON);
        // 3.发送请求
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = ES_UPDATE_SOLD_QUEUE_NAME),
            exchange = @Exchange(name = ES_DOC_EXCHANGE_NAME),
            key = ES_UPDATE_SOLD_KEY
    ))
    public void esDocUpdateSold(List<ItemDoc> itemDocs) throws IOException {
        BulkRequest request = new BulkRequest();
        for (ItemDoc itemDoc : itemDocs) {
            request.add(
                    new UpdateRequest("items", itemDoc.getId())
                            .doc(
                                    "sold", itemDoc.getSold()
                            )
            );
        }
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            // 记录异常日志或采取其他处理措施
            System.err.println("Failed to update ES document: " + e.getMessage());
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = ES_DELETE_QUEUE_NAME),
            exchange = @Exchange(name = ES_DOC_EXCHANGE_NAME),
            key = ES_DELETE_KEY
    ))
    public void esDocDelete(Long id) throws IOException {
        // 1.准备Request
        DeleteRequest request = new DeleteRequest("items", id.toString());
        // 2.发送请求
        restHighLevelClient.delete(request, RequestOptions.DEFAULT);
    }
}
