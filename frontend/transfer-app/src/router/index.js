import { createRouter, createWebHistory } from 'vue-router';
import Scheduler from '../components/scheduler.vue';
import Extract from '../components/extract.vue';

const routes = [
  { path: '/', component: Scheduler },
  { path: '/extrato', component: Extract }
];

export const router = createRouter({
  history: createWebHistory(),
  routes
});