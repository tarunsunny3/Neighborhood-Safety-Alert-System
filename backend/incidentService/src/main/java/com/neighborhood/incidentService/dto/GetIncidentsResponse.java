package com.neighborhood.incidentService.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetIncidentsResponse {

    private List<IncidentDTO> incidents;
}
