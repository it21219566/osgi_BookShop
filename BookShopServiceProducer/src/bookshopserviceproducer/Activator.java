package bookshopserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegisterer;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("============Book Shop service started.============");
		CashierServicePublish cashierSer = new CashierServicePublishImpl();
		serviceRegisterer = context.registerService(CashierServicePublish.class.getName().toString(), cashierSer, null);//registering the cashierService
		ManagerServicePublish managerSer = new ManagerServicePublishImpl();
		serviceRegisterer = context.registerService(ManagerServicePublish.class.getName(), managerSer, null); //registering the managerService
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("==============Book Shop service stoped.=============");
		serviceRegisterer.unregister();
	}

}
