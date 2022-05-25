package vn.cosmetics.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.cosmetics.model.Catalog;
import vn.cosmetics.model.Product;
import vn.cosmetics.service.CategoryService;
import vn.cosmetics.service.ProductService;
import vn.cosmetics.service.impl.CategoryServicesImpl;
import vn.cosmetics.service.impl.ProductServiceImpl;

public class ProductListOfCategoryClientController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServicesImpl();
	ProductService productService = new ProductServiceImpl();
	DecimalFormat df = new DecimalFormat("#.000");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Catalog> cateList = cateService.getAll();
		req.setAttribute("catelist", cateList);
		String id = req.getParameter("id");
		/*
		 * List<Product> productList =
		 * productService.getProductById(Integer.parseInt(id));
		 * req.setAttribute("productlist", productList); //Giá giảm List<Product>
		 * productsList1 = new ArrayList<Product>(); for(Product product: productList) {
		 * Product product1 = productService.get(Integer.parseInt(product.getId()));
		 * product1.setPrice(String.valueOf(df.format(Double.parseDouble(product.
		 * getPrice()) * (1 - (Double.parseDouble(product.getDiscount())/100)))));
		 * productsList1.add(product1);
		 * 
		 * }
		 * 
		 * req.setAttribute("productlist1", productsList1);
		 */
		req.setAttribute("tagactive", id);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product.jsp");
		dispatcher.forward(req, resp);
	}

}
