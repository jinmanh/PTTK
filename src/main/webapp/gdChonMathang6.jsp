<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.ImportControl6, model.Item6, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tra cứu Mặt hàng - ItemSearchFrm6</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <h1>Chọn mặt hàng</h1>
        <form action="gdChonMathang6.jsp" method="get" class="search-form">
            <label>Từ khóa Mặt hàng:</label>
            <input type="text" name="inItemKeyword6" id="inItemKeyword6" placeholder="Nhập tên hoặc mã mặt hàng" value="<%= request.getParameter("inItemKeyword6") != null ? request.getParameter("inItemKeyword6") : "" %>" />
            <button type="submit" id="subSearchItem6">Tìm</button>
        </form>
        <table id="inoutItemTable6" class="data-table">
            <thead>
                <tr>
                    <th class="center">Mã</th>
                    <th>Tên mặt hàng</th>
                    <th>Tồn kho</th>
                    <th>Đơn giá</th>
                    <th>Chọn</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String kw = request.getParameter("inItemKeyword6");
                    String selectedId = request.getParameter("selectedItemId6");
                    ImportControl6 ctrl = new ImportControl6();
                    List<Item6> list = ctrl.searchItem(kw);
                    Item6 selectedItem = null;
                    if (list != null && !list.isEmpty()) {
                        for (Item6 item : list) {
                            if (selectedId != null && String.valueOf(item.getId()).equals(selectedId)) {
                                selectedItem = item;
                            }
                %>
                <tr>
                    <td class="center"><%= item.getItemCode() %></td>
                        <td><%= item.getItemName() %></td>
                        <td><%= item.getStockQuantity() %></td>
                    <td><%= String.format("%,.0f", item.getPrice()) %></td>
                    <td><a href="gdChonMathang6.jsp?selectedItemId6=<%= item.getId() %>&inItemKeyword6=<%= java.net.URLEncoder.encode(kw != null ? kw : "", "UTF-8") %>" class="back-link">Chọn</a></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="center">Không tìm thấy mặt hàng nào.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <%
            String message = request.getParameter("outMessage6");
            if (message != null && !message.isEmpty()) {
                out.println("<p class=\"message\">" + message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>");
            }
            if (selectedItem != null) {
        %>
        <p class="notice">Đã chọn: <b><%= selectedItem.getItemName() %></b> (tồn kho <%= selectedItem.getStockQuantity() %>).</p>
        <form action="doLuuHDNhap6.jsp" method="post" class="content-form">
            <input type="hidden" name="itemId" value="<%= selectedItem.getId() %>">
            <input type="hidden" name="action" value="addDetail">
            <table class="form-table">
                <tr>
                    <td>Số lượng nhập:</td>
                    <td><input type="number" name="inQuantity6" id="inQuantity6" min="1" value="1" required></td>
                </tr>
                <tr>
                    <td>Đơn giá nhập:</td>
                    <td><input type="number" name="inImportPrice6" id="inImportPrice6" min="0" value="<%= (long)selectedItem.getPrice() %>" required></td>
                </tr>
            </table>
            <div class="home-actions">
                <a href="gdLapHDNhap6.jsp" class="button">Quay lại hóa đơn</a>
                <button type="submit" id="subAddToInvoice6">Thêm vào hóa đơn</button>
            </div>
        </form>
        <%
            }
        %>
        <% if (selectedItem == null) { %>
        <div class="home-actions">
            <a href="gdLapHDNhap6.jsp" class="button">Quay lại hóa đơn</a>
            <a href="gdThemMatHang6.jsp" id="subCreateItem6" class="button">Thêm mặt hàng mới</a>
        </div>
        <% } %>
    </main>
</body>
</html>
