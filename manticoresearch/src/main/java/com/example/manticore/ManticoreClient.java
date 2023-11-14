package com.example.manticore;

import com.example.models.Request;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.api.UtilsApi;
import com.manticoresearch.client.model.InsertDocumentRequest;
import com.manticoresearch.client.model.SearchRequest;
import com.manticoresearch.client.model.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ManticoreClient {

    private final IndexApi indexApi;
    private final SearchApi searchApi;
    private final UtilsApi utilsApi;

    public void createTable() throws ApiException {
        String body = "create table products(title text, price float) morphology='stem_en'";
        utilsApi.sql(body, true);
    }

    public void insert() {
        try {
            InsertDocumentRequest newDoc = new InsertDocumentRequest();
            newDoc.index("products").doc(new HashMap<>() {{
                put("title", "Pet Hair Remover Glove");
                put("price", 7.99);
            }});
            indexApi.insert(newDoc);
        } catch (ApiException e) {
            System.err.println("Exception when calling IndexApi#bulk");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
        }
    }

    public void searchAll() throws ApiException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.index("youtube");
        searchRequest.setOffset(1);
        searchRequest.setLimit(10);

        SearchResponse search = searchApi.search(searchRequest);
        System.out.println(search.getHits().getTotal());
    }

    private Request getSearchRequest() {
        return Request.builder()
                .index("mediums")
                .query(Request.Query.builder()
                        .match(Request.Match.builder()
                                .title("The")
                                .build())
                        .build())
                .highlight(Request.Highlight.builder()
                        .fields(List.of("title"))
                        .build())
                .build();
    }
}
