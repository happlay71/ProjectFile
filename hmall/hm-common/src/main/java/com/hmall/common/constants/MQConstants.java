package com.hmall.common.constants;

public interface MQConstants {
    String DELAY_EXCHANGE_NAME = "trade.delay.direct";
    String DELAY_ORDER_QUEUE_NAME = "trade.delay.order.queue";
    String DELAY_ORDER_KEY = "delay.order.query";
    String ES_DOC_EXCHANGE_NAME =  "es.doc.direct";
    String ES_INDEX_QUEUE_NAME = "es.doc.index.queue";
    String ES_UPDATE_SOLD_QUEUE_NAME = "es.doc.update.sold.queue";
    String ES_DELETE_QUEUE_NAME = "es.doc.delete.queue";
    String ES_INDEX_KEY = "doc.index";
    String ES_UPDATE_SOLD_KEY = "doc.update.sold";
    String ES_DELETE_KEY = "doc.delete";
}
