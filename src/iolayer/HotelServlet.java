package iolayer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HotelDAO;
import model.Hotel;

public class HotelServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
 
        int start = 0;
        int count = 10;
 
        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数start时
        }
 
        int next = start + count;
        int pre = start - count;
 
        int total = new HotelDAO().getTotal();
 
        int last;
        if (0 == total % count)
            last = total - count;
        else
            last = total - total % count;
 
        pre = pre < 0 ? 0 : pre;
        next = next > last ? last : next;
 
        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);
 
        List<Hotel> hotels = new HotelDAO().list(start, count);
        request.setAttribute("hotels", hotels);
        request.getRequestDispatcher("Hotel.jsp").forward(request, response);
 
    }
}
