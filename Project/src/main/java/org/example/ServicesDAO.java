package org.example;
import java.util.List;

public interface ServicesDAO {
    public void saveService(Services service);
    public List<Services> getAllServices();
    public void updateService(Services service);
    public void deleteService(Services service);
}

