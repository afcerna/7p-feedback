<template>
  <BaseCard>
    <form class="feedback-form" @submit.prevent="onSubmit">
      <label for="message">Message*</label>
      <textarea
        id="message"
        v-model="message"
        class="feedback-message"
        :class="{ invalid: messageInvalid }"
        required
        rows="3"
        placeholder="Your feedback..."
        @input="messageInvalid = false"
      />
      <label for="author">Author</label>
      <input id="author" v-model="author" type="text" placeholder="Your name" />
      <BaseButton @click="onSubmit">
        Submit feedback
      </BaseButton>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </BaseCard>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import BaseCard from '../ui/baseCard.vue'
import BaseButton from '../ui/baseButton.vue'
import { createFeedback } from '../../api/feedbackApi'
import type { CreateFeedbackRequest } from '../../types/feedback'

const message = ref('')
const author = ref('')
const error = ref('')
const messageInvalid = ref(false)

const emit = defineEmits<{
  submitted: []
}>()

async function onSubmit() {
  if (!message.value.trim()) {
    messageInvalid.value = true
    return
  }
  error.value = ''
  try {
    const data: CreateFeedbackRequest = {
      message: message.value.trim(),
      author: author.value.trim() || undefined,
    }
    await createFeedback(data)
    message.value = ''
    author.value = ''
    emit('submitted')
  } catch (e) {
    error.value = 'Failed to submit. Try again.'
  }
}
</script>

<style scoped>
.feedback-form {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.feedback-form label {
  font-weight: 500;
  color: var(--color-heading);
}

.feedback-form textarea,
.feedback-form input {
  padding: 0.5rem;
  border: 1px solid var(--color-border);
  border-radius: 6px;
  font-size: 1rem;
  font-family: inherit;
}

.feedback-message.invalid {
  border-color: #c53030;
  box-shadow: 0 0 0 2px rgba(197, 48, 48, 0.15);
}

</style>
