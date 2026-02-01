<template>
  <div class="feedback-entry-wrap" :class="{ 'helpful-pulse': pulsing }">
    <BaseCard class="feedback-card">
      <div class="helpful-count" :class="{ 'counter-flash': counterFlashing }">
        <span class="helpful-icon" aria-hidden="true">
          <svg viewBox="0 0 24 24" role="img" focusable="false">
            <path
              d="M2 10h4v10H2V10zm20 1.5c0-.8-.7-1.5-1.5-1.5h-6.2l.9-4.1.02-.2c0-.3-.13-.6-.34-.8L13 3 7.6 8.4c-.4.4-.6 1-.6 1.6V18c0 1.1.9 2 2 2h7.2c.8 0 1.5-.5 1.8-1.3l2.2-5.6c.1-.2.1-.4.1-.6v-1.0z" />
          </svg>
        </span>
        {{ feedback.helpfulCount }}
      </div>
      <div class="feedback-entry">
        <p class="message">{{ feedback.message }}</p>
        <p class="meta">
          — {{ feedback.author ?? 'Anonymous' }}
        </p>
        <div class="actions">
          <BaseButton @click="onMarkHelpful">
            This is helpful
          </BaseButton>
          <p v-if="error" class="error">{{ error }}</p>
        </div>
      </div>
    </BaseCard>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import BaseCard from '../ui/baseCard.vue'
import BaseButton from '../ui/baseButton.vue'
import { markHelpful as markHelpfulApi } from '../../api/feedbackApi'
import type { Feedback } from '../../types/feedback'

const props = defineProps<{
  feedback: Feedback
}>()

const emit = defineEmits<{
  onMarkHelpful: [feedback: Feedback]
}>()

const pulsing = ref(false)
const counterFlashing = ref(false)
const error = ref('')

async function onMarkHelpful() {
  try {
    error.value = ''
    const updated = await markHelpfulApi(props.feedback.id)
    emit('onMarkHelpful', updated)
    pulsing.value = true
    counterFlashing.value = true
    setTimeout(() => {
      pulsing.value = false
      counterFlashing.value = false
    }, 400)
  } catch {
    error.value = 'Failed to mark as helpful. Try again.'
  }
}
</script>

<style scoped>
.feedback-entry-wrap {
  display: block;
}

.feedback-entry {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.feedback-card {
  position: relative;
}

.helpful-count {
  position: absolute;
  top: 0.75rem;
  right: 0.75rem;
  display: inline-flex;
  align-items: center;
  gap: 0.35rem;
  font-weight: 600;
  color: var(--color-text);
}

.helpful-icon {
  display: inline-flex;
  width: 1rem;
  height: 1rem;
}

.helpful-icon svg {
  width: 100%;
  height: 100%;
  fill: currentColor;
}

.message {
  color: var(--color-heading);
  margin: 0;
}

.meta {
  color: var(--color-text);
  font-size: 0.9rem;
  opacity: 0.9;
  margin: 0;
}

.actions {
  margin-top: 0.5rem;
}


.counter-flash {
  color: hsla(160, 100%, 37%, 1);
}
</style>
