<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.ImportControl6, model.Supplier6, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm Nhà cung cấp - SupplierSearchFrm6</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <h1>Chọn nhà cung cấp</h1>
        <form action="gdChonNCC6.jsp" method="get" class="search-form">
            <label>Từ khóa NCC:</label>
            <input type="text" name="inSupplierKeyword6" id="inSupplierKeyword6" placeholder="Nhập tên hoặc mã nhà cung cấp" value="<%= request.getParameter("inSupplierKeyword6") != null ? request.getParameter("inSupplierKeyword6") : "" %>" />
            <button type="submit" id="subSearchSupplier6">Tìm</button>
        </form>
        <%
            String message = request.getParameter("outMessage6");
            if (message != null && !message.isEmpty()) {
                out.println("<p class=\"message\">" + message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>");
            }
        %>
        <table id="inoutSupplierTable6" class="data-table">
            <thead>
                <tr>
                    <th class="center">Mã</th>
                    <th>Tên nhà cung cấp</th>
                    <th>SĐT</th>
                    <th>Chọn</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String kw = request.getParameter("inSupplierKeyword6");
                    ImportControl6 ctrl = new ImportControl6();
                    List<Supplier6> list = ctrl.searchSupplier(kw);
                    if (list != null && !list.isEmpty()) {
                        for (Supplier6 sup : list) {
                %>
                <tr>
                    <td class="center"><%= sup.getSupplierCode() %></td>
                    <td><%= sup.getSupplierName() %></td>
                    <td><%= sup.getPhone() %></td>
                    <td>
                        <a href="gdLapHDNhap6.jsp?supplierId=<%= sup.getId() %>&supplierName=<%= java.net.URLEncoder.encode(sup.getSupplierName(), "UTF-8") %>" class="back-link">Chọn</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="center">Không tìm thấy nhà cung cấp phù hợp.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <div class="home-actions">
            <a href="gdChinhKho6.jsp" class="button">Quay lại</a>
            <a href="gdThemNCC6.jsp" id="subAddSupplier6" class="button">Thêm NCC mới</a>
        </div>
    </main>
</body>
</html>
