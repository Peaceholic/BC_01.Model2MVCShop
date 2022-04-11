package com.model2.mvc.view.product;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("\n* [ UpdateProductAction : execute() ] ");

		ProductVO productVO = new ProductVO();
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println("* prodNo : "+ request.getParameter("prodNo"));
		
		productVO.setProdNo(prodNo);

		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));

		String manuDate = (String) request.getParameter("manuDate");
		productVO.setManuDate(manuDate);

		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));

		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);

		Date regDate = service.getProduct(prodNo).getRegDate();
		request.setAttribute("regDate", regDate);
		request.setAttribute("productVO", productVO);
		
		return "redirect:/getProduct.do?prodNo=" + prodNo + "&menu=ok";
	}
}
