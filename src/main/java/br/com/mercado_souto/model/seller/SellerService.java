package br.com.mercado_souto.model.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercado_souto.model.client.Client;
import br.com.mercado_souto.model.client.ClientRepository;
import br.com.mercado_souto.util.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Seller create(Long idClient,Seller seller){
        Client clientSeller=clientRepository.findById(idClient)
            .orElseThrow(()-> new EntityNotFoundException("Client",idClient));

        seller.setClient(clientSeller);
        seller.setActive(Boolean.TRUE);
        
        return sellerRepository.save(seller);


    }

    public List<Seller> findAll(){

        return sellerRepository.findAll();
    }

    
    public Seller findById(Long id){
        Seller seller = sellerRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Seller",id));
        
        return seller;
    }


    @Transactional
    public Seller update (Long id, Seller modifiedSeller){
        Seller seller = findById(id);
        seller.setCnpj(modifiedSeller.getCnpj());
        seller.setSales(modifiedSeller.getSales());
        seller.setBalance(modifiedSeller.getBalance());
       
       
        return sellerRepository.save(seller);
    }

    @Transactional
    public void delete(Long id){
        Seller seller = findById(id);
        seller.setActive(Boolean.FALSE);

        sellerRepository.save(seller);
    }

}
