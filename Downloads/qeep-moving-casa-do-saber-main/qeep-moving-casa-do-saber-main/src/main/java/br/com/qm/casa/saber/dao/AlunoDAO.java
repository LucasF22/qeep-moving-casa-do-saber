package br.com.qm.casa.saber.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.qm.casa.saber.entity.Aluno;
import br.com.qm.casa.saber.exception.CasaSaberException;

public class AlunoDAO {

	private EntityManager entityManager;

	public AlunoDAO() {
		this.entityManager = Persistence.createEntityManagerFactory("casaDoSaber").createEntityManager();
	}
	
	public boolean cadastraAluno(Aluno aluno) throws CasaSaberException {
		aluno = this.entityManager.find(Aluno.class, aluno.getMatricula());
		
		if(aluno != null) {
			throw new CasaSaberException("Aluno j� est� cadastrado!");
		}
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(aluno);
		this.entityManager.getTransaction().commit();
		
		
		return true;
	}

	public boolean removeAluno(int matricula) throws CasaSaberException {

		Aluno aluno = this.entityManager.find(Aluno.class, matricula);

		if (aluno == null) {
			throw new CasaSaberException("Aluno n�o existe! Verifique se a matricula informada est� correta!");
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

	public boolean promoveAluno(int matricula) throws CasaSaberException {

		Aluno aluno = this.entityManager.find(Aluno.class, matricula);

		if (aluno == null) {
			throw new CasaSaberException("Aluno n�o existe! Verifique se a matricula informada est� correta!");
		}
		
		int anoPromocao = aluno.getDataPromocao().getYear();
		int anoAtual = LocalDate.now().getYear();
		
		if (anoPromocao >= anoAtual) {
			throw new CasaSaberException("Aluno j� foi promovido de S�rie este ano!");
		}
		
		aluno.setSerie(aluno.getSerie() + 1);
		aluno.setDataPromocao(LocalDate.now());
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(aluno);
		this.entityManager.getTransaction().commit();

		return true;
	}
}
