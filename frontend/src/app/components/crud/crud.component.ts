import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Servico, ServicoServiceService } from '../../services/servico-service.service';

@Component({
  selector: 'app-crud',
  imports: [FormsModule],
  templateUrl: './crud.component.html',
  styleUrl: './crud.component.scss'
})
export class CrudComponent {
    servicoFormNome = '';
    servicoFormValor = 0;
    servicoFormDuracao = 0;

    servicoEditingId: number | undefined = undefined;
    servicoSearch = '';
    servicos: Servico[] = [];

    constructor(private servicoService: ServicoServiceService) {}

    criarServico() {
        if (this.servicoEditingId) {
            this.servicoService.update(this.servicoEditingId, {
                nome: this.servicoFormNome,
                valor: this.servicoFormValor,
                duracao: this.servicoFormDuracao
            });

            this.servicoEditingId = undefined;
            this.loadServicos();

            return;
        }

        this.servicoService.create({
            nome: this.servicoFormNome,
            valor: this.servicoFormValor,
            duracao: this.servicoFormDuracao
        });

        this.loadServicos();
    }

    getServico(id: number) {
        return this.servicoService.find(id);
    }

    get servicosFiltrados() {
        const search = this.servicoSearch.trim().toLowerCase();

        if (!search) {
            return this.servicos;
        }

        return this.servicos.filter(servico =>
            servico.nome.toLowerCase().includes(search)
        );
    }

    deleteServico(id: number) {
        this.servicoService.delete(id);
        this.loadServicos();
    }

    editarServico(id: number) {
        this.servicoEditingId = id;
        const servicoEditingObject = this.servicoService.find(this.servicoEditingId);

        if (!servicoEditingObject) {
            this.servicoEditingId = undefined;
            return;
        }

        this.servicoFormNome = servicoEditingObject.nome;
        this.servicoFormValor = servicoEditingObject.valor;
        this.servicoFormDuracao = servicoEditingObject.duracao;
    }

    ngOnInit() {
        this.loadServicos();
    }

    private loadServicos() {
        this.servicos = this.servicoService.getServicos();
    }
}
