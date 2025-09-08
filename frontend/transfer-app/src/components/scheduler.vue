<template>
  <div>
    <h2>Schedule transfer</h2>
    <form @submit.prevent="agendar">
      <input v-model="sourceAccount" placeholder="Source Account" />
      <input v-model="destinationAccount" placeholder="Destination Account" />
      <input v-model.number="valueTransfer" type="number" placeholder="Value Transer" />
      <input v-model="dateTransfer" type="date" />
      <button type="submit">Schedule</button>
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
    async scheduler() {
      try {
        await api.post('/scheduler', {
          sourceAccount: this.sourceAccount,
          destinationAccount: this.destinationAccount,
          valueTransfer: this.valueTransfer,
          dateTransfer: this.dateTransfer + " 10:00:00",
          dateScheduler: new Date().toISOString().slice(0, 19).replace('T', ' ') 
        });
        this.mensagem = '✅ Transfer registered successfully!';
      } catch (error) {
        console.error(error);
        this.mensagem = '❌ Error registering transfer';
      }
    }
  }
};
</script>
