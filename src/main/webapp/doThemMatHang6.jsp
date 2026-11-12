<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.ImportControl6, model.Item6"%>
<%
    request.setCharacterEncoding("UTF-8");
    Item6 item = new Item6();
    item.setItemCode(request.getParameter("itemCode6"));
    item.setItemName(request.getParameter("itemName6"));
    item.setCategory(request.getParameter("category6"));
    item.setSpecifications(request.getParameter("specifications6"));

    boolean saved = false;
    try {
        item.setPrice(Double.parseDouble(request.getParameter("price6")));
        item.setStockQuantity(Integer.parseInt(request.getParameter("stockQuantity6")));
        saved = new ImportControl6().addItem(item);
    } catch (NumberFormatException e) {
        response.sendRedirect("gdThemMatHang6.jsp?outMessage6=" + java.net.URLEncoder.encode("Giá và tồn kho phải là số hợp lệ.", "UTF-8"));
        return;
    }

    String message = saved ? "Thêm mặt hàng thành công." : "Thêm thất bại. Hãy kiểm tra dữ liệu hoặc mã mặt hàng đã tồn tại.";
    response.sendRedirect("gdChonMathang6.jsp?outMessage6=" + java.net.URLEncoder.encode(message, "UTF-8"));
%>
