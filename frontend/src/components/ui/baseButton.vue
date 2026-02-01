<template>
  <button type="button" class="base-button" @click="$emit('click')">
    <slot />
  </button>
</template>

<script setup lang="ts">
defineEmits<{
  click: []
}>()
</script>

<style scoped>
.base-button {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  color: var(--color-background);
  background: var(--color-heading);
  border: 1px solid var(--color-border);
  border-radius: 6px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.base-button:hover {
  opacity: 0.9;
}
</style>
