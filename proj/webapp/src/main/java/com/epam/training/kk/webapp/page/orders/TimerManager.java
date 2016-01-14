package com.epam.training.kk.webapp.page.orders;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager{
	private static TimerManager instance;
	private Timer timer;
	private Map<Long, Timer> timersMap;
	public TimerManager(){
		this.timer = new Timer();
	}
	
	public static TimerManager getInstance(){
		if(instance == null){
			instance = new TimerManager();
			instance.timersMap= new HashMap<Long, Timer>();
		}
		return instance;
	}
	
	public void start(TimerTask task, Timer timer){
		timersMap.put(task.scheduledExecutionTime(), timer);
		timersMap.get(task.scheduledExecutionTime()).schedule(task, 5000, 10000);
	}
	
	public void stop(){
		for (Map.Entry<Long, Timer> entry : timersMap.entrySet())
		{
			timersMap.get(entry.getKey()).cancel();
		}
	}

}
