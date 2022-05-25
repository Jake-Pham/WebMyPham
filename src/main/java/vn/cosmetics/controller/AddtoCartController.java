package vn.cosmetics.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.cosmetics.model.Item;
import vn.cosmetics.model.Order;
import vn.cosmetics.model.Product;
import vn.cosmetics.service.ProductService;
import vn.cosmetics.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class AddtoCartController
 */
public class AddtoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	DecimalFormat df = new DecimalFormat("#.000");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n= 0;
		int qty = 1;
		String id;
		if(request.getParameter("product-id")!=null) {
			id = request.getParameter("product-id");
			Product product = productService.get(Integer.parseInt(id));;
			if(product != null) {
				if(request.getParameter("qty")!=null) {
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				
				HttpSession session = request.getSession();
				if(session.getAttribute("order") == null) {
					Order order = new Order();
					List<Item> listItems = new ArrayList<Item>();
					Item item = new Item();
					item.setQty(qty);
					item.setProduct(product);
					item.setPrice(Double.parseDouble(product.getPrice()) );
					order.setSumPrice(0);
					order.setSumPrice(order.getSumPrice() + item.getPrice());
					listItems.add(item);
					order.setItems(listItems);
					n = listItems.size();
					session.setAttribute("length_order",n);
					session.setAttribute("order", order);
					session.setAttribute("sumprice", df.format(order.getSumPrice()));
				} else {
					Order order = (Order) session.getAttribute("order");
					List<Item> listItems = order.getItems();
					boolean check = false;
					for(Item item : listItems) {
						if(Integer.parseInt(item.getProduct().getId()) == Integer.parseInt(product.getId())) {
							item.setQty(item.getQty() + qty);
							order.setSumPrice(order.getSumPrice() + Double.parseDouble(item.getProduct().getPrice()));
							item.setPrice(item.getPrice() + (Double.parseDouble(item.getProduct().getPrice())));
							check = true;
						}
					}
					if(check == false) {
						Item item = new Item();
						item.setQty(qty);
						item.setProduct(product);
						item.setPrice(Double.parseDouble(product.getPrice()) );
						order.setSumPrice(order.getSumPrice() + Double.parseDouble(item.getProduct().getPrice()) );
						listItems.add(item);
					}
					n = listItems.size();
					session.setAttribute("length_order",n);
					session.setAttribute("order", order);
					session.setAttribute("sumprice", df.format(order.getSumPrice()));
				}
			}
			response.sendRedirect(request.getContextPath() + "/view/client/product");
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
		
	}

}
