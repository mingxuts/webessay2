package com.webessay.user.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webessay.model.Jobs;
import com.webessay.model.JobsRepository;
import com.webessay.model.Messages;
import com.webessay.model.Userinfo;
import com.webessay.user.RegisteredUser;

@Component
public class WriterProfile implements Profile {

	private RegisteredUser writer;
	
	public static JobsRepository jobsrepo;
	
	public WriterProfile(){}
	
	public WriterProfile(RegisteredUser writer) {
		this.writer = writer;
	}


	@Autowired
	public void setJob(JobsRepository repo){
		WriterProfile.jobsrepo = repo;
	}
	
	
	@Override
	public String loadDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jobs> getProcessingJobs() {
		List<Jobs> ret= jobsrepo.getWriterProcessing(4, writer.getUserID());
		return ret;
	}

	@Override
	public List<Messages> getCanReadMessages() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<Integer, String> getContactsList() {
		Map<Integer, String> ret = new HashMap<Integer, String>();
		List<Jobs> myjobs = getProcessingJobs();
		for (Jobs job : myjobs){
			Userinfo customer = job.getCustomerId();
			Integer id = customer.getId();
			if (! ret.containsKey(id)){
				StringBuffer sb = new StringBuffer(500);
				sb.append("[Customer - ");
				sb.append(id + "] ");
				sb.append(customer.getFirstName() + "." + customer.getLastName());
				ret.put(id, sb.toString());
			}
		}
		return ret;
	}
	

}
