package pos.java.maven.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	public static EntityManagerFactory entityManagerFactory = null;
	
	static {
		init();
	}
	
	
	private static void init() {
		
		
		try {
			
			
			if(entityManagerFactory == null) {
				entityManagerFactory = Persistence.createEntityManagerFactory("pos-java-maven-hibernate");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {
		
		return entityManagerFactory.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
