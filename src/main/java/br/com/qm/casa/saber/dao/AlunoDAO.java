package br.com.qm.casa.saber.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.qm.casa.saber.entity.Aluno;

public class AlunoDAO {

//	 (Criar, Remover, Consultar e Listar) para essas duas entidades, 
//	 e uma listagem de alunos por série, 
//	 além da funcionalidade de promover o aluno de série (modificar sua série).

	private EntityManager entityManager;

	public AlunoDAO() {
		this.entityManager = Persistence.createEntityManagerFactory("casaDoSaber").createEntityManager();
	}

	public boolean cadastraAluno(Aluno aluno) {

		this.entityManager.getTransaction().begin();
		this.entityManager.persist(aluno);
		this.entityManager.getTransaction().commit();

		return true;
	}

	public boolean removeAluno(int matricula) {

		Aluno aluno = this.entityManager.find(Aluno.class, matricula);

		if (aluno == null) {
			return false;
		}

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(aluno);
		this.entityManager.getTransaction().commit();

		return true;
	}

	public Aluno consultaAluno(int matricula) {
		return this.entityManager.find(Aluno.class, matricula);
	}

	public List<Aluno> listaAlunos() {

		Query query = this.entityManager.createQuery("select a from Aluno a");

		return query.getResultList();
	}

	public List<Aluno> listaAlunosPorSerie(int serie) {

		Query query = this.entityManager.createQuery("select a from Aluno a where a.serie = :serie");
		query.setParameter("serie", serie);

		return query.getResultList();
	}

	public boolean promoveAluno(int matricula) {

		Aluno aluno = this.entityManager.find(Aluno.class, matricula);

		if (aluno == null) {
			return false;
		}
		
		int anoPromocao = aluno.getDataPromocao().getYear();
		int anoAtual = LocalDate.now().getYear();
		
		if (anoPromocao >= anoAtual) {
			return false;
		}
		
		aluno.setSerie(aluno.getSerie() + 1);
		aluno.setDataPromocao(LocalDate.now());
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(aluno);
		this.entityManager.getTransaction().commit();

		return true;
	}
}
