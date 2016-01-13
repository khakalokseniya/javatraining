package com.epam.training.kk.webapp.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import javax.inject.Inject;

import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.OrderService;


public class DriverTask extends TimerTask{
@Inject
private OrderService orderService;


	@Override
	public void run() {
		List<Order> all = orderService.getAll();
		List<Order> orders = new ArrayList();
		for(int i =0; i<all.size(); i++){
			if(all.get(i).isCompleted()==false){
				orders.add(all.get(i));
			}
		}
		Random randomizer = new Random();
		Order random = orders.get(randomizer.nextInt(orders.size()));
		random.setCompleted(true);
		
	}

}
