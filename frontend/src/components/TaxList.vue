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
import axios from 'axios'
export default {
  name: 'TaxList',
  data () {
    return {
      taxes: []
    }
  },
  mounted () {
    axios.get('/api/tax/get_all').then(response => { this.taxes = response.data })
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
