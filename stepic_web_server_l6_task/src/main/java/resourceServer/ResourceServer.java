package resourceServer;

import resources.IResource;

public class ResourceServer {
    private IResource testResource;

    public ResourceServer(final IResource testResource) {
        this.testResource = testResource;
    }

    public String getName() {
        return testResource.getName();
    }

    public int getAge() {
        return testResource.getAge();
    }

    public void setName(String name) {
        testResource.setName(name);
    }

    public void setAge(int age) {
        testResource.setAge(age);
    }
}
