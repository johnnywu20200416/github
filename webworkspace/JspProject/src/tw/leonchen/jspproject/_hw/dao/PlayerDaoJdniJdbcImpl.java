package tw.leonchen.jspproject._hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tw.leonchen.jspproject._hw.PlayerJavaBean;

public class PlayerDaoJdniJdbcImpl implements IPlayerDao {
	private Connection conn;

	@Override
	public boolean createConn() throws SQLException {
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jndiJdbcConnSQLServer/PlayGames");
			conn = ds.getConnection();
			return true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void closeConn() throws SQLException {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean verifiedUserProfiles(PlayerJavaBean player) throws SQLException {
		boolean checkUserProfilesStatus = false;
		
		if(createConn()) {
			String sqlStr = "select userId, userBalance from userProfiles where (userName = ? and userPwd = ?)";
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			
			preState.setString(1, player.getName());
			preState.setString(2, player.getPwd());
			ResultSet rs = preState.executeQuery();
			
			if(rs.next()) {
				player.setId(rs.getInt("userId"));
				player.setBalance(rs.getInt("userBalance"));
				checkUserProfilesStatus = true;
			}
			
			rs.close();
			preState.close();
			closeConn();
		}
		
		return checkUserProfilesStatus;
	}

	@Override
	public void updateBalance(PlayerJavaBean player) throws SQLException {
		if(createConn()) {
			String sqlStr = "update userProfiles set userBalance = ? where userId = ?";
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			
			preState.setInt(1, player.getBalance());
			preState.setInt(2, player.getId());
			preState.executeUpdate();
			
			preState.close();
			closeConn();
		}
	}
		

}
