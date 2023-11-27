package com.example.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {

    private String index;
    private Query query;
    private Highlight highlight;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Query {
        private Match match;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Match {
        private String title;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Highlight {
        private List<String> fields;
    }
}
