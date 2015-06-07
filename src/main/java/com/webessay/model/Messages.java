package com.webessay.model;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJpaEntity(versionField = "", table = "messages")
public class Messages {
	
	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		if (this.date == null){
			this.date = new Date();
		}
	}

	@Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date date;

	public Date getDate() {
        return date;
    }

	public void setDate(Date date) {
        this.date = date;
    }
}
