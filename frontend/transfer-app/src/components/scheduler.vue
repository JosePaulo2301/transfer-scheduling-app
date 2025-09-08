<template>
  <div>
    <h2>Agendar Transferência</h2>
    <form @submit.prevent="agendar">
      <input v-model="sourceAccount" placeholder="Conta Origem" />
      <input v-model="destinationAccount" placeholder="Conta Destino" />
      <input v-model.number="valueTransfer" type="number" placeholder="valueTransfer" />
      <input v-model="dataTransferencia" type="date" />
      <button type="submit">Agendar</button>
    </form>
    <p v-if="mensagem">{{ mensagem }}</p>
  </div>
</template>

<script>
import { api } from '../services/api';

export default {
  data() {
    return {
      sourceAccount: '',
      destinationAccount: '',
      valueTransfer: null,
      dataTransferencia: '',
      mensagem: ''
    };
  },
  methods: {
    async agendar() {
      try {
        await api.post('', {
          sourceAccount: this.sourceAccount,
          destinationAccount: this.destinationAccount,
          valueTransfer: this.valueTransfer,
          dataTransferencia: this.dataTransferencia
        });
        this.mensagem = 'Transferência agendada com sucesso!';
      } catch (error) {
        this.mensagem = 'Erro ao agendar transferência.';
      }
    }
  }
};
</script>
