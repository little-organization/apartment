<template>
  <div id="app">
    <viewer ref="viewer" :images="images" class="viewer" @inited="inited">
      <template slot-scope="scope">
        <img v-for="src in scope.images" :key="src" :src="src">
        {{ scope.options }}
      </template>
    </viewer>
    <button type="button" @click="show">Show</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      images: [
        'https://picsum.photos/200/200',
        'https://picsum.photos/300/200',
        'https://picsum.photos/250/200'
      ]
    }
  },
  methods: {
    inited(viewer) {
      this.$viewer = viewer
    },
    show() {
      this.$viewer.show()
    }
  }
}
</script>
