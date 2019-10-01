<template>
  <div>
    <el-table height="200" stripe border
              :data="taxes"
              slot="No data">
      <el-table-column prop="rsu" label="Rsu Id"></el-table-column>
      <el-table-column prop="date" label="Due Date"></el-table-column>
      <el-table-column prop="taxType.name" label="Type"></el-table-column>
      <el-table-column prop="value" label="Value" :formatter="hufFormat"></el-table-column>
      <el-table-column prop="isPaid" label="Status"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import repo from '../repositories/TaxRepository'

export default {
  name: 'TaxList',
  computed: {
    taxes () {
      return this.$store.getters['TaxStore/getTaxes']
    }
  },
  async mounted () {
    try {
      const loaded = await repo.getAll()
      this.$store.commit('TaxStore/setTaxes', loaded)
    } catch (e) {
      this.$message.warning(e.message)
    }
  },
  methods: {
    hufFormat (row, col, val, index) {
      return val + ' Ft'
    }
  }
}
</script>

<style>
</style>
