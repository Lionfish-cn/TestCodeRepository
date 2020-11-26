package com.code.repository.controller;

import com.code.repository.entity.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
public class BaseController {

    @Autowired
    private EntityManager em;

    @RequestMapping("/save")
    public void save(@ModelAttribute BaseEntity baseEntity) throws Exception {
        JpaRepository<BaseEntity, String> jpa = new SimpleJpaRepository<BaseEntity, String>((Class<BaseEntity>) baseEntity.getClass(), em);
        jpa.save(baseEntity);
    }

}
