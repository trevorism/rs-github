<template>
  <div id="app">

    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">ResultStack Github Search</h1>
      </div>
    </section>

    <section class="section">
      <b-field label="Search github by username or email">
        <b-input :value="searchBarValue" class="m-3">
        </b-input>
        <button class="button is-info m-3" :disabled="disabled" @click="invokeButton">
          Submit
          <b-loading :is-full-page="false" :active.sync="disabled" :can-cancel="false"></b-loading>
        </button>
        <button class="button is-danger m-3" :disabled="disabled" @click="invokeButton">
          Clear Results
          <b-loading :is-full-page="false" :active.sync="disabled" :can-cancel="false"></b-loading>
        </button>
      </b-field>
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
  },
  data () {
    return {
      searchBarValue: 'hi'
    }
  },
  methods: {
    invokeButton: function () {
      this.searchBarValue = 'bye'
    }
  }

}
</script>

<style>
body::-webkit-scrollbar {
  display: none;
}
</style>
