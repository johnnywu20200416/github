package tw.leonchen.jspproject._hw.dao;

import java.sql.SQLException;

import tw.leonchen.jspproject._hw.PlayerJavaBean;

public interface IPlayerDao {
	public boolean createConn() throws SQLException;
	public void closeConn() throws SQLException;
	public boolean verifiedUserProfiles(PlayerJavaBean player) throws SQLException;
	public void updateBalance(PlayerJavaBean player) throws SQLException;
}
