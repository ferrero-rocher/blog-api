package com.shaun.blogapi.dto;

import com.shaun.blogapi.entity.Author;
import com.shaun.blogapi.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class CreatePostRequest {

    private String title;

    private String content;


    private AuthorRequest author;



    private Date createdAt=calculatetime();

    private boolean isDeleted=false;



    private Date calculatetime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        return new Date(calendar.getTime().getTime());
    }
}
