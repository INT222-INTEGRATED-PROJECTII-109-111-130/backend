package IntegratedProject.int222.uploadfiles;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="integrated.strorage")
public class StorageProperties {


    private String location ;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
