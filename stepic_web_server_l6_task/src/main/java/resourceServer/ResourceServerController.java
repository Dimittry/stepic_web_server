package resourceServer;

public class ResourceServerController implements ResourceServerControllerMBean {
    private ResourceServer resourceServer;

    public ResourceServerController(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    public String getName() {
        return resourceServer.getName();
    }

    public int getAge() {
        return resourceServer.getAge();
    }

    public void setName(String name) {
        resourceServer.setName(name);
    }

    public void setAge(int age) {
        resourceServer.setAge(age);
    }
}
