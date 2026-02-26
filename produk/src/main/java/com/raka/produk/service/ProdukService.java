package com.raka.produk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raka.produk.model.Produk;
import com.raka.produk.repository.ProdukRepository;

@Service
public class ProdukService {
  @Autowired
  private ProdukRepository produkRepository;

  public List<Produk> getAllProduks(){
    return produkRepository.findAll();
  }

  public Produk getProdukById(Long Id){
    return produkRepository.findById(Id).orElse(null);
  }

  public Produk createProduk(Produk produk){
    return produkRepository.save(produk);
  }

  public void deleteProduk (Long id){
    produkRepository.deleteById(id);
  }
}
