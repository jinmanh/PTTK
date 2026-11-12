<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm nhà cung cấp</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <h1>Thêm nhà cung cấp</h1>
        <%
            String message = request.getParameter("outMessage6");
            if (message != null && !message.isEmpty()) {
                out.println("<p class=\"message\">" + message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>");
            }
        %>
        <form action="doThemNCC6.jsp" method="post" class="content-form">
            <table class="form-table">
                <tr><td>Mã nhà cung cấp:</td><td><input type="text" name="supplierCode6" required maxlength="50"></td></tr>
                <tr><td>Tên nhà cung cấp:</td><td><input type="text" name="supplierName6" required maxlength="100"></td></tr>
                <tr><td>Địa chỉ:</td><td><input type="text" name="address6" maxlength="255"></td></tr>
                <tr><td>Số điện thoại:</td><td><input type="text" name="phone6" maxlength="20"></td></tr>
                <tr><td>Email:</td><td><input type="email" name="email6" maxlength="100"></td></tr>
                <tr><td>Mô tả:</td><td><input type="text" name="description6"></td></tr>
                <tr><td colspan="2"><div class="form-actions">
                    <button type="submit" id="subSaveSupplier6">Lưu nhà cung cấp</button>
                    <a href="gdChonNCC6.jsp" class="button">Quay lại</a>
                </div></td></tr>
            </table>
        </form>
    </main>
</body>
</html>
