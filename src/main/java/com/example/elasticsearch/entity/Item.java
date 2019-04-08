package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "item",type = "docs", shards = 1, replicas = 0)
public class Item {

    /**
     * @Description: @Id注解必须是springframework包下的
     * org.springframework.data.annotation.Id
     *@Author: https://blog.csdn.net/chen_2890
     */
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", fielddata = true)
    private String title; //标题

    @Field(type = FieldType.Keyword, fielddata = true)
    private String category;// 分类

    @Field(type = FieldType.Keyword, fielddata = true)
    private String brand; // 品牌

    @Field(type = FieldType.Double, fielddata = true)
    private Double price; // 价格

    @Field(index = false, type = FieldType.Keyword, fielddata = true)
    private String images; // 图片地址

    public Item(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }

    public Item() {
    }
}
