<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="control.ImportControl6, model.*, java.util.*"%>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    List<ImportDetail6> cart = (List<ImportDetail6>) session.getAttribute("importCart6");
    if (cart == null) cart = new ArrayList<>();

    if ("addDetail".equals(action)) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int qty = Integer.parseInt(request.getParameter("inQuantity6"));
        double importPrice = Double.parseDouble(request.getParameter("inImportPrice6"));

        ItemDAO6 itemDAO = new ItemDAO6();
        List<Item6> items = itemDAO.getItemByKeyword("");
        Item6 selectedItem = null;
        for (Item6 it : items) {
            if (it.getId() == itemId) {
                selectedItem = it;
                break;
            }
        }

        if (selectedItem != null) {
            ImportDetail6 detail = new ImportDetail6();
            detail.setItem(selectedItem);
            detail.setQuantity(qty);
            detail.setImportPrice(importPrice);
            cart.add(detail);
            session.setAttribute("importCart6", cart);
        }
        response.sendRedirect("gdLapHDNhap6.jsp");
        return;
    }

    // Default: Save invoice
    String supIdStr = (String) session.getAttribute("selectedSupplierId");
    if (supIdStr == null || cart.isEmpty()) {
        response.sendRedirect("gdLapHDNhap6.jsp");
        return;
    }

    ImportInvoice6 invoice = new ImportInvoice6();
    invoice.setImportCode("HDN" + System.currentTimeMillis() % 100000);
    Supplier6 sup = new Supplier6();
    sup.setId(Integer.parseInt(supIdStr));
    invoice.setSupplier(sup);
    invoice.setDetails(cart);

    double total = 0;
    for (ImportDetail6 dt : cart) {
        total += dt.getQuantity() * dt.getImportPrice();
    }
    invoice.setTotalValue(total);

    ImportControl6 ctrl = new ImportControl6();
    boolean res = ctrl.saveImportInvoice(invoice);

    if (res) {
        session.removeAttribute("importCart6");
        session.removeAttribute("selectedSupplierId");
        session.removeAttribute("selectedSupplierName");
        out.println("<script>alert('Lưu hóa đơn nhập kho thành công!'); window.location.href='gdChinhKho6.jsp';</script>");
    } else {
        out.println("<script>alert('Lưu hóa đơn thất bại!'); window.location.href='gdLapHDNhap6.jsp';</script>");
    }
%>
