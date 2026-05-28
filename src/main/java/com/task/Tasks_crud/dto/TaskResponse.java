package com.task.Tasks_crud.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String state;
    private Date dateCreation;

}
