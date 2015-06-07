package com.webessay.db.specification;

import org.springframework.data.jpa.domain.Specification;

import com.webessay.model.Userinfo;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class UserinfoSpecifications {
	
	public static Specification<Userinfo> emailEqual(final String email){
		
		return new Specification<Userinfo>(){
			
			@Override
			public Predicate toPredicate(Root<Userinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get("email"), email);
			}
		};
	}

}
