<template>
  <div class="container">
    <b-table :data="details" :paginated="true" :per-page="10" default-sort="datecreated"
             default-sort-direction="desc">
      <b-table-column label="Avatar" width="40" field="avatar" v-slot="props">
        <img :src="props.row.avatarUrl"/>
      </b-table-column>
      <b-table-column label="Username" v-slot="props">
        <a :href="makeGithubUrl(props.row.username )">{{ props.row.username }}</a>
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
export default {
  name: 'Grid',
  props: ['details'],
  methods: {
    makeGithubUrl: function (username) {
      return 'https://www.github.com/' + username
    }
  }
}
</script>

<style scoped>
</style>
