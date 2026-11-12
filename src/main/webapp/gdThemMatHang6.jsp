<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mặt hàng</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <h1>Thêm mặt hàng mới</h1>
        <%
            String message = request.getParameter("outMessage6");
            if (message != null && !message.isEmpty()) {
                out.println("<p class=\"message\">" + message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>");
            }
        %>
        <form action="doThemMatHang6.jsp" method="post" class="content-form">
            <table class="form-table">
                <tr><td>Mã mặt hàng:</td><td><input type="text" name="itemCode6" required maxlength="50"></td></tr>
                <tr><td>Tên mặt hàng:</td><td><input type="text" name="itemName6" required maxlength="150"></td></tr>
                <tr><td>Danh mục:</td><td><input type="text" name="category6" maxlength="100"></td></tr>
                <tr><td>Thông số:</td><td><input type="text" name="specifications6"></td></tr>
                <tr><td>Giá bán:</td><td><input type="number" name="price6" min="0" step="0.01" value="0" required></td></tr>
                <tr><td>Tồn kho ban đầu:</td><td><input type="number" name="stockQuantity6" min="0" value="0" required></td></tr>
                <tr><td colspan="2"><div class="form-actions">
                    <button type="submit" id="subSaveItem6">Lưu mặt hàng</button>
                    <a href="gdChonMathang6.jsp" class="button">Quay lại</a>
                </div></td></tr>
            </table>
        </form>
    </main>
</body>
</html>
