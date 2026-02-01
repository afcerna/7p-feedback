<template>
  <div class="feedback-list">
    <p v-if="loading" class="status">Loading...</p>
    <p v-else-if="error" class="error">{{ error }}</p>
    <p v-else-if="feedbackList.length === 0" class="status">No feedback yet. Be the first!</p>
    <template v-else>
      <FeedbackEntry v-for="item in feedbackList" :key="item.id" :feedback="item" @onMarkHelpful="onUpdateLocalist" />
    </template>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import FeedbackEntry from './feedbackEntry.vue'
import { getAllFeedback } from '../../api/feedbackApi'
import type { Feedback } from '../../types/feedback'

const feedbackList = ref<Feedback[]>([])
const loading = ref(true)
const error = ref('')

async function fetchFeedback() {
  loading.value = true
  error.value = ''
  try {
    const items = await getAllFeedback()
    feedbackList.value = items.slice().reverse()
  } catch {
    error.value = 'Failed to load feedback. Try again.'
  } finally {
    loading.value = false
  }
}

function onUpdateLocalist(updatedFeedback: Feedback) {
  const index = feedbackList.value.findIndex((f) => f.id === updatedFeedback.id)
  if (index !== -1) {
    feedbackList.value[index] = updatedFeedback
  }
}

onMounted(fetchFeedback)

defineExpose({
  refetch: fetchFeedback,
})
</script>

<style scoped>
.feedback-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.status {
  color: var(--color-text);
  opacity: 0.8;
  margin: 0;
}
</style>
