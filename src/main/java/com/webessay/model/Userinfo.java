package com.webessay.model;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "userinfo")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "jobss", "jobss1" })
public class Userinfo {
}
