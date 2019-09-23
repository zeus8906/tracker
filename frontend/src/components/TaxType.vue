<template>
    <div>
      <el-table height="200" stripe border
                :data="taxTypes"
                slot="No data">
        <el-table-column prop="id" label="Id" width="40"></el-table-column>
        <el-table-column prop="name" label="Name"></el-table-column>
        <el-table-column prop="percentage" label="Percent" :formatter="percentFormat"></el-table-column>
        <el-table-column prop="onSell" label="When Sell?" width="95">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.onSell ? 'success' : 'danger'"
              disable-transitions>{{ scope.row.onSell ? 'Yes' : 'No' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="Operations" width="170">
          <template slot-scope="scope">
            <el-button size="mini" @click="editType(scope.$index, taxTypes)">Edit</el-button>
            <el-button size="mini" type="danger" @click="deleteType(scope.$index, taxTypes)">Delete</el-button>
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
            <el-input-number :precision="1" :step="1.0" :min="0.0" :max="100.0"
                             v-model="newType.percentage"></el-input-number>
          </el-form-item>
          <el-form-item>
            <el-checkbox v-model="newType.onSell">Apply on sell</el-checkbox>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="closeForm">Cancel</el-button>
          <el-button type="primary" @click="submitForm">Confirm</el-button>
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
      isAddFormVisible: false,
      indexOfUpdate: -1,
      newType: {}
    }
  },
  computed: {
    taxTypes () {
      return this.$store.getters.getTaxTypes
    }
  },
  mounted () {
    this.$store.dispatch('loadTaxTypes')
  },
  methods: {
    percentFormat (row, col, val, index) {
      return val + ' %'
    },
    submitForm () {
      var promise = this.newType.id ? axios.put('/api/tax_type/update', this.newType) : axios.post('/api/tax_type/add', this.newType)
      promise.then(response => {
        if (this.indexOfUpdate >= 0) {
          this.$store.commit('updateTaxType', response.data)
        } else {
          this.$store.commit('addTaxType', response.data)
        }
        this.closeForm()
      })
    },
    closeForm () {
      this.newType = {}
      this.indexOfUpdate = -1
      this.isAddFormVisible = false
    },
    deleteType (index, rows) {
      axios.delete('/api/tax_type/delete/' + rows[index].id)
        .then(response => {
          response.data && this.$store.commit('deleteTaxType', rows[index].id)
        })
    },
    editType (index, rows) {
      this.newType = rows[index]
      this.indexOfUpdate = index
      this.isAddFormVisible = true
    }
  }
}
</script>

<style>
.el-table .cell {
  text-align: center;
}
</style>
