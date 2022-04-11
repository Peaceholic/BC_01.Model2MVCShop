package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {

	public ProductDAO() {
	}

	public ProductVO findProduct(int prodNo) throws Exception {

		System.out.println("\n* [ ProductDAO : findProduct ] ");

		Connection con = DBUtil.getConnection();

		String sql = "select * from PRODUCT where PROD_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;

		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}

		con.close();

		return productVO;
	}

	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {

		System.out.println("\n* [ ProductDAO : getProductList() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "select * from product p ,transaction t where t.prod_no(+)=p.prod_no";

		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " and P.PROD_NO='" + searchVO.getSearchKeyword() + "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " and lower(PROD_NAME) LIKE lower('%" + searchVO.getSearchKeyword() + "%')";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += " and PRICE = '" + searchVO.getSearchKeyword() + "'";
			}
		}
		sql += " order by P.PROD_NO";

		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);

		ArrayList<ProductVO> arrayList = new ArrayList<ProductVO>();

		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {

				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt(1));
				productVO.setProdName(rs.getString("PROD_NAME"));
				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setFileName(rs.getString("IMAGE_FILE"));
				productVO.setRegDate(rs.getDate("REG_DATE"));
				if (rs.getString("TRAN_STATUS_CODE") == null) {
					productVO.setProTranCode("0");
				} else {
					productVO.setProTranCode(rs.getString("TRAN_STATUS_CODE").trim());
				}
				arrayList.add(productVO);

				if (!rs.next())
					break;
			}
		}
		map.put("list", arrayList);

		con.close();

		return map;
	}

	public void insertProduct(ProductVO provo) throws Exception {

		System.out.println("\n* [ ProductDAO : insertProduct() ] ");

		Connection conn = DBUtil.getConnection();

		String sql = "INSERT INTO product VALUES (seq_product_prod_no.nextval, ?, ?, ?, ?, ?, SYSDATE)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, provo.getProdName());
		pstmt.setString(2, provo.getProdDetail());
		pstmt.setString(3, provo.getManuDate());
		pstmt.setInt(4, provo.getPrice());
		pstmt.setString(5, provo.getFileName());
		pstmt.executeUpdate();

		conn.close();
	}

	public void updateProduct(ProductVO provo) throws Exception {

		System.out.println("\n* [ ProductDAO : updateProduct() ] ");

		Connection conn = DBUtil.getConnection();

		String sql = "UPDATE product set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, provo.getProdName());
		pstmt.setString(2, provo.getProdDetail());
		pstmt.setString(3, provo.getManuDate());
		pstmt.setInt(4, provo.getPrice());
		pstmt.setString(5, provo.getFileName());
		pstmt.setInt(6, provo.getProdNo());
		pstmt.executeUpdate();

		conn.close();
	}
}