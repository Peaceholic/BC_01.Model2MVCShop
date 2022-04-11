<%@ page contentType="text/html; charset=EUC-KR" %>

<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>

<%
	PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO");
	System.out.println("aaa" +purchaseVO);
%>

<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
<!--
function fncupdatePurchase() {
	document.updatePurchase.submit();
}
-->
</script>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=purchaseVO.getTranNo()%>" method="post">

다음과 같이 구매가 되었습니다.

<table border=1 cellspacing="5">
	<tr>
		<td width="200" height="50">물품번호</td>
		<td><%=purchaseVO.getPurchaseProd().getProdNo() %></td>

	</tr>
	<tr>
		<td width="200" height="50">구매자아이디</td>
		<td><%=purchaseVO.getBuyer().getUserId() %></td>

	</tr>
	<tr>
		<td width="200" height="50">구매방법</td>
		<td>
		<%if(purchaseVO.getPaymentOption().trim().equals("1")){%>
			현금구매
		<%}else{%>
			신용구매
		<%} %>
		</td>
	
	</tr>
	<tr>
		<td width="200" height="50">구매자이름</td>
		<td><%=purchaseVO.getReceiverName() %></td>

	</tr>
	<tr>
		<td width="200" height="50">구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>

	</tr>
	<tr>
		<td width="200" height="50">구매자주소</td>
		<td width="500" ><%=purchaseVO.getDivyAddr() %></td>

	</tr>
		<tr>
		<td width="200" height="50">구매요청사항</td>
		<td><%=purchaseVO.getDivyRequest() %></td>

	</tr>
	<tr>
		<td width="200" height="50">배송희망일자</td>
		<td><%=purchaseVO.getDivyDate() %></td>
	</tr>
</table>

</form>

</body>
</html>