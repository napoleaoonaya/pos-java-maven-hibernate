package pos.java.maven.hibernate;

import java.util.List;

import org.junit.Test;

import pos.java.maven.hibernate.dao.DaoGeneric;
import pos.java.maven.hibernate.model.UsuarioPessoa;

public class TesteHibernate {

	@Test//insert
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
		
		usuarioPessoa.setEmail("napoleao.onaya@gmail.com");
		usuarioPessoa.setIdade(34);
		usuarioPessoa.setLogin("teste");
		usuarioPessoa.setNome("napoleao");
		usuarioPessoa.setSenha("123");
		usuarioPessoa.setSobrenome("onaya");
		
		daoGeneric.salvar(usuarioPessoa);
	}
	
	@Test//select
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
		
		usuarioPessoa.setId(1L);
		
		usuarioPessoa = daoGeneric.pesquisar(usuarioPessoa);
		
		System.out.println(usuarioPessoa);
	}
	
	@Test//select
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L,UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test//update
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L,UsuarioPessoa.class);
		
		pessoa.setIdade(35);
		pessoa.setNome("nome atualizado no hibernate");
		
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test//delete
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L,UsuarioPessoa.class);
	
		daoGeneric.deletarPorId(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test//select all
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("------------------------------------------------------------------------");
		}
		
		
	}
	
	@Test
	public void testeQuery() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa p where p.nome ='atila'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
}
