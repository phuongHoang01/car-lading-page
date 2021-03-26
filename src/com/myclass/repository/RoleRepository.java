package com.myclass.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.db.JDBCConnection;
import com.myclass.entity.Role;

public class RoleRepository {

	// HÀM TRUY VẤN DB LẤY RA DANH SÁCH ROLE
	public List<Role> findAll() {
		final String QUERY = "SELECT * FROM roles";

		// B1: Kết nối db
		List<Role> roles = new ArrayList<Role>();
		try {
			Connection conn = JDBCConnection.getConnection();

			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = conn.prepareStatement(QUERY);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("description"));

				// Dùng hàm khởi tạo không tham số và hàm setter
//					Role role = new Role();
//					role.setId(resultSet.getInt("id"));
//					role.setName(resultSet.getString("name"));
//					role.setDescription(resultSet.getString("description"));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	public int save(Role role) {
		final String QUERY = "INSERT INTO roles(name, description) VALUES(?, ?)";
		try {
			Connection conn = JDBCConnection.getConnection();
			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = conn.prepareStatement(QUERY);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			// B3: Thực thi câu lệnh truy vấn
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
