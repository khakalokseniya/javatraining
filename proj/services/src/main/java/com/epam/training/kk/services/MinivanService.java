package com.epam.training.kk.services;

import com.epam.training.kk.dataaccess.model.Minivan;

public interface MinivanService {
	
	Minivan get(Long id);
	 
	void insertOrUpdate(Minivan car);
	
	void delete(Long id);

}
