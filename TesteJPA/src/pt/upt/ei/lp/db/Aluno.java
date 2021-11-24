package pt.upt.ei.lp.db;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private int numero;
	private String nome;

	@ManyToMany
	@JoinTable(name = "AlunoTurma", 
		joinColumns = { @JoinColumn(name = "idAluno", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "idTurma", referencedColumnName = "id") })
	private Set<Turma> turmas = new HashSet<Turma>();

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

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void addTurma( Turma t) {
		turmas.add(t);
	}
	
	public void removeTurma( Turma t) {
		turmas.remove(t);
	}
	
	public String toString() {
		String st = "Aluno id=" + id + "  num=" + numero + "  nome=" + nome
				+ "\n";
		for (Turma t : turmas) {
			st += "  turma id " + t.getId() +"  num " + t.getNumero() + "\n";
		}
		return st;
	}

}
