package com.salmon.ssata.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.backend.service.common.ConfigDataComposerServiceImpl;
import com.salmon.ssata.backend.service.common.ConfigDataSet;

public class ConfigDataSetupInterceptor extends HandlerInterceptorAdapter{
	
		
	
	@Autowired
	private ConfigDataSet configDataSet;
	
	@Autowired
	private ConfigDataComposerServiceImpl configDataComposerServiceImpl;
	
	public ConfigDataComposerServiceImpl getconfigDataComposerServiceImpl() {
		return configDataComposerServiceImpl;
	}

	public void setconfigDataComposerServiceImpl(
			ConfigDataComposerServiceImpl configDataComposerServiceImpl) {
		this.configDataComposerServiceImpl = configDataComposerServiceImpl;
	}

	public ConfigDataSet getConfigDataSet() {
		return configDataSet;
	}

	public void setConfigDataSet(ConfigDataSet configDataSet) {
		this.configDataSet = configDataSet;
	}
	
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Pre-handle");
		if(this.getConfigDataSet().isExecuteOnce() == true){
			
			System.out.println("Data execution flag: " + this.getConfigDataSet().isExecuteOnce());
			this.getConfigDataSet().setExecuteOnce(false);

			try{
				configDataComposerServiceImpl.parseAndPrepareConfigData();
			}catch(ServiceException spe){
				spe.printStackTrace();
			}
			
//			catch(DaoProfileException dpe){
//				dpe.printStackTrace();
//			} catch (DssServiceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (DssDAOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
        return true;
	}

}