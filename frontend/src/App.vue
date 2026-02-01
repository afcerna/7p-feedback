<template>
  <header>
    <h1>7P Demo App</h1>
  </header>

  <main>
    <FeedbackForm @submitted="feedbackListRef?.refetch()" />
    <FeedbackList ref="feedbackListRef" />
  </main>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const feedbackListRef = ref<{ refetch: () => void } | null>(null)
</script>
