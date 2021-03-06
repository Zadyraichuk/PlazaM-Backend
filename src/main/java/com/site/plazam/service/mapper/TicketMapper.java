package com.site.plazam.service.mapper;

import com.site.plazam.domain.Ticket;
import com.site.plazam.dto.SeanceForTicketDTO;
import com.site.plazam.dto.parents.TicketSimpleDTO;
import com.site.plazam.service.SeanceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TicketMapper {

    @Autowired
    SeanceService ss;

    @Mapping(source = "seance.id", target = "seanceId")
    public abstract Ticket toEntity(TicketSimpleDTO ticketSimpleDTO);

    @Mapping(source = "seanceId", target = "seance", qualifiedByName =
            "toSeance")
    public abstract TicketSimpleDTO toDTO(Ticket ticket);

    SeanceForTicketDTO toSeance(String id) {
        return ss.findSeanceForTicketById(id);
    }
}
