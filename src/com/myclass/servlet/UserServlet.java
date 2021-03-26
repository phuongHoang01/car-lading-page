package com.myclass.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.UrlConstant;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;

@WebServlet(name = "userServlet", urlPatterns = { "/user", "/user/add", UrlConstant.URL_ROLE_EDIT, "/user/delete" })
public class UserServlet extends HttpServlet {

	private RoleRepository roleRepository = null;
	private UserRepository userRepository = null;

	public UserServlet() {
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();

		switch (action) {
		case "/user":
			req.setAttribute("users", userRepository.findAllUserRole());
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
		case "/user/add":
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
		case UrlConstant.URL_ROLE_EDIT:
			// LẤY id TỪ URL
			int id = Integer.valueOf(req.getParameter("id"));
			// TRUY VẤN DB LẤY THÔNG TIN USER CẦN SỬA
			User user = userRepository.findById(id);
			// CHUYỂN USER TÌM ĐƯỢC QUA TRANG edit.jsp
			req.setAttribute("user", user);
			req.setAttribute("roles", roleRepository.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
		case "/user/delete":

			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getServletPath();

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.valueOf(req.getParameter("roleId"));

		switch (action) {
		case "/user/add":
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			User user = new User(email, hashed, fullname, avatar, roleId);
			userRepository.save(user);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		case UrlConstant.URL_ROLE_EDIT:
			int id = Integer.valueOf(req.getParameter("id"));
			User userEdit = userRepository.findById(id);
			userEdit.setEmail(email);
			userEdit.setFullname(fullname);
			userEdit.setAvatar(avatar);
			userEdit.setRoleId(roleId);

			// KIỂM TRA XEM NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI KHÔNG
			if (password != null && !password.isEmpty()) {
				// MÃ HÓA MẬT KHẨU
				String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
				// CẬP NHẬT THÔNG TIN USER (BAO GỒM CẢ KHẨU)
				userEdit.setPassword(hashed2);
			}
			userRepository.update(userEdit);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		default:
			break;
		}
	}
}
