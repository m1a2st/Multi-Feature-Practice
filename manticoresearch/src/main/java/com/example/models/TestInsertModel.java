package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestInsertModel {

    private Insert insert;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Insert{
        private String index;
        private int id;
        private Doc doc;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Doc{
        private String title;
    }

    public static TestInsertModel init(int id, String title){
        return TestInsertModel.builder()
                .insert(Insert.builder()
                        .index("test")
                        .id(id)
                        .doc(Doc.builder()
                                .title(title)
                                .build())
                        .build())
                .build();
    }
}
