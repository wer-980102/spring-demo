package com.wer.elasticsearch.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Index {

    private Long id;
    private String title;
    private String desc;
    private String category;
}
