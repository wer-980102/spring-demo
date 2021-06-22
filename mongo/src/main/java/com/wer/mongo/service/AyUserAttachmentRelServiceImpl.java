package com.wer.mongo.service;

import com.wer.mongo.model.AyUserAttachmentRel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * mongo实现接口
 */
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService{

    @Autowired
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    /**
     * 新增
     * @param ayUserAttachmentRel
     * @return
     */
    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
