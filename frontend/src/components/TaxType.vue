<template>
    <div>
      <el-table height="200" stripe border
                :data="taxTypes"
                slot="No data">
        <el-table-column prop="id" label="Id" width="40"></el-table-column>
        <el-table-column prop="name" label="Name"></el-table-column>
        <el-table-column label="Percent">
          <template slot-scope="scope">
            {{ scope.row.percentage | percentFormatter }}
          </template>
        </el-table-column>
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
import repo from '../repositories/TaxTypeRepository'

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
      return this.$store.getters['TaxTypeStore/getTaxTypes']
    }
  },
  async mounted () {
    const types = await repo.getAll().catch(error => {
      this.$message(error.message)
    })
    this.$store.commit('TaxTypeStore/setTaxTypes', types)
  },
  methods: {
    async submitForm () {
      try {
        const update = this.newType.id ? await repo.update(this.newType) : await repo.save(this.newType)
        if (this.indexOfUpdate >= 0) {
          this.$store.commit('TaxTypeStore/updateTaxType', update)
        } else {
          this.$store.commit('TaxTypeStore/addTaxType', update)
        }
      } catch (error) {
        this.$message(error.message)
      }
      this.closeForm()
    },
    closeForm () {
      this.newType = {}
      this.indexOfUpdate = -1
      this.isAddFormVisible = false
    },
    async deleteType (index, rows) {
      const isDeleted = await repo.delete(rows[index].id).catch(
        error => this.$message(error.message)
      )
      isDeleted && this.$store.commit('TaxTypeStore/deleteTaxType', rows[index].id)
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
