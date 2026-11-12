<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ImportDetail6, java.util.List, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lập Hóa đơn nhập kho - ImportInvoiceFrm6</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <h1>Lập hóa đơn nhập</h1>
        <%
            String supName = request.getParameter("supplierName");
            if (supName == null) supName = (String) session.getAttribute("selectedSupplierName");
            else session.setAttribute("selectedSupplierName", supName);

            String supIdStr = request.getParameter("supplierId");
            if (supIdStr == null) supIdStr = (String) session.getAttribute("selectedSupplierId");
            else session.setAttribute("selectedSupplierId", supIdStr);

            List<ImportDetail6> cart = (List<ImportDetail6>) session.getAttribute("importCart6");
            if (cart == null) cart = new ArrayList<>();

            double totalAmount = 0;
            for (ImportDetail6 dt : cart) {
                totalAmount += dt.getQuantity() * dt.getImportPrice();
            }
        %>
        <div class="supplier-info">
            <b>Nhà cung cấp:</b>
            <span id="outSupplierInfo6"><%= supName != null ? supName : "Chưa chọn" %></span>
            <b>Mã nhà cung cấp:</b>
            <span><%= supIdStr != null ? supIdStr : "-" %></span>
        </div>

        <table id="inoutImportDetailTable6" class="data-table">
            <thead>
                <tr>
                    <th class="center">Mã MH</th>
                    <th>Tên mặt hàng</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Thành tiền (VNĐ)</th>
                    <th>Sửa/Xóa</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (!cart.isEmpty()) {
                        for (ImportDetail6 dt : cart) {
                %>
                <tr>
                    <td class="center"><%= dt.getItem().getItemCode() %></td>
                    <td><%= dt.getItem().getItemName() %></td>
                    <td><%= dt.getQuantity() %></td>
                    <td><%= String.format("%,.0f", dt.getImportPrice()) %></td>
                    <td><%= String.format("%,.0f", dt.getQuantity() * dt.getImportPrice()) %></td>
                    <td>-</td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="center">Chưa có mặt hàng trong hóa đơn.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <p class="total">Tổng giá trị nhập: <span id="outTotalAmount6"><%= String.format("%,.0f", totalAmount) %> VNĐ</span></p>
        <div class="home-actions">
            <a href="gdChonMathang6.jsp" id="subAddItem6" class="button">Thêm mặt hàng</a>
            <a href="doLuuHDNhap6.jsp" id="subSaveInvoice6" class="button">Lưu hóa đơn</a>
            <a href="gdChinhKho6.jsp" id="subCancel6" class="button">Hủy / Quay lại</a>
        </div>
    </main>
</body>
</html>
