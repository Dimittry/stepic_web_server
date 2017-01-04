package services;

import factory.ResourceFactory;
import resourceServer.ResourceServer;
import resourceServer.ResourceServerController;
import resourceServer.ResourceServerControllerMBean;
import resources.IResource;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class ResourceService {
    private IResource resource;
    private ResourceServer resourceServer;

    public ResourceService(String fileName) throws Exception {
        resource = (IResource) ResourceFactory.getInstance(fileName);
    }

    public void configureJMX() throws Exception {
        ResourceServer resourceServer = new ResourceServer(resource);
        ResourceServerControllerMBean serverStatistics = new ResourceServerController(resourceServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        if(mbs.isRegistered(name)) {
            mbs.unregisterMBean(name);
        }
        mbs.registerMBean(serverStatistics, name);
    }

}
