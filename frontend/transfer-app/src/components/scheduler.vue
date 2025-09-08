<template>
  <div>
    <h2>Agendar Transferência</h2>
    <form @submit.prevent="agendar">
      <input v-model="sourceAccount" placeholder="Conta Origem" />
      <input v-model="destinationAccount" placeholder="Conta Destino" />
      <input v-model.number="valueTransfer" type="number" placeholder="Valor da Transferência" />
      <input v-model="dateTransfer" type="date" />
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
      dateTransfer: '',
      mensagem: ''
    };
  },
  methods: {
    async agendar() {
      try {
        await api.post('/scheduler', {
          sourceAccount: this.sourceAccount,
          destinationAccount: this.destinationAccount,
          valueTransfer: this.valueTransfer,
          dateTransfer: this.dateTransfer + " 10:00:00",
          dateScheduler: new Date().toISOString().slice(0, 19).replace('T', ' ') 
        });
        this.mensagem = '✅ Transferência agendada com sucesso!';
      } catch (error) {
        console.error(error);
        this.mensagem = '❌ Erro ao agendar transferência.';
      }
    }
  }
};
</script>
