<template>
  <div>
    <el-table height="200" stripe border
              :data="rsus"
              slot="No data">
      <el-table-column prop="id" label="Id" width="40"></el-table-column>
      <el-table-column label="Date" width="100">
        <template slot-scope="scope">
          {{ scope.row.settleDate | dateFormatter }}
        </template>
      </el-table-column>
      <el-table-column prop="count" label="Count"></el-table-column>
      <el-table-column label="Value">
        <template slot-scope="scope">
          {{ scope.row.value | dollarFormatter }}
        </template>
      </el-table-column>
      <el-table-column prop="usdhuf" label="USD to HUF"></el-table-column>
      <el-table-column label="Operations" width="170">
        <template slot-scope="scope">
          <el-button size="mini" @click="editRSU(scope.$index, rsus)">Edit</el-button>
          <el-button size="mini" type="danger" @click="deleteRSU(scope.$index, rsus)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button size="medium" type="primary" @click="openForm">Add</el-button>
    <RSUForm></RSUForm>
  </div>
</template>

<script>
import RSUForm from './RSUForm'
import repo from '../../repositories/RSURepository'

export default {
  name: 'RSUList',
  components: {RSUForm},
  computed: {
    rsus () {
      return this.$store.getters['RSUStore/getRsus']
    }
  },
  async mounted () {
    const loaded = await repo.getAll().catch(
      error => this.$message.warning(error.message)
    )
    this.$store.commit('RSUStore/setRsus', loaded)
  },
  methods: {
    async deleteRSU (index, rows) {
      const deleted = await repo.delete(rows[index].id).catch(
        error => this.$message.warning(error.message)
      )
      deleted && this.$store.commit('RSUStore/deleteRsu', rows[index].id)
    },
    editRSU (index, rows) {
      this.$store.commit('RSUStore/setNewRSU', rows[index])
      this.$store.commit('RSUStore/setAddFormVisible', true)
    },
    openForm () {
      this.$store.commit('RSUStore/setAddFormVisible', true)
    }
  }
}
</script>

<style>
</style>
