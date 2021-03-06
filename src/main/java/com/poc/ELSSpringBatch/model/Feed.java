package com.poc.ELSSpringBatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "feed")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "location")
    private String location;

    @Field(type = FieldType.Integer, name = "lng")
    private Integer lng;

    @Field(type = FieldType.Integer, name = "lat")
    private Integer lat;

    @Field(type = FieldType.Integer, name = "userId")
    private Integer userId;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Boolean, name = "isdeleted")
    private Boolean isdeleted;

    @Field(type = FieldType.Text, name = "profilePicture")
    private String profilePicture;

    @Field(type = FieldType.Text, name = "videoUrl")
    private String videoUrl;

    @Field(type = FieldType.Text, name = "images")
    private String images;

    @Field(type = FieldType.Integer, name = "mediatype")
    private Integer mediatype;

    @Field(type = FieldType.Text, name = "imagePaths")
    private String imagePaths;

    @Field(type = FieldType.Text, name = "feedsComment")
    private String feedsComment;

    @Field(type = FieldType.Text, name = "commentCount")
    private String commentCount;

    @Field(type = FieldType.Text, name = "createdAt")
    private String createdAt;

    @Field(type = FieldType.Integer, name = "code")
    private String code;

}
