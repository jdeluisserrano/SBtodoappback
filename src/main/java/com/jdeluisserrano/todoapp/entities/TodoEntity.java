package com.jdeluisserrano.todoapp.entities;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class TodoEntity {

	@Id
    private String id;
    
    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String title;
    
    private String name;
    
    private List<String> tags;
    
    private Boolean checkboxesEnabled = true;
    
    private Boolean viewed = false;
    
    private Date createdAt = new Date();
    
    public TodoEntity() {
        super();
    }
    
    public TodoEntity(String title) {
        this.title = title;
    }
    
}
