<template>
  <div id="baseInputContainer">
    <label :for="uuid" v-if="label">{{ label }}</label>
    <input
        v-bind="$attrs"
        :placeholder="placeholder"
        class="field"
        :value="modelValue"
        @input="$emit('inputUpdate', $event.target.value)"
        :id="uuid"
        aria-describedby="error ? `${uuid}-error` : null"
        :aria-invalid="error ? true : null"
    >
    <p
        v-if="error"
        class="errorMessage"
        :id="`${uuid}-error`"
        aria-live="assertive"
    >
      {{ error }}
    </p>
  </div>
</template>

<script>
import UniqueID from "@/features/UniqueID"

export default {
  props: {
    label: {
      type: String,
      default: ''
    },
    modelValue: {
      type: [String, Number],
      default: ''
    },
    placeholder: {
      type: [String, Number],
      default: ''
    },
    error: {
      type: String,
      default: ''
    }
  },
  setup () {
    const uuid = UniqueID().getID()

    return {
      uuid
    }
  }
}
</script>

<style scoped>

</style>
