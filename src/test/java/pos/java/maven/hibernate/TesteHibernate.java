package pos.java.maven.hibernate;

import java.util.List;

import org.junit.Test;

import pos.java.maven.hibernate.dao.DaoGeneric;
import pos.java.maven.hibernate.model.TelefoneUser;
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
	
	@Test//Query customizada
	public void testeQuery() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa p where p.nome ='atila'").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test//Query customizada
	public void testeQueryMaxResult() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa p order by p.id")
				.setMaxResults(2)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test//Busca com parâmetro
	public void testeQueryListParameter() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa p where p.nome = :nome and p.sobrenome = :sobrenome")
				.setParameter("nome", "atila")
				.setParameter("sobrenome", "onaya")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager()
				.createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Soma de todas as idades é "+somaIdade);
		
	}
	
	@Test
	public void testeNamedQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.consultaTodos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeNamedQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome").setParameter("nome","atila")
			.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("2944-5077");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
		
	}
	
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("---------------------------------------");
		}
		
	}
}
