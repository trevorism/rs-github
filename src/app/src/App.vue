<template>
  <div id="app">

    <section class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">ResultStack Github Search</h1>
      </div>
    </section>

    <section class="container">
      <b-field label="Search github by a user's name or email" class="formWidth">
        <b-input :value="searchBarValue" class="formWidth m-3">
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
      <div v-if="details.length">
        <Grid :details="details"></Grid>
      </div>
    </section>

  </div>
</template>

<script>
import axios from 'axios'
import Grid from './components/Grid.vue'

export default {
  name: 'App',
  components: {
    Grid
  },
  data () {
    return {
      searchBarValue: '',
      details: []
    }
  },
  methods: {
    invokeButton: function () {
      axios.post('/api/result/', {value: this.searchBarValue})
        .then(response => {
          this.details = response.data
        })
        .catch(() => {
          console.log('Error loading status details. Please refresh.')
        })
    },
    clear: function () {
      this.details = []
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
