package com.salmon.ssata.backend.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;



@Component
public class AppSmartLifeCycleBean implements SmartLifecycle{

	private volatile boolean isRunning = false;

	@Autowired
	private ConfigDataSet testDataSet;
	
	public ConfigDataSet getTestDataSet() {
		return testDataSet;
	}

	public void setTestDataSet(ConfigDataSet testDataSet) {
		this.testDataSet = testDataSet;
	}

	@Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable r) {
        System.out.println("STOPPED RUNNABLE!!!");
        isRunning = false;
    }

    @Override
    public void start() {
        System.out.println("STARTED!!!STARTED");
        System.out.println("Data execution flag: " + this.getTestDataSet().isExecuteOnce());
        this.getTestDataSet().setExecuteOnce(true);

        isRunning = true;
        this.stop();
    }

    @Override
    public void stop() {
        System.out.println("STOPPED!!!");
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 1;
    }

}
