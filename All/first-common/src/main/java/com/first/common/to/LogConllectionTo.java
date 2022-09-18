package com.first.common.to;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * Sir 豪
 * 2022/6/20 0020
 */
@Document
@Data
public class LogConllectionTo {
    @MongoId
    private Long id;

    private String log;

    private Date createTime;
}
