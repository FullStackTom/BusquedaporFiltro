package com.gestion.productos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.productos.Model.Producto;
import com.gestion.productos.Repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repository;
	
	
	public List<Producto> listAll(String palabraClave){
		if(palabraClave != null) {
			return repository.findAll(palabraClave);
		}
		return repository.findAll();
	}
	
	
	public void save(Producto producto) {
		repository.save(producto);
	}
	
	public Producto get(Long id) {
		return repository.findById(id).get();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
