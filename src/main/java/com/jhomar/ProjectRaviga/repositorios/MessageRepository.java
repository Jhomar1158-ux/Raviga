package com.jhomar.ProjectRaviga.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhomar.ProjectRaviga.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

}
