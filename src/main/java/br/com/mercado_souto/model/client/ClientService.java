package br.com.mercado_souto.model.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientService {
    @Autowired 
    private ClientRepository clientRepository;

    @Transactional
    public Client create (Client client){
        client.setActive(Boolean.TRUE);
        
        return clientRepository.save(client);
    }

    
    public List<Client> findAll(){

        return clientRepository.findAll();
    }

    
    public Client findById(Long id){
        Client client = clientRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Client not found with id: "+id));
        
        return client;
    }


    @Transactional
    public Client update (Long id, Client clientModified){
        Client client = findById(id);
        client.setName(clientModified.getName());
        client.setEmail(clientModified.getEmail());
        client.setPassword(clientModified.getPassword());
        client.setCpf(clientModified.getCpf());
        client.setPhone(clientModified.getPhone());

        return clientRepository.save(client);
    }

    @Transactional
    public void delete (Long id){
        Client client = findById(id);
        client.setActive(Boolean.FALSE);
    }
}
