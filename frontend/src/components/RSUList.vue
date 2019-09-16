<template>
  <div>
    <el-table height="200" stripe border
              :data="rsus"
              slot="No data">
      <el-table-column prop="id" label="Id" width="40"></el-table-column>
      <el-table-column prop="date" label="Date" width="100"></el-table-column>
      <el-table-column prop="count" label="Count"></el-table-column>
      <el-table-column prop="value" label="Value" :formatter="dollarFormat"></el-table-column>
      <el-table-column prop="usdhuf" label="USD to HUF"></el-table-column>
      <el-table-column label="Operations" width="170">
        <template slot-scope="scope">
          <el-button size="mini">Edit</el-button>
          <el-button size="mini" type="danger">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button size="medium" type="primary">Add</el-button>
  </div>
</template>

<script>
// import axios from 'axios'
export default {
  name: 'TaxType',
  data () {
    return {rsus: [{
      'id': 1,
      'date': '2019-09-15',
      'count': 1000,
      'value': 8.92,
      'usdhuf': 300.1
    }]}
  },
  mounted () {
  //  axios.get('/api/tax_type/get_all').then(response => { this.taxTypes = response.data })
  },
  methods: {
    dollarFormat (row, col, val, index) {
      return '$' + val
    }
  }
}
</script>

<style>
</style>
