<template>
  <div>
    <h2>Transfer Statement</h2>
    <ul>
      <li v-for="t in transfers" :key="t.id">
        <div class="pretty-block">
          <div class="sep"></div>
          <div class="row"><span class="label">Source Account:</span><span class="value">{{ t.sourceAccount }}</span></div>
          <div class="row"><span class="label">Destination Account:</span><span class="value">{{ t.destinationAccount }}</span></div>
          <div class="row"><span class="label">Value Transfer:</span><span class="value">R$ {{ formatCurrency(t.valueTransfer) }}</span></div>
          <div class="row"><span class="label">Tax:</span><span class="value">R$ {{ formatCurrency(t.tax) }}</span></div>
          <div class="row"><span class="label">Transfer:</span><span class="value">{{ t.dateScheduler }}</span></div>
          <div class="row"><span class="label">Scheduler:</span><span class="value">{{ t.dateTransfer }}</span></div>
          <div class="sep"></div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { api } from '../services/api';

export default {
  data() {
    return {
      transfers: []
    };
  },
  async mounted() {
    try {
      const res = await api.get('/extract');
      this.transfers = res.data;
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
}
.pretty-block .sep {
  border-top: 2px dashed #bdbdbd;
  margin: 4px 0 12px;
}
.row {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  line-height: 1.6;
}
.label {
  width: 190px;
  min-width: 160px;
  font-weight: 600;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
}
.value {
  flex: 1 1 auto;
  overflow-wrap: anywhere;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
}
</style>
