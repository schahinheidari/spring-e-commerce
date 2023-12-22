package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommandeMapper {

    @Mapping(target = "commandeId", source = "id", expression = "java{new java.util.Date}")
    public abstract CommandeDto mapCommandeToCommandeDto(Commande commande);

}
