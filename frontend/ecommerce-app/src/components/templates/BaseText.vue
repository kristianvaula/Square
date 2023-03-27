<template>
  <label 
    v-if="label"
    :for="id"
    >{{ label }}</label>
  <input
    v-if="!textarea"
    v-bind="$attrs" 
    :placeholder="label"
    class="field"
    :value="modelValue"
    @input="$emit('update:modelValue', $event.target.value)"
    :id="id"
    :aria-describedby="error ? '${uuid}-error' : null"
    :aria-invalid="error ? true : null"
  >
  <textarea
    v-if="textarea"
    v-bind="$attrs" 
    placeholder="..."
    class="field"
    :value="modelValue"
    @input="$emit('update:modelValue', $event.target.value)"
    :id="id"
    :aria-describedby="error ? '${uuid}-error' : null"
    :aria-invalid="error ? true : null"
  />
  <p 
    v-if="error"
    class="errorMessage"
    :id="id + '-error'"
    arie-live='assertive'
  >
  {{ error }}
  </p>
</template>

<script>
import UniqueID from '@/features/UniqueID';

  export default {
    name: 'BaseText',
    props: {
      label: {
        type: String, 
        default: ''
      },
      modelValue: {
        type: [String, Number],
        default: ''
      },
      error: {
        type: String, 
        default: ''
      }, 
      textarea: {
        type: Boolean, 
        default:false
      },
      id: {
        type: String, 
        default: UniqueID().getID()
      },
    }
  }
</script>
<style scoped>
label {
  margin-right:10px;
  font-size:1.25em;
}

textarea {
  min-height:100px;
  min-width:300px;
}
</style>