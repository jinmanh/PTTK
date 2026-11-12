<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.ImportControl6, model.Supplier6"%>
<%
    request.setCharacterEncoding("UTF-8");
    Supplier6 supplier = new Supplier6();
    supplier.setSupplierCode(request.getParameter("supplierCode6"));
    supplier.setSupplierName(request.getParameter("supplierName6"));
    supplier.setAddress(request.getParameter("address6"));
    supplier.setPhone(request.getParameter("phone6"));
    supplier.setEmail(request.getParameter("email6"));
    supplier.setDescription(request.getParameter("description6"));

    boolean saved = new ImportControl6().addSupplier(supplier);
    String message = saved ? "Thêm nhà cung cấp thành công." : "Thêm thất bại. Hãy kiểm tra dữ liệu hoặc mã nhà cung cấp đã tồn tại.";
    response.sendRedirect("gdChonNCC6.jsp?outMessage6=" + java.net.URLEncoder.encode(message, "UTF-8"));
%>
