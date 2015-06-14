package com.webessay.user.logic;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webessay.model.Jobs;
import com.webessay.model.JobsRepository;
import com.webessay.model.Messages;
import com.webessay.model.MessagesRepository;
import com.webessay.model.Userinfo;
import com.webessay.user.RegisteredUser;
import com.webessay.user.UserUtils;

@Component
public class CustomerProfile implements Profile {
	
	private RegisteredUser customer;
	
	public CustomerProfile(){}
	
	public CustomerProfile(RegisteredUser customer){
		this.customer = customer;
	}
	
	public static JobsRepository jobsrepo;
	public static MessagesRepository messagesRepo;
	
	@Autowired
	public void setJob(JobsRepository repo){
		CustomerProfile.jobsrepo = repo;
	}
	
	@Autowired
	public void setMessage(MessagesRepository repo){
		CustomerProfile.messagesRepo = repo;
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
	public List<Messages> getCanReadMessages(Boolean hasRead) {
		if (hasRead){
			return messagesRepo.findReadMessages(this.customer.getUserID());
		} else {
			return messagesRepo.findUnreadMessages(this.customer.getUserID());
		}
	}

	@Override
	public Map<Integer, String> getContactsList() {
		Map<Integer, String> ret = new HashMap<Integer, String>();
		List<Jobs> myjobs = getProcessingJobs();
		for (Jobs job : myjobs){
			Userinfo writer = job.getWriterId();
			Integer id = writer.getId();
			if (! ret.containsKey(id)){
				StringBuffer sb = new StringBuffer(500);
				sb.append("[Writer - ");
				sb.append(id + "] ");
				sb.append(writer.getFirstName() + "." + writer.getLastName());
				ret.put(id, sb.toString());
			}
		}
		return ret;
	}

}
