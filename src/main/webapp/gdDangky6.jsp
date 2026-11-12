<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký thành viên - RegisterFrm6</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <main class="page">
        <a href="gdChinhKH6.jsp" class="back-link">← Trang chủ</a>
        <h1>ĐĂNG KÝ THÀNH VIÊN SIÊU THỊ ĐIỆN MÁY</h1>
        <%
            String msg = request.getParameter("outMessage6");
            if (msg != null && !msg.isEmpty()) {
                out.println("<p id='outMessage6' class='message'>" + msg.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>");
            }
        %>
        <form action="doDangky6.jsp" method="post" class="content-form">
            <table class="form-table">
                <tr>
                    <td>Họ và tên:</td>
                    <td><input type="text" name="inFullName6" id="inFullName6" required /></td>
                </tr>
                <tr>
                    <td>Ngày sinh:</td>
                    <td><input type="date" name="inDob6" id="inDob6" /></td>
                </tr>
                <tr>
                    <td>Địa chỉ:</td>
                    <td><input type="text" name="inAddress6" id="inAddress6" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="inEmail6" id="inEmail6" required /></td>
                </tr>
                <tr>
                    <td>Số điện thoại:</td>
                    <td><input type="text" name="inPhone6" id="inPhone6" /></td>
                </tr>
                <tr>
                    <td>Tên đăng nhập:</td>
                    <td><input type="text" name="inUsername6" id="inUsername6" required /></td>
                </tr>
                <tr>
                    <td>Mật khẩu:</td>
                    <td><input type="password" name="inPassword6" id="inPassword6" required /></td>
                </tr>
                <tr>
                    <td>Xác nhận mật khẩu:</td>
                    <td><input type="password" name="inConfirmPassword6" id="inConfirmPassword6" required /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="form-actions">
                            <button type="submit" id="subSubmit6">Đăng ký</button>
                            <a href="gdChinhKH6.jsp" id="subCancel6" class="button">Hủy</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </main>
</body>
</html>
