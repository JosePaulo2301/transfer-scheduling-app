<script setup>
import { ref } from 'vue'
import { api } from './services/api'

// estado para guardar o resultado
const result = ref(null)

// função para criar agendamento
const createScheduler = async () => {
  try {
    const payload = {
      destinationAccount: "1234567890",
      sourceAccount: "9876543210",
      valueTransfer: 100,
      dateTransfer: "10/09/2025 10:00:00",
      dateScheduler: "08/09/2025 10:00:00"
    }
    const response = await api.post('/scheduler', payload)
    result.value = response.data
  } catch (err) {
    console.error(err)
    result.value = "Erro ao criar agendamento"
  }
}

// função para listar agendamentos
const listSchedulers = async () => {
  try {
    const response = await api.get('/extract')
    result.value = response.data
  } catch (err) {
    console.error(err)
    result.value = "Erro ao listar agendamentos"
  }
}
</script>

<template>
  <div class="app">
    <h1>Finance Transfer</h1>

    <div class="buttons">
      <button @click="createScheduler">Schedule Transfer</button>
      <button @click="listSchedulers">List Scheduler</button>
    </div>

    <div v-if="result">
      <h2>Resultado</h2>
      <pre>{{ result }}</pre>
    </div>
  </div>
</template>

<style scoped>
.app {
  text-align: center;
  padding: 50px;
  color: white;
  background-color: #121212;
  min-height: 100vh;
}

.buttons {
  margin: 20px 0;
}

button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}
</style>
