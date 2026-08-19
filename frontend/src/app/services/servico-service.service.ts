import { Injectable } from '@angular/core';

export interface Servico {
    id: number,
    nome: string,
    valor: number,
    duracao: number
}

export interface ServicoCreateDefs {
    nome: string,
    valor: number,
    duracao: number
}

@Injectable({
    providedIn: 'root'
})
export class ServicoServiceService {
    servicos: Servico[] = [
        {
            id: 1,
            nome: "Corte de Cabelo",
            valor: 29.99,
            duracao: 30
        },
        {
            id: 2,
            nome: "Retoque Barba",
            valor: 19.99,
            duracao: 15
        },
        {
            id: 3,
            nome: "Corte de Cabelo e Barba",
            valor: 39.99,
            duracao: 45
        }
    ]

    private nextServicoId = this.servicos.length + 1;
    create(servico: ServicoCreateDefs) {
        if (!this.validateServicoCreate(servico)) {
            return;
        }

        this.servicos.push({
            id: this.nextServicoId++,
            ...servico
        });
    }

    update(id: number, defs: ServicoCreateDefs) {
        const servico = this.find(id);
        if (!servico) {
            return;
        }

        if (defs.nome !== undefined) {
            servico.nome = defs.nome;
        }

        if (defs.valor !== undefined) {
            servico.valor = defs.valor;
        }

        if (defs.duracao !== undefined) {
            servico.duracao = defs.duracao;
        }
    }

    find(id: number) {
        return this.servicos.find(servico => servico.id === id);
    }

    delete(id: number) {
        this.servicos = this.servicos.filter(servico => servico.id !== id);
    }

    getServicos() {
        return this.servicos;
    }

    private validateServicoCreate(servico: ServicoCreateDefs) {
        if (servico.nome.trim().length === 0) {
            alert("Nome de serviço inválido!");
            return false;
        }

        if (servico.valor <= 0) {
            alert("Valor de serviço inválido!");
            return false;
        }

        if (servico.duracao <= 0) {
            alert("Duração de serviço inválido!");
            return false;
        }

        return this.validateConflictingServico(servico);
    }

    private validateConflictingServico(servico: ServicoCreateDefs) {
        for (const current of this.servicos) {
            const normalizedCurrentNome = current.nome.trim().toUpperCase();
            const normalizedServicoNome = servico.nome.trim().toUpperCase();

            if (normalizedServicoNome === normalizedCurrentNome) {
                alert(`Já existe um Serviço com o nome de '${current.nome}'`)
                return false;
            }
        }

        return true;
    }
}
