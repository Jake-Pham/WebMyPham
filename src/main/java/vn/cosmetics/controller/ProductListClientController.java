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

/**
 * Servlet implementation class CategoryListController
 */

public class ProductListClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServicesImpl();
	ProductService productService = new ProductServiceImpl();
	DecimalFormat df = new DecimalFormat("#.000");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = req.getParameter("id");
		List<Catalog> cateList = cateService.getAll();
		req.setAttribute("catelist", cateList);
		List<Product> productList = productService.getAll();
		req.setAttribute("productlist", productList);
		
	
		 
	

		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product.jsp");
		dispatcher.forward(req, resp);
	}

}
