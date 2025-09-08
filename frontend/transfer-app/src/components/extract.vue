<template>
  <div>
    <h2>Extrato de Transferências</h2>
    <ul>
      <li v-for="t in transferencias" :key="t.id">
        <pre class="pretty-block">
=========================================
      Source Account: {{ t.sourceAccount }}
      Destination Account:  {{ t.destinationAccount }}
      Value Transfer: R$ {{ formatCurrency(t.valueTransfer) }}
      Tax: R$ {{ formatCurrency(t.tax) }}
      Transfer: {{ t.dateTransfer }}
      Scheduler: {{ t.dateScheduler }}
=========================================
        </pre>
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
  },
  methods: {
    formatCurrency(value) {
      if (value === null || value === undefined) return '0,00';
      try {
        return new Intl.NumberFormat('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }).format(value);
      } catch (_) {
        const n = Number(value);
        return isNaN(n) ? String(value) : n.toFixed(2).replace('.', ',');
      }
    }
  }
};
</script>

<style scoped>
.pretty-block {
  background: #111;
  color: #ffffff;
  padding: 16px 20px;
  border-radius: 8px;
  border: 1px solid #2a2a2a;
  white-space: pre;  
  overflow-x: auto;
  line-height: 1.6;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
}
</style>
