<template>
  <div class="base-card">
    <slot />
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
.base-card {
  padding: 1.25rem;
  background: var(--color-background-soft);
  border: 1px solid var(--color-border);
  border-radius: 8px;
}
</style>
