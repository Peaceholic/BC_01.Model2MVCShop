package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
	}

	public void addPurchase(PurchaseVO purchaseVO) throws Exception {

		System.out.println("\n* [ PurchaseDAO : insertPurchase() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, '1', sysdate, ?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getDivyDate());
		stmt.executeUpdate();

		con.close();
	}
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {

		System.out.println("\n* [ PurchaseDAO : getPurchaseList() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION where BUYER_ID='" + buyerId + "'";

		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();

		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {

				UserVO userVO = new UserVO();
				ProductVO productVO = new ProductVO();
				PurchaseVO purchaseVO = new PurchaseVO();
				purchaseVO.setTranNo(rs.getInt(1));
				productVO.setProdNo(rs.getInt(2));
				purchaseVO.setPurchaseProd(productVO);
				userVO.setUserId(rs.getString(3));
				purchaseVO.setBuyer(userVO);
				purchaseVO.setPaymentOption(rs.getString(4));
				purchaseVO.setReceiverName(rs.getString(5));
				purchaseVO.setReceiverPhone(rs.getString(6));
				purchaseVO.setDivyAddr(rs.getString(7));
				purchaseVO.setDivyRequest(rs.getString(8));
				purchaseVO.setTranCode(rs.getString(9));
				purchaseVO.setOrderDate(rs.getDate(10));
				purchaseVO.setDivyDate(rs.getString(11));

				list.add(purchaseVO);

				if (!rs.next())
					break;
			}
		}
		map.put("list", list);

		con.close();

		return map;
	}
	
	public PurchaseVO getPurchaseByTran(int tranNo) throws Exception {

		System.out.println("\n* [ PurchaseDAO : findPurchase() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION where TRAN_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		ResultSet rs = stmt.executeQuery();

		UserVO userVO = null;
		ProductVO productVO = null;
		PurchaseVO purchaseVO = null;

		while (rs.next()) {
			userVO = new UserVO();
			productVO = new ProductVO();
			purchaseVO = new PurchaseVO();
			purchaseVO.setTranNo(rs.getInt(1));
			productVO.setProdNo(rs.getInt(2));
			purchaseVO.setPurchaseProd(productVO);
			userVO.setUserId(rs.getString(3));
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPaymentOption(rs.getString(4));
			purchaseVO.setReceiverName(rs.getString(5));
			purchaseVO.setReceiverPhone(rs.getString(6));
			purchaseVO.setDivyAddr(rs.getString(7));
			purchaseVO.setDivyRequest(rs.getString(8));
			purchaseVO.setTranCode(rs.getString(9));
			purchaseVO.setOrderDate(rs.getDate(10));
			purchaseVO.setDivyDate(rs.getString(11));
		}

		con.close();

		return purchaseVO;
	}
	
	public PurchaseVO getPurchaseByProd(int prodNo) throws Exception {

		System.out.println("\n* [ PurchaseDAO : findPurchase2() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION where PROD_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		UserVO userVO = null;
		ProductVO productVO = null;
		PurchaseVO purchaseVO = null;

		while (rs.next()) {
			userVO = new UserVO();
			productVO = new ProductVO();
			purchaseVO = new PurchaseVO();
			purchaseVO.setTranNo(rs.getInt(1));
			productVO.setProdNo(rs.getInt(2));
			purchaseVO.setPurchaseProd(productVO);
			userVO.setUserId(rs.getString(3));
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPaymentOption(rs.getString(4));
			purchaseVO.setReceiverName(rs.getString(5));
			purchaseVO.setReceiverPhone(rs.getString(6));
			purchaseVO.setDivyAddr(rs.getString(7));
			purchaseVO.setDivyRequest(rs.getString(8));
			purchaseVO.setTranCode(rs.getString(9));
			purchaseVO.setOrderDate(rs.getDate(10));
			purchaseVO.setDivyDate(rs.getString(11));
		}

		con.close();

		return purchaseVO;
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {

		System.out.println("\n* [ PurchaseDAO : updatePurchase() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION set RECEIVER_PHONE=?, DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=? where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getReceiverPhone());
		stmt.setString(2, purchaseVO.getDivyAddr());
		stmt.setString(3, purchaseVO.getDivyRequest());
		stmt.setString(4, purchaseVO.getDivyDate());
		stmt.setInt(5, purchaseVO.getTranNo());
		stmt.executeUpdate();

		con.close();
	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {

		System.out.println("\n* [ PurchaseDAO : updateTranCode() ] ");

		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION set TRAN_STATUS_CODE=? where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getTranNo());
		stmt.executeUpdate();

		con.close();
	}
	
}