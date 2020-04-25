package iolayer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DAO.HotelDAO;
import DAO.RestaurantDAO;
import DAO.ShopDAO;
import DAO.TravelDAO;
import model.Comment;
import model.Hotel;
import model.Restaurant;
import model.Shop;
import model.Travel;

public class HomeServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
 
        int start = 0;
        int count = 3;
 
        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数start时
        }
 
        int next = start + count;
        int pre = start - count;
 
        int total = new CommentDAO().getTotal();
 
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
 
        List<Comment> comments = new CommentDAO().list(start, count);
        List<Hotel> hotels=new HotelDAO().list(start,count);
        List<Restaurant> rests=new RestaurantDAO().list(start,count);
        List<Travel>travels=new TravelDAO().list(start,count);
        List<Shop>shops=new ShopDAO().list(start,count);
        request.setAttribute("hotels", hotels);
        request.setAttribute("comments", comments);
        request.setAttribute("rests", rests);
        request.setAttribute("travels", travels);
        request.setAttribute("shops", shops);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
 
}
}
