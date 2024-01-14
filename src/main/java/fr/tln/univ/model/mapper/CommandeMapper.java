package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommandeMapper {
    public abstract Commande mapCommandeToCommandeDto(Commande commande);
    public abstract Commande mapCommandeDtoToCommande(CommandeDto commandeDto);


}
