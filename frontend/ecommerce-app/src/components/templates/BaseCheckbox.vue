<template>
  <input
      :id="uuid"
      type="checkbox"
      :checked="modelValue"
      @change="$emit('update:modelValue', $event.target.checked)"
      class="field"
    />
  <label :for="uuid" v-if="label">{{ label }}</label>
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
      type: Boolean, 
      defualt: false
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