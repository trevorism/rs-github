<template>
  <div id="app">

    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">ResultStack Github Search</h1>
      </div>
    </section>

    <section class="container">
      <b-field label="Search github by a user's name or email" class="formWidth">
        <b-input v-model="searchBarValue" class="formWidth m-3">
        </b-input>
        <button class="button is-info m-3" @click="invokeButton">
          Submit
        </button>
        <button class="button is-danger m-3" @click="clear">
          Clear Results
        </button>
      </b-field>
    </section>

    <section class="section">

      <div v-if="showGrid">
        <Grid :searchBarValue="searchBarValue" :key="componentKey"></Grid>
      </div>
      <div v-if="inError">
        Unable to load results. Please contact site admin.
      </div>
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
      searchBarValue: '',
      inError: false,
      showGrid: false,
      componentKey: 0
    }
  },
  methods: {
    invokeButton: function () {
      this.componentKey += 1
      this.showGrid = true
    },
    clear: function () {
      this.showGrid = false
      this.inError = false
    }
  }

}
</script>

<style>
body::-webkit-scrollbar {
  display: none;
}
.formWidth {
  width: 400px
}
</style>
