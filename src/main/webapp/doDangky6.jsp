<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.CustomerControl6, model.Customer6, java.text.SimpleDateFormat"%>
<%
    request.setCharacterEncoding("UTF-8");
    String fullName = request.getParameter("inFullName6");
    String dobStr = request.getParameter("inDob6");
    String address = request.getParameter("inAddress6");
    String email = request.getParameter("inEmail6");
    String phone = request.getParameter("inPhone6");
    String username = request.getParameter("inUsername6");
    String password = request.getParameter("inPassword6");
    String confirmPassword = request.getParameter("inConfirmPassword6");

    if (password == null || !password.equals(confirmPassword)) {
        response.sendRedirect("gdDangky6.jsp?outMessage6=" + java.net.URLEncoder.encode("Mật khẩu xác nhận không trùng khớp!", "UTF-8"));
        return;
    }

    Customer6 cust = new Customer6();
    cust.setFullName(fullName);
    cust.setAddress(address);
    cust.setEmail(email);
    cust.setPhone(phone);
    cust.setUsername(username);
    cust.setPassword(password);
    if (dobStr != null && !dobStr.isEmpty()) {
        try {
            cust.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dobStr));
        } catch(Exception e) {}
    }

    CustomerControl6 ctrl = new CustomerControl6();
    boolean res = ctrl.registerCustomer(cust);

    if (res) {
        response.sendRedirect("gdDangky6.jsp?outMessage6=" + java.net.URLEncoder.encode("Đăng ký thành viên thành công!", "UTF-8"));
    } else {
        response.sendRedirect("gdDangky6.jsp?outMessage6=" + java.net.URLEncoder.encode("Đăng ký thất bại! Tên đăng nhập/Email có thể đã tồn tại hoặc cơ sở dữ liệu chưa sẵn sàng.", "UTF-8"));
    }
%>
