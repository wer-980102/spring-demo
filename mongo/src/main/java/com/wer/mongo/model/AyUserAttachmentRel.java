package com.wer.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AyUserAttachmentRel {

    private String id;
    private String userId;
    private String fileName;
}
