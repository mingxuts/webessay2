package com.webessay.user.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webessay.model.Jobs;
import com.webessay.model.JobsRepository;
import com.webessay.model.Messages;
import com.webessay.user.RegisteredUser;

public class WriterProfile implements Profile {

	private RegisteredUser writer;
	
	public static JobsRepository jobsrepo;
	
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

}
