package com.neighborhood.incidentService.service;

import com.neighborhood.incidentService.dto.IncidentDTO;
import com.neighborhood.incidentService.model.Incident;
import com.neighborhood.incidentService.model.User;
import com.neighborhood.incidentService.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void reportIncident(Integer curr){
        kafkaTemplate.send("incident-topic", "message " + curr);
    }
    public void reportIncident(IncidentDTO incidentDTO) {
        Incident incident = new Incident();
        incident.setTitle(incidentDTO.getTitle());
        incident.setDescription(incidentDTO.getDescription());
        incident.setLocation(incidentDTO.getLocation());
        incident.setEmergency(false);  // Normal incident
        incident.setTimestamp(new Date());

        // Fetch user details (Assuming you have a UserService to fetch user by JWT token)
        User reportedBy = fetchCurrentUser();
        incident.setReportedBy(reportedBy);

        incidentRepository.save(incident);

        // Send message to Kafka
        kafkaTemplate.send("incident-topic", incidentDTO);
    }

    public void reportEmergency(IncidentDTO incidentDTO) {
        Incident incident = new Incident();
        incident.setTitle(incidentDTO.getTitle());
        incident.setDescription(incidentDTO.getDescription());
        incident.setLocation(incidentDTO.getLocation());
        incident.setEmergency(true);  // Emergency incident
        incident.setTimestamp(new Date());

        // Fetch user details (Assuming you have a UserService to fetch user by JWT token)
        User reportedBy = fetchCurrentUser();
        incident.setReportedBy(reportedBy);

        incidentRepository.save(incident);

        // Send message to Kafka
        kafkaTemplate.send("emergency-topic", incidentDTO);
    }

    private User fetchCurrentUser() {
        // Implement fetching the current user based on the JWT token
        // This is a placeholder and needs actual implementation
        return new User();  // Replace with actual user fetching logic
    }
}
