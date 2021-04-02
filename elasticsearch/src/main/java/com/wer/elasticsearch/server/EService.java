package com.wer.elasticsearch.server;

import com.alibaba.fastjson.JSON;
import com.wer.elasticsearch.model.Index;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.SumBucketPipelineAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

@Service
public class EService {

    /**
     * 高等级的客户端RestHighLevelClient
     */
    @Autowired
    private RestHighLevelClient client;

    /**
     * 新增es数据
     * @throws IOException
     */
    public void insertIndex() throws IOException {
        /** 定义索引 ： 表 **/
       IndexRequest request = new IndexRequest("ik_index");
        /** 插数据 **/
        request.source(JSON.toJSONString(Index.builder().id(100L).title("篮球").desc("我爱打篮球").category("体育")), XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.status());
        System.out.println(response.toString());


    }

    /**
     * ik是中分分词器
     * 查询数据
     * @throws IOException
     */
    public void searchIndex() throws IOException{
        SearchRequest searchRequest = new SearchRequest("ik_index");
        /** 请求体 **/
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /** 条件查询  **/
        QueryBuilder queryBuilder = new MatchQueryBuilder("desc","篮球");
        builder.query(queryBuilder);
        searchRequest.source(builder);
        /** 查询语法  **/
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        /** 拿到集合  his内含有his  第一个hits中包含了数据的条数，第二个hits中才是我们想要的查询结果  **/
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String record = hit.getSourceAsString();
            //这里可以转java集合
            System.out.println(record);
        }
    }

    /**
     * 取聚合查询，我们要使用aggregation了，然后再get我们的自定义名称response.getAggregations().get("category")。
     * 至于前面的类型，它是和AggregationBuilder对应的，在咱们的例子中使用的是TermsAggregationBuilder，那么我们在取
     * 结果时就要用Terms；如果查询时使用的是AvgAggregationBuilder，取结果时就要用Avg。
     *
     * @throws IOException
     */
    public void searchAggregation() throws IOException{
        SearchRequest request = new SearchRequest("ik_index");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /** terms：指定列查询   field：是聚合查询   keyword ： 不用分词器
         *  这里可以做统计查询
         * **/
        TermsAggregationBuilder category = AggregationBuilders.terms("category").field("category.keyword");
        SumAggregationBuilder category1 = AggregationBuilders.sum("id").field("category.keyword");
        builder.aggregation(category);

        request.source(builder);
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);

        Terms terms = response.getAggregations().get("category");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            System.out.println(bucket.getKey());
            System.out.println(bucket.getDocCount());
        }
    }

}
