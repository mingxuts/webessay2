package com.webessay.user.logic;

import java.util.List;

import com.webessay.model.Jobs;
import com.webessay.model.Messages;

public interface Profile {
	
	String loadDetail();
	List<Jobs> getProcessingJobs();
	List<Messages> getCanReadMessages();
}
