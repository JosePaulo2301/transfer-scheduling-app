<template>
  <div>
    <h2>Extrato de Transferências</h2>
    <ul>
      <li v-for="t in transferencias" :key="t.id">
        {{ t.sourceAccount }} → {{ t.destinationAccount }} |
        R$ {{ t.valueTransfer }} |
        Taxa: R$ {{ t.tax }} |
        Data: {{ t.dateTransfer }}
      </li>
    </ul>
  </div>
</template>

<script>
import { api } from '../services/api';

export default {
  data() {
    return {
      transferencias: []
    };
  },
  async mounted() {
    try {
      const res = await api.get('/extract');
      this.transferencias = res.data;
    } catch (err) {
      console.error('Error fetching transfers:', err);
    }
  }
};
</script>
