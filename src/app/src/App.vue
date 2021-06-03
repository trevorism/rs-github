<template>
  <div id="app">

    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">ResultStack Github Search</h1>
      </div>
    </section>
    <section class="section">
      <Grid></Grid>
    </section>

  </div>
</template>

<script>
import Grid from './components/Grid.vue'

export default {
  name: 'App',
  components: {
    Grid
  }
}
</script>

<style>
body::-webkit-scrollbar {
  display: none;
}
</style>
