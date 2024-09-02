package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;

        /*return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();*/
    }

    public int mostrarLvl() {
        int levelAtual = 0;
        if (calcularTotalXp() <= 10) {
            levelAtual = 0;
        }
        if (calcularTotalXp() > 40) {
            levelAtual = 1;
        }
        if (calcularTotalXp() > 80) {
            levelAtual = 2;
        }
        if (calcularTotalXp() > 160) {
            levelAtual = 3;
        }
        return levelAtual;
    }

    public int progressaolvl() {
        int restante = 0;
        if (mostrarLvl() == 0) {
            restante = 10;
            restante -= calcularTotalXp(); 
            System.out.println("Você precisa de " + restante + " para o chegar ao próximo lvl");
        }
        if (mostrarLvl() == 1) {
            restante = 40;
            restante -= calcularTotalXp();
            System.out.println("Você precisa de " + restante + " para o chegar ao próximo lvl");
        }
        if (mostrarLvl() == 2) {
            restante = 160;
            restante -= calcularTotalXp();
            System.out.println("Você precisa de " + restante + " para o chegar ao próximo lvl");
        }
        if (mostrarLvl() == 3) {
            System.out.println("Você está no lvl máximo, parabéns!");
        }
        return restante;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
