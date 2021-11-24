package pt.upt.ei.lp.db;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TesteJPAMain {
	private static final String PERSISTENCE_UNIT_NAME = "TesteJPA";
	private static EntityManagerFactory factory;

	public TesteJPAMain() {
	}

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = null;
		List<Aluno> alunos = null;
		List<Turma> turmas = null;
		// Read the existing entries
		em.getTransaction().begin();
		Query qd = em.createQuery("SELECT a FROM Aluno a");
		List<Aluno> la = (List<Aluno>) qd.getResultList();
		for (Aluno a : la) {
			em.remove(a);
		}
		qd = em.createQuery("SELECT t FROM Turma t");
		List<Turma> ta = (List<Turma>) qd.getResultList();
		for (Turma t : ta) {
			em.remove(t);
		}
		em.getTransaction().commit();

		System.out.println("Limpei BD");
		System.out.println("------------------------");
		// Read the existing entries
		q = em.createQuery("select a from Aluno a");

		// Do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			// Begin a new local transaction so that we can persist a new entity
			em.getTransaction().begin();
			Aluno aluno1 = new Aluno();
			aluno1.setNumero(1);
			aluno1.setNome("Abel");
			em.persist(aluno1);
			Aluno aluno2 = new Aluno();
			aluno2.setNumero(2);
			aluno2.setNome("Bruno");
			em.persist(aluno2);
			Aluno aluno3 = new Aluno();
			aluno3.setNumero(3);
			aluno3.setNome("Carolina");
			em.persist(aluno3);
			Aluno aluno4 = new Aluno();
			aluno4.setNumero(4);
			aluno4.setNome("Duarte");
			em.persist(aluno4);
			//
			Turma turma1 = new Turma();
			turma1.setNumero(11);
			turma1.setNome("IA");
			em.persist(turma1);
			aluno1.addTurma(turma1);
			aluno3.addTurma(turma1);
			Turma turma2 = new Turma();
			turma2.setNumero(12);
			turma2.setNome("PAJ");
			em.persist(turma2);
			aluno1.addTurma(turma2);
			aluno4.addTurma(turma2);
			Turma turma3 = new Turma();
			turma3.setNumero(13);
			turma3.setNome("FBD");
			em.persist(turma3);
			aluno2.addTurma(turma3);
			Turma turma4 = new Turma();
			turma4.setNumero(14);
			turma4.setNome("POO");
			em.persist(turma4);
			aluno1.addTurma(turma4);
			aluno3.addTurma(turma4);
			aluno4.addTurma(turma4);

			// Commit the transaction, which will cause the entity to
			// be stored in the database
			em.getTransaction().commit();
			em.refresh(turma1);
			em.refresh(turma2);
			em.refresh(turma3);
			em.refresh(turma4);
		}
		//
		q = em.createQuery("select a from Aluno a");
		alunos = q.getResultList();
		System.out.println("------------------------");
		System.out.println("tabela Alunos");
		for (Aluno a : alunos) {
			System.out.println(a);
		}
		//
		q = em.createQuery("select t from Turma t");
		turmas = q.getResultList();
		System.out.println("------------------------");
		System.out.println("tabela Turmas");
		for (Turma t : turmas) {
			System.out.println(t);
		}
		System.out.println("------------------------");
		System.out.println("Apagar aluno 3");
		em.getTransaction().begin();
		q = em.createQuery("SELECT a FROM Aluno a WHERE a.numero = :num");
		q.setParameter("num", 3);
		List<Aluno> la2 = (List<Aluno>) q.getResultList();
		if (la2.size() == 1) {
			Aluno ar = la2.get(0);
			Set<Turma> lt = ar.getTurmas();
			em.remove(ar);
		}
		em.getTransaction().commit();
		//
		System.out.println("------------------------");
		System.out.println("Alterar aluno 2");
		em.getTransaction().begin();
		q = em.createQuery("SELECT a FROM Aluno a WHERE a.numero = :num");
		q.setParameter("num", 2);
		List<Aluno> la3 = (List<Aluno>) q.getResultList();
		if (la3.size() == 1)	// encontrou um aluno?
			la3.get(0).setNumero(222);
		em.getTransaction().commit();
		//
		q = em.createQuery("select a from Aluno a");
		alunos = (List<Aluno>) q.getResultList();
		System.out.println("------------------------");
		System.out.println("tabela Alunos");
		for (Aluno al : alunos) {
			System.out.println(al);
		}
		//
		q = em.createQuery("select t from Turma t");
		turmas = (List<Turma>) q.getResultList();
		System.out.println("------------------------");
		System.out.println("tabela Turmas");
		for (Turma t : turmas) {
			em.refresh(t);
			System.out.println(t);
		}

		em.close();
		System.out.println("\n\nTerminei!!!");
	}

}
