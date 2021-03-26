package com.myclass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.SessionConstants;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@WebServlet(urlPatterns = { "/login", "/logout" })
public class AuthServlet extends HttpServlet {

	private UserRepository userRepository = null;

	public AuthServlet() {
		userRepository = new UserRepository();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/login":
			req.getRequestDispatcher("/WEB-INF/views/auth/index.jsp").forward(req, resp);
			break;
		case "/logout":
			HttpSession session = req.getSession();
			if (session.getAttribute(SessionConstants.USER_LOGIN) != null) {
				session.removeAttribute(SessionConstants.USER_LOGIN);
			}
			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// BƯỚC 1: Lấy thông tin đăng nhâp từ form
		String email = req.getParameter("email");
		String pass = req.getParameter("password");

		// BƯỚC 2: Gọi hàm truy vấn database kiểm tra email
		// TH1: user bằng null (Email không tồn tại) => Thông báo cho người dùng biết
		// TH2: user khác null (EMail tồn tại) => Qua bước 3
		User user = userRepository.findByEmail(email);
		if (user == null) {
			req.setAttribute("message", "Email không tồn tại!");
			req.getRequestDispatcher("/WEB-INF/views/auth/index.jsp").forward(req, resp);
			return;
		}

		// BƯỚC 3: Gọi Bcrypt.checkpw() để kiểm tra mật khẩu
		// TH1: Hàm trả về FALSE (Sai mật khẩu) => Thông báo cho người dùng biết
		// TH2: Hàm trả về TRUE (Mật khẩu đúng) => Qua bước 4
		if (!BCrypt.checkpw(pass, user.getPassword())) {
			req.setAttribute("message", "Mật khẩu không đúng!");
			req.getRequestDispatcher("/WEB-INF/views/auth/index.jsp").forward(req, resp);
			return;
		}
		// BƯỚC 4: Lưu thông tin USER vào Session
		HttpSession session = req.getSession();
		session.setAttribute(SessionConstants.USER_LOGIN, user);

		// BƯỚC 5: Chuyển hướng về trang chủ
		resp.sendRedirect(req.getContextPath() + "/home");
	}
}
