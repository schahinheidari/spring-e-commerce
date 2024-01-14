package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.model.entities.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProduitMapper {
    public abstract ProduitDto mapProduitToProduitDto(Produit produit);

}
