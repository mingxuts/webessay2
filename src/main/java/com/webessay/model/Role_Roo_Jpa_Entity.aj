// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.webessay.model;

import com.webessay.model.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Role_Roo_Jpa_Entity {
    
    declare @type: Role: @Entity;
    
    declare @type: Role: @Table(name = "role");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer Role.id;
    
    public Integer Role.getId() {
        return this.id;
    }
    
    public void Role.setId(Integer id) {
        this.id = id;
    }
    
}
