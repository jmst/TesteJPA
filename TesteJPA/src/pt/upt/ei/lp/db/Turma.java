package pt.upt.ei.lp.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private int numero;
	private String nome;

	@ManyToMany(mappedBy="turmas")
	private Set<Aluno> alunos = new HashSet<Aluno>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void addAluno( Aluno a) {
		alunos.add(a);
		a.getTurmas().add(this);
	}
	
	public void removeAluno( Aluno a) {
		alunos.remove(a);
		a.getTurmas().remove(this);
	}
	
	public void removeAluno( int ida) {
		for (Aluno a : alunos) {
			if ( a.getId()== ida) {
				alunos.remove(a);
			return;
			}
		}
	}

	public String toString() {
		String st = "Turma id=" + id + "  num=" + numero + "  nome=" + nome;
		for (Aluno a : alunos) {
			st += "\n    Aluno "+a.getId()+"  num "+a.getNumero()+"  nome "+a.getNome();
		}
		return st;
	}

}
