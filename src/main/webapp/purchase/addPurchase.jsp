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

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1 cellspacing="5">
	<tr>
		<td width="200" height="50">��ǰ��ȣ</td>
		<td><%=purchaseVO.getPurchaseProd().getProdNo() %></td>

	</tr>
	<tr>
		<td width="200" height="50">�����ھ��̵�</td>
		<td><%=purchaseVO.getBuyer().getUserId() %></td>

	</tr>
	<tr>
		<td width="200" height="50">���Ź��</td>
		<td>
		<%if(purchaseVO.getPaymentOption().trim().equals("1")){%>
			���ݱ���
		<%}else{%>
			�ſ뱸��
		<%} %>
		</td>
	
	</tr>
	<tr>
		<td width="200" height="50">�������̸�</td>
		<td><%=purchaseVO.getReceiverName() %></td>

	</tr>
	<tr>
		<td width="200" height="50">�����ڿ���ó</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>

	</tr>
	<tr>
		<td width="200" height="50">�������ּ�</td>
		<td width="500" ><%=purchaseVO.getDivyAddr() %></td>

	</tr>
		<tr>
		<td width="200" height="50">���ſ�û����</td>
		<td><%=purchaseVO.getDivyRequest() %></td>

	</tr>
	<tr>
		<td width="200" height="50">����������</td>
		<td><%=purchaseVO.getDivyDate() %></td>
	</tr>
</table>

</form>

</body>
</html>