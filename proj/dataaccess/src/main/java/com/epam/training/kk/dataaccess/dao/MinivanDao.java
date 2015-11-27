package com.epam.training.kk.dataaccess.dao;

import com.epam.training.kk.dataaccess.model.Minivan;

public interface MinivanDao {
	Minivan getById(Long id);

	void insert(Minivan minivan);

	void update(Minivan minivan);
	
	void delete(Long id);

}
