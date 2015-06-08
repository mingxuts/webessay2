package com.webessay.user.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webessay.model.Jobs;
import com.webessay.model.JobsRepository;
import com.webessay.model.Messages;
import com.webessay.user.RegisteredUser;

@Component
public class CustomerProfile implements Profile {
	
	private RegisteredUser customer;
	
	public CustomerProfile(){}
	
	public CustomerProfile(RegisteredUser customer){
		this.customer = customer;
	}
	
	public static JobsRepository jobsrepo;
	
	@Autowired
	public void setJob(JobsRepository repo){
		CustomerProfile.jobsrepo = repo;
	}

	@Override
	public String loadDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jobs> getProcessingJobs() {
		List<Jobs> ret = jobsrepo.getCustomerProcessing(4, this.customer.getUserID());
		return ret;
	}

	@Override
	public List<Messages> getCanReadMessages() {
		// TODO Auto-generated method stub
		return null;
	}

}
