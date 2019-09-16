<template>
    <div>
      <el-table height="200" stripe border
                :data="taxTypes"
                slot="No data">
        <el-table-column prop="id" label="Id" width="40"></el-table-column>
        <el-table-column prop="name" label="Name"></el-table-column>
        <el-table-column prop="percentage" label="Percent" :formatter="percentFormat"></el-table-column>
        <el-table-column label="Operations" width="170">
          <template slot-scope="scope">
            <el-button size="mini">Edit</el-button>
            <el-button size="mini" type="danger">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button size="medium" type="primary" @click="isAddFormVisible=true">Add</el-button>
      <el-dialog title="Add new Tax Type" :visible.sync="isAddFormVisible" width="20%" center>
        <el-form :model="newType" label-width="90px" size="mini">
          <el-form-item label="Name">
            <el-input v-model="newType.name"></el-input>
          </el-form-item>
          <el-form-item label="Percentage">
            <el-input-number :precision="1" :step="0.1" :min="0.0" :max="100.0"
                             v-model="newType.percentage"></el-input-number>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="isAddFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="isAddFormVisible = false">Confirm</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'TaxType',
  data () {
    return {
      taxTypes: [],
      isAddFormVisible: false,
      newType: {
        'name': '',
        'percentage': 0.0
      }
    }
  },
  mounted () {
    axios.get('/api/tax_type/get_all').then(response => { this.taxTypes = response.data })
  },
  methods: {
    percentFormat (row, col, val, index) {
      return val + ' %'
    }
  }
}
</script>

<style>

</style>
