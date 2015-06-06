package com.webessay.model;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(versionField = "", table = "orders")
@RooDbManaged(automaticallyDelete = true)
public class Orders {

    @Transient
    @Size(max = 255)
    private String tempfile;

    public String getTempfile() {
        return tempfile;
    }

    public void setTempfile(String tempfile) {
        this.tempfile = tempfile;
    }

    public Orders() {
        tempfile = null;
    }
}
