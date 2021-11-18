package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.List;

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
	private List<Aluno> alunos = new ArrayList<Aluno>();

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

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String toString() {
		String st = "Turma id=" + id + "  num=" + numero + "  nome=" + nome;
		for (Aluno a : alunos) {
			st += "\n    Aluno "+a.getId()+"  num "+a.getNumero()+"  nome "+a.getNome();
		}
		return st;
	}

}
