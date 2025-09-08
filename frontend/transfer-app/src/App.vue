<script setup>
import { ref } from 'vue'
import { api } from './services/api'

const sourceAccount = ref('')
const destinationAccount = ref('')
const valueTransfer = ref(null)
const dateTransfer = ref('')
const message = ref('')
const transfers = ref([])

const createScheduler = async () => {
  try {
    const payload = {
      sourceAccount: sourceAccount.value,
      destinationAccount: destinationAccount.value,
      valueTransfer: valueTransfer.value,
      dateTransfer: formatDate(dateTransfer.value)
    }

    await api.post('/scheduler', payload)
    message.value = '✅ Transfer registered successfully!'
    
    sourceAccount.value = ''
    destinationAccount.value = ''
    valueTransfer.value = null
    dateTransfer.value = ''
  } catch (err) {
    console.error(err)
    message.value = '❌ Error registering transfer'
  }
}

const listSchedulers = async () => {
  try {
    const response = await api.get('/extract')
    transfers.value = response.data
    message.value = ''
  } catch (err) {
    console.error(err)
    message.value = '❌ Error when searching extract'
  }
}

function formatDate(dateInput) {
  const d = new Date(dateInput)
  const day = String(d.getDate()).padStart(2, '0')
  const month = String(d.getMonth() + 1).padStart(2, '0')
  const year = d.getFullYear()
  const hours = String(d.getHours()).padStart(2, '0')
  const minutes = String(d.getMinutes()).padStart(2, '0')
  const seconds = String(d.getSeconds()).padStart(2, '0')
  return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`
}

function formatCurrency(value) {
  if (value === null || value === undefined) return '0,00'
  try {
    return new Intl.NumberFormat('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }).format(value)
  } catch (_) {
    const n = Number(value)
    return isNaN(n) ? String(value) : n.toFixed(2).replace('.', ',')
  }
}
</script>

<template>
  <div class="app">
    <h1>Finance Transfer</h1>

    <form @submit.prevent="createScheduler" class="form">
      <input v-model="sourceAccount" placeholder="Source Account" required />
      <input v-model="destinationAccount" placeholder="Destination Account" required />
      <input v-model.number="valueTransfer" type="number" step="0.01" placeholder="Value transfer" required />
      <input v-model="dateTransfer" type="datetime-local" required />
      <button type="submit">Schedule Transfer</button>
    </form>

    <div v-if="message" class="message">{{ message }}</div>

    <button @click="listSchedulers">List Statement</button>

    <div v-if="transfers.length > 0" class="list">
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
  </div>
</template>

<style scoped>
.app {
  text-align: center;
  padding: 40px;
  color: white;
  background-color: #a4a1a1;
  min-height: 100vh;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  width: 300px;
  margin: 0 auto 20px auto;
}

input, button {
  padding: 10px;
  border-radius: 5px;
  border: none;
  font-size: 14px;
}

button {
  cursor: pointer;
  background: #1976d2;
  color: white;
  font-weight: bold;
}

button:hover {
  background: #0d47a1;
}

.message {
  margin: 15px;
  padding: 10px;
  border-radius: 5px;
  font-weight: bold;
}

.list {
  margin-top: 20px;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  background: #1e1e1e;
  margin: 8px auto;
  padding: 12px;
  border-radius: 6px;
  width: 70%;
  text-align: left;
}

.pretty-block {
  background: #111;
  color: #fff;
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
