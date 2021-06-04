<template>
  <div class="container">
    <b-table :data="details" :loading="loading" paginated
             backend-pagination :per-page="10" @page-change="onPageChange" default-sort="username" default-sort-direction="desc" :total="total">
      <b-table-column label="Avatar" width="40" field="avatar" v-slot="props">
        <img :src="props.row.avatarUrl"/>
      </b-table-column>
      <b-table-column label="Username" v-slot="props">
        <a target="_blank" :href="makeGithubUrl(props.row.username)">{{ props.row.username }}</a>
      </b-table-column>
      <b-table-column label="Real Name" v-slot="props">
        {{ props.row.realName }}
      </b-table-column>
      <b-table-column label="Location" v-slot="props">
        {{ props.row.location }}
      </b-table-column>
      <b-table-column label="Email" v-slot="props">
        {{ props.row.email }}
      </b-table-column>
      <b-table-column label="Public Repo Count" v-slot="props">
        {{ props.row.countOfPublicRepos }}
      </b-table-column>
      <b-table-column label="Account Created" v-slot="props">
        {{ new Date(props.row.creationDate).toLocaleDateString()  }}
      </b-table-column>
      <b-table-column label="Account Updated" v-slot="props">
        {{ new Date(props.row.lastUpdateDate).toLocaleDateString()  }}
      </b-table-column>
    </b-table>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Grid',
  props: ['searchBarValue'],
  data () {
    return {
      loading: false,
      page: 1,
      total: 0,
      details: []
    }
  },
  mounted () {
    this.loadAsyncData()
  },
  methods: {
    makeGithubUrl: function (username) {
      return 'https://www.github.com/' + username
    },
    onPageChange (page) {
      this.page = page
      this.loadAsyncData()
    },
    loadAsyncData () {
      this.loading = true
      console.log('Here in loadAsyncData ' + this.page)

      axios.post('/api/result/', {value: this.searchBarValue, page: this.page})
        .then(response => {
          this.total = response.data.totalCount
          this.details = response.data.records
          this.loading = false
        })
        .catch(() => {
          console.log('Error loading grid. Please refresh.')
          this.details = []
          this.loading = false
        })
    }
  }
}
</script>

<style scoped>
</style>
