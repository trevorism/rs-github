<template>
  <div class="container">
    <div v-if="!loaded">
      {{message}}
    </div>
    <div v-else-if="loaded">
      <div v-for="topic in data" v-bind:key="topic.id">
        {{topic}} <br/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Grid',
  mounted () {
    this.data = ['hi', 'bye']
  },
  data () {
    return {
      data: [],
      loaded: false,
      message: 'Loading...'
    }
  }
}
</script>

<style scoped>
</style>
