package com.slokam.healthcare.service;

import java.util.List;

public interface IVisitingService {
	public  List<Object[]> getVisitingsByPatientId(Integer id);

}
