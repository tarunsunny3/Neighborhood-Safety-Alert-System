package com.neighborhood.incidentService.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDTO {
    private String title;
    private String description;
    private String location;
    private boolean isEmergency;

    // Getters and setters
}