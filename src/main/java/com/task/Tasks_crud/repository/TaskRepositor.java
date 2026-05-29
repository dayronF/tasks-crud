package com.task.Tasks_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Tasks_crud.entity.TaskEntity;

@Repository
public interface TaskRepositor extends JpaRepository <TaskEntity, Long>  {

    
}