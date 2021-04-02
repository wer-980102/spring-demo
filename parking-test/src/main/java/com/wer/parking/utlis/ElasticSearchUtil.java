package com.wer.parking.utlis;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.ParsedComposite;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nutgi
 */
@Component
@Slf4j
public class ElasticSearchUtil {
    @Autowired
    private RestHighLevelClient client;

    private static int retryLimit = 3;


    /**
     * 搜索
     *
     * @param index
     * @param searchSourceBuilder
     * @param clazz 需要封装的obj
     * @param pageNum
     * @param pageSize
     * @return ElasticSearchPageResponse<T>
     */
    public <T> ElasticSearchPageResponse<T> search(String index, SearchSourceBuilder searchSourceBuilder, Class<T> clazz,
                                                   Integer pageNum, Integer pageSize){
        if (ObjectUtil.isNull(pageNum)){
            pageNum = 1;
        }
        if (ObjectUtil.isNull(pageSize)){
            pageSize= 10;
        }
        SearchRequest searchRequest = new SearchRequest(index);
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.from(pageNum-1);
        searchSourceBuilder.trackTotalHits(true);
        searchRequest.source(searchSourceBuilder);
        log.info("DSL语句为：{}",searchRequest.source().toString());
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            ElasticSearchPageResponse<T> pageResponse = new ElasticSearchPageResponse<>();
            pageResponse.setPageNum(pageNum);
            pageResponse.setPageSize(pageSize);
            pageResponse.setTotal(response.getHits().getTotalHits().value);
            List<T> dataList = new ArrayList<>();
            SearchHits hits = response.getHits();
            for(SearchHit hit : hits){
                dataList.add(JSONObject.parseObject(hit.getSourceAsString(), clazz));
            }
            pageResponse.setData(dataList);
            return pageResponse;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 聚合(不适用于分组多个)
     *
     * @param index
     * @param searchSourceBuilder
     * @param aggName 聚合名
     * @return Map<String, Long>  key:aggName   value: doc_count
     */
    public Map<String, Object> aggSearch(String index, SearchSourceBuilder searchSourceBuilder, String aggName){
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        log.info("DSL语句为：{}",searchRequest.source().toString());
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Terms terms = aggregations.get(aggName);
            List<? extends Terms.Bucket> buckets = terms.getBuckets();
            Map<String, Object> responseMap = new HashMap<>(buckets.size());
            buckets.forEach(bucket-> {
                responseMap.put("doc_count", bucket.getDocCount());
                responseMap.put("key_as_string", bucket.getKeyAsString());
                responseMap.put("key", bucket.getKey());
            });
            return responseMap;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 聚合-返回Aggregations(List<Terms.Bucket>类型)
     *
     * @param index
     * @param searchSourceBuilder
     * @param aggName 聚合名
     */
    public List aggListSearch(String index, SearchSourceBuilder searchSourceBuilder, String aggName){
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        log.info("DSL语句为：{}",searchRequest.source().toString());
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Terms terms = aggregations.get(aggName);
            List<? extends Terms.Bucket> buckets = terms.getBuckets();
            return buckets;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 聚合-返回Aggregations(List<ParsedBucket>类型) Composite聚合查询时
     *
     * @param index
     * @param searchSourceBuilder
     * @param aggName 聚合名
     */
    public List<ParsedComposite.ParsedBucket> aggCompositeSearch(String index, SearchSourceBuilder searchSourceBuilder, String aggName){
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        log.info("DSL语句为：{}",searchRequest.source().toString());
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            for(Aggregation aggregation:aggregations.asList()){
                ParsedComposite parsedComposite = (ParsedComposite)aggregation;
                if (parsedComposite.getName().equals(aggName)){
                    return parsedComposite.getBuckets();
                }
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 聚合-求和
     *
     * @param index
     * @param searchSourceBuilder
     * @param aggName 聚合名
     * @return Double
     */
    public Double aggSumSearch(String index, SearchSourceBuilder searchSourceBuilder, String aggName){
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);
        log.info("DSL语句为：{}",searchRequest.source().toString());
        try {
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            ParsedSum parsedSum = aggregations.get(aggName);
            return parsedSum.getValue();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }



    /**
     *  新增或者更新文档
     *
     *  对于更新文档，建议可以直接使用新增文档的API，替代 UpdateRequest
     *  避免因对应id的doc不存在而抛异常：document_missing_exception
     * @param obj
     * @param index
     * @return
     */
    public Boolean addOrUptDocToEs(Object obj, String index){

        try {
            IndexRequest indexRequest = new IndexRequest(index).id(getESId(obj))
                    .source(JSON.toJSONString(obj), XContentType.JSON);
            int times = 0;
            while (times < retryLimit) {
                IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

                if (indexResponse.status().equals(RestStatus.CREATED) || indexResponse.status().equals(RestStatus.OK)) {
                    return true;
                } else {
                    log.info(JSON.toJSONString(indexResponse));
                    times++;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("Object = {}, index = {}, id = {} , exception = {}", obj, index, getESId(obj) , e.getMessage());
            return null;
        }

    }


    /**
     *  删除文档
     *
     * @param index
     * @param id
     * @return
     */
    public Boolean deleteDocToEs(Integer id, String index) {
        try {
            DeleteRequest request = new DeleteRequest(index, id.toString());

            int times = 0;
            while (times < retryLimit) {
                DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);

                if (delete.status().equals(RestStatus.OK)) {
                    return true;
                } else {
                    log.info(JSON.toJSONString(delete));
                    times++;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("index = {}, id = {} , exception = {}", index, id , e.getMessage());
            return null;
        }
    }


    /**
     * 批量插入 或者 更新
     *
     * @param array 数据集合
     * @param index
     * @return
     */
    public Boolean batchAddOrUptToEs(JSONArray array, String index) {

        try {
            BulkRequest request = new BulkRequest();
            for (Object obj : array) {
                IndexRequest indexRequest = new IndexRequest(index).id(getESId(obj))
                        .source(JSON.toJSONString(obj), XContentType.JSON);
                request.add(indexRequest);
            }
            BulkResponse bulk = client.bulk(request, RequestOptions.DEFAULT);

            return bulk.status().equals(RestStatus.OK);
        } catch (Exception e) {
            log.error("index = {}, exception = {}", index, e.getMessage());
            return null;
        }
    }


    /**
     * 批量删除
     * @param deleteIds 待删除的 _id list
     * @param index
     * @return
     */
    public Boolean batchDeleteToEs(List<Integer> deleteIds, String index){
        try {
            BulkRequest request = new BulkRequest();
            for (Integer deleteId : deleteIds) {
                DeleteRequest deleteRequest = new DeleteRequest(index, deleteId.toString());
                request.add(deleteRequest);
            }
            BulkResponse bulk = client.bulk(request, RequestOptions.DEFAULT);

            return bulk.status().equals(RestStatus.OK);
        } catch (Exception e) {
            log.error("index = {}, exception = {}", index, e.getMessage());
            return null;
        }
    }


    /**
     * 将obj的id 作为 doc的_id
     * @param obj
     * @return
     */
    private String getESId(Object obj) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(obj));
        Object id = jsonObject.get("id");
        return JSON.toJSONString(id);
    }
}
