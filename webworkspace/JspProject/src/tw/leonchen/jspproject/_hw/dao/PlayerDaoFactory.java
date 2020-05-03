package tw.leonchen.jspproject._hw.dao;

public class PlayerDaoFactory {
	public static IPlayerDao createPlayerDao() {
		IPlayerDao dao = new PlayerDaoJdniJdbcImpl();
		return dao;
	}
}
