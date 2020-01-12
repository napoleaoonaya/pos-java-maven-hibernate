package pos.java.maven.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pos.java.maven.hibernate.HibernateUtil;
import pos.java.maven.hibernate.model.UsuarioPessoa;

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
		
		
	}
	
	//Salva ou atualiza
	public E updateMerge(E entidade) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		
		E entidadeSalva = entityManager.merge(entidade);
		
		entityTransaction.commit();
		
		return entidadeSalva;
	}
	
	//pesquisar
	public E pesquisar(E entidade) {
		
		Object id = HibernateUtil.getPrimaryKey(entidade);
		
		E e = (E) entityManager.find(entidade.getClass(), id);
		
		return e;
	}
	
	//pesquisar
	public E pesquisar(Long id, Class<E> entidade) {
		
		E e = (E) entityManager.find(entidade, id);
		
		return e;
	}
	
	//delete
	public void deletarPorId(E entidade) {
		
		Object id = HibernateUtil.getPrimaryKey(entidade);
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		entityManager.createNativeQuery("delete from "
				+ entidade.getClass().getSimpleName().toLowerCase()
				+ " where id =" + id).executeUpdate();
		
		transaction.commit();//grava alteração no banco
		
		
	}
	
	public List<E> listar(Class<E> entidade){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		List<E> lista = entityManager.createQuery("from "+entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
		
	}
	
}
