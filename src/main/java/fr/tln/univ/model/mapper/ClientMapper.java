package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public ClientDto mapClientToClientDto(Client client) {
        ClientDto clientDto = null;
        if (client != null) {
            clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            clientDto.setFamily(client.getFamily());
            clientDto.setEmail(client.getEmail());
            clientDto.setPassword(client.getPassword());
            List<CommandeDto> commandeDtoList = new ArrayList<>();
            List<Commande> commandeList = client.getCommandeList();

            for (Commande co : commandeList) {
                CommandeDto commandeDto = new CommandeDto();
                commandeDto.setId(co.getId());
                commandeDto.setCount(co.getCount());
                commandeDtoList.add(commandeDto);
            }
            clientDto.setCommandeDtoList(commandeDtoList);
        }
        return clientDto;
    }

    /*
// --> example
   @FullMapping
   @IterableMapping(qualifiedBy = {FullMapping.class})
   public List<DTO> loads(List<ENTITY> data);
   */
        /*
// --> example
   @IterableMapping(qualifiedByName = "client")
   @Mapping(target = "clientId", source = "id")
   public List<DTO> loads(List<ENTITY> data);
   */
    public List<ClientDto> listClientToListClientDtoMapper(List<Client> clientList){
        List<ClientDto> clientDtoList = new ArrayList<>();

        for (Client client : clientList) {
            ClientDto clientDto = mapClientToClientDto(client);
            clientDtoList.add(clientDto);
        }
        return clientDtoList;
    }
 /*   @IterableMapping(qualifiedByName = "client")
    @Mapping(target = "clientId", source = "id")
    public abstract List<ClientDto> listClientToListClientDtoMapper(List<Client> clientList);*/
}
