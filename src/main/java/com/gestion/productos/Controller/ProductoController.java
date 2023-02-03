package com.gestion.productos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.productos.Model.Producto;
import com.gestion.productos.Service.ProductoService;


@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	
	@RequestMapping("/")
	public String verPaginaDeInicio(Model modelo,@Param("palabraClave") String palabraClave) {
		
		List<Producto> listaProductos = service.listAll(palabraClave);
		
		modelo.addAttribute("listaProductos", listaProductos);
		modelo.addAttribute("palabraClave",palabraClave);
		
		return "index";
		
	}
	
	@RequestMapping("/nuevo")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);
		return "nuevo_producto";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		service.save(producto);
		return "redirect:/";
	}
	
	
	@RequestMapping("/editar/{id}")
	public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable(name = "id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_producto");
		
		Producto producto= service.get(id);
		modelo.addObject("producto", producto);
		return modelo;
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	

}
